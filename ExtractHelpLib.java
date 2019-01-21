
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * [How to write a Java program which can extract a JAR file and store its data
 * in specified directory (location)? - Stack
 * Overflow](https://stackoverflow.com/questions/1529611/how-to-write-a-java-program-which-can-extract-a-jar-file-and-store-its-data-in-s)
 * 
 * [file io - Delete directories recursively in Java - Stack
 * Overflow](https://stackoverflow.com/questions/779519/delete-directories-recursively-in-java)
 * 
 * 
 * @author xy2401
 *
 */
public class ExtractHelpLib {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		String tocfragmentFilename = "2018-12.tocfragment.xml";
		String pluginsDir = "D:\\code\\xy2401-local\\local-mirror\\mirror\\eclipse\\releases\\2018-12\\201812191000\\plugins";

		tocfragmentFilename = args[0];
		pluginsDir = args[1];

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(ExtractHelpLib.class.getResourceAsStream("/" + tocfragmentFilename));

		NodeList nodeList = doc.getFirstChild().getChildNodes();

		List<String> ids = new ArrayList<>();

		for (int i = 0; i < nodeList.getLength(); i++) {
			// 过滤空节点
			if (nodeList.item(i) instanceof Element) {
				ids.add(nodeList.item(i).getAttributes().getNamedItem("id").getTextContent());
			}
		}

		// 全部以/开头 截取
		ids = ids.stream().map(id -> id.split("/")[1]).collect(Collectors.toList());
		// 去重
		ids = ids.stream().distinct().collect(Collectors.toList());
		System.out.println("ids.size:" + ids.size());

		// 只要 jar 结尾的
		File[] jars = Paths.get(pluginsDir).toAbsolutePath().toFile().listFiles((dir, name) -> name.endsWith(".jar"));

		// 创建目标目录
		Path target = Paths.get("target").resolve(tocfragmentFilename.replace(".tocfragment.xml", ""));

		// 目标文件夹存在则清空 不存在 则创建
		if (target.toFile().exists()) {
			Files.walk(target).map(Path::toFile).sorted(Comparator.reverseOrder()).forEach(File::delete);
		} else {
			Files.createDirectories(target);
		}

		// ids = Arrays.asList(ids.get(0));

		int index = 0;
		for (String id : ids) {
			int count = 0;
			for (File jar : jars) {

				// org.eclipse.eef.documentation.ext.widgets.reference.source_2.1.1.201810110645.jar
				// org.eclipse.eef.documentation.ext.widgets.reference_2.1.1.201810110645.jar
				// org.eclipse.eef.documentation.javadoc.source_2.1.1.201810110645.jar
				// org.eclipse.eef.documentation.javadoc_2.1.1.201810110645.jar
				// org.eclipse.eef.documentation.source_2.1.1.201810110645.jar
				// org.eclipse.eef.documentation_2.1.1.201810110645.jar
				// 只匹配 帶_的
				if (jar.getName().startsWith(id + "_")) {
					count++;
					System.out.println((++index) + " extractArchive:" + jar.getName());
					extractArchive(jar.toPath(), target.resolve(id));

				}
			}
			if (count != 1) {
				System.out.println(id + " match jar count: " + count);
			}

		}
		System.out.println("jar extract end");

	}

	// jar 中 可能 无法遍历到 META-INF/ 文件夹 但是缺有 META-INF/ECLIPSE_.RSA   org.ec 
	// se.emf.doc_2.7.0.v20140203-1126.jar
	// entries:[META-INF/ECLIPSE_.RSA, META-INF/ECLIPSE_.SF, META-INF/MANIFEST.MF,
	// META-INF/eclipse.inf, about.html, about.ini, ....]
	public static void extractArchive(Path archiveFile, Path destPath) throws IOException {

		Files.createDirectories(destPath); // create dest path folder(s)

		try (ZipFile archive = new ZipFile(archiveFile.toFile())) {

			// sort entries by name to always create folders first
			List<? extends ZipEntry> entries = archive.stream().sorted(Comparator.comparing(ZipEntry::getName))
					.collect(Collectors.toList());
			// System.out.println("entries:"+entries);
			// copy each entry in the dest path
			for (ZipEntry entry : entries) {
				Path entryDest = destPath.resolve(entry.getName());

				if (entry.isDirectory()) {
					Files.createDirectory(entryDest);
					continue;
				}
				try {
					Files.copy(archive.getInputStream(entry), entryDest);
				} catch (java.nio.file.NoSuchFileException e) {
					// 可能是为jar/zip 目录没有属性 没有识别为 file 这里再创建一次目录(多层目录)
					Files.createDirectories(entryDest.getParent());
					Files.copy(archive.getInputStream(entry), entryDest);
				}
			}
		}

	}

}
