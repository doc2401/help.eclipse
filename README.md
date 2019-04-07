 


# nav

[help](help.html)

[tips](tips.html)


## Eclipse Documentation HTML Help Center

Current releases
----------------

*  201812191000 [Eclipse IDE 2018-12 (4.10) Documentation](http://help.eclipse.org/2018-12/index.jsp) [local](help.html?tocfragment=../local-doc-help.eclipse.org-2018-12/tocfragment.xml)

Older releases
--------------

* 201809191002 [Eclipse IDE 2018-09 (4.9) Documentation](http://help.eclipse.org/2018-09/index.jsp) [local](help.html?tocfragment=../local-doc-help.eclipse.org-2018-09/tocfragment.xml)
* 201806271001  [Eclipse Photon (4.8) Documentation](http://help.eclipse.org/photon/index.jsp) [local](help.html?tocfragment=../local-doc-help.eclipse.org-photon/tocfragment.xml)
* 201804111000  [Eclipse Oxygen (4.7) Documentation](http://help.eclipse.org/oxygen/index.jsp) [local](help.html?tocfragment=../local-doc-help.eclipse.org-oxygen/tocfragment.xml)
* 201705151400[Eclipse Neon (4.6) Documentation](http://help.eclipse.org/neon/index.jsp) [local](help.html?tocfragment=../local-doc-help.eclipse.org-neon/tocfragment.xml)
* 201602261000  [Eclipse Mars (4.5) Documentation](http://help.eclipse.org/mars/index.jsp) [local](help.html?tocfragment=../local-doc-help.eclipse.org-mars/tocfragment.xml)
* 201502271000  [Eclipse Luna (4.4) Documentation](http://help.eclipse.org/luna/index.jsp) [local](help.html?tocfragment=../local-doc-help.eclipse.org-luna/tocfragment.xml)
* 201402280900 [Eclipse Kepler (4.3) Documentation](http://help.eclipse.org/kepler/index.jsp) [local](help.html?tocfragment=../local-doc-help.eclipse.org-kepler/tocfragment.xml)

 


# script

 ```bash
 
 # 代码中有中文注释 需要 指定 UTF-8编码(centos)
javac  -encoding UTF-8  ExtractHelpLib.java
 
## 查看目录
rsync download.eclipse.org::eclipseMirror/releases/

## 获取最佳镜像地址 Direct link to file (download starts immediately from best mirror)
curl -I "https://www.eclipse.org/downloads/download.php?file=/&r=1" |grep Location
Location: http://ftp.osuosl.org/pub/eclipse/
## 查看镜像目录 不带pub前缀
rsync ftp.osuosl.org::eclipse/releases/


# 查看目录 找到发行版最新目录
rsync download.eclipse.org::eclipseMirror/releases/2018-09/201809191002/plugins/*.jar 
# 同步目录 发行版插件目录 到 201809191002-plugins ,不用提前创建 rsync will `created directory` 
rsync  -avz  --safe-links   --recursive    download.eclipse.org::eclipseMirror/releases/2018-09/201809191002/plugins/*.jar ./201809191002-plugins


#下载
wget https://help.eclipse.org/2018-09/advanced/tocfragment -O  2018-09.tocfragment.xml 
# java 代码 解压文件
java ExtractHelpLib 2018-09.tocfragment.xml  ./201809191002-plugins
# 拷贝文件
cp 2018-09.tocfragment.xml   target/2018-09/tocfragment.xml

## 发布github
cd target/2018-09/
git init
git add .## 添加所有文件
git commit -m "first commit"
git remote add origin https://github.com/xy2401/local-doc-help.eclipse.org-2018-09.git
#git push -u origin master
## 推送 gh-pages分支 就不用手动设置GitHub Pages
# push to the remote gh-pages branch with force
git push --force origin master:gh-pages
 


 ## 同步目录tip
 rsync  -avz  --safe-links   --recursive    mirrors.shu.edu.cn::eclipse/eclipse/tips/ ../local-doc-help.eclipse.org-tips


## org.eclipse.jst.ws.cxf.doc.user_1.0.300.v201802222200.jar 有一些另外都 jar 不在 xml 里面 。额外解压

```


# reference

 待补充说明


[Tree-nav based on details/summary](https://codepen.io/dsheiko/pen/MvEpXm)


https://www.w3.org/TR/2017/NOTE-wai-aria-practices-1.1-20171214/examples/treeview/treeview-2/treeview-2b.html

 

