
[Help - Eclipse Platform](https://help.eclipse.org)
Eclipse 文档静态版

`帮助文档 Eclipse documentation` [help.html](help.html)
`机翻` [help.html](help.html?tocfragment=../help.eclipse.2019-06.zh/tocfragment.xml)

`Tip of the Day` [tips.html](tips.html)
`每日提示 机翻` [tips.html](tips.html?provider=../help.eclipse.tips/org.eclipse.jdt.tips.user.zh-CN/provider.json)

# 文档列表 

* 202504111000 [Eclipse documentation 2025-06](help.html?v=2025-06)
* 202503121000 [Eclipse documentation 2025-03](help.html?v=2025-03)
* 202412041000 [Eclipse documentation 2024-12](help.html?v=2024-12)
* 202409111000 [Eclipse documentation 2024-09](help.html?v=2024-09)
* 202406121000 [Eclipse documentation 2024-06](help.html?v=2024-06)
* 202403131000 [Eclipse documentation 2024-03](help.html?v=2024-03)
* 202312061001 [Eclipse documentation 2023-12](help.html?v=2023-12) *4.30.0*
* 202309131000 [Eclipse documentation 2023-09](help.html?v=2023-09) *4.29.0*
* 202306141000 [Eclipse documentation 2023-06](help.html?v=2023-06) *4.28.0*
* 202303151000 [Eclipse documentation 2023-03](help.html?v=2023-03) *4.27.0*
* 202212071000 [Eclipse documentation 2022-12](help.html?v=2022-12) *4.26.0*
* 202209141001 [Eclipse documentation 2022-09](help.html?v=2022-09) *4.25.0*
* 202206151000 [Eclipse documentation 2022-06](help.html?v=2022-06) *4.24.0*
* 202203161000 [Eclipse documentation 2022-03](help.html?v=2022-03) *4.23.0*
* 202112081000 [Eclipse documentation 2021-12](help.html?v=2021-12) *4.22.0*
* 202109151000 [Eclipse documentation 2021-09](help.html?v=2021-09) *4.21.0*
* 202106161001 [Eclipse documentation 2021-06](help.html?v=2021-06) *4.20.0*
* 202103171000 [Eclipse documentation 2021-03](help.html?v=2021-03) *4.19.0*
* 202012161000 [Eclipse documentation 2020-12](help.html?v=2020-12) *4.18.0* 
* 202009161000 [Eclipse documentation 2020-09](help.html?v=2020-09) *4.17.0*
* 202006171000 [Eclipse documentation 2020-06](help.html?v=2020-06) *4.16.0*
* 202003181000 [Eclipse documentation 2020-03](help.html?v=2020-03) *4.15.0*
* 201912181000 [Eclipse documentation 2019-12](help.html?v=2019-12) *4.14.0*
* 201909181001 [Eclipse documentation 2019-09](help.html?v=2019-09) *4.13.0*
* 201906191000 [Eclipse documentation 2019-06](help.html?v=2019-06) *4.12.0*
* 201903201000 [Eclipse documentation 2019-03](help.html?v=2019-03) *4.11.0*
* 201812191000 [Eclipse documentation 2018-12](help.html?v=2018-12) *4.10.0*
* 201809191002 [Eclipse documentation 2018-09](help.html?v=2018-09) *4.9.0*

* 201806271001 [Eclipse Photon (4.8) Documentation](help.html?v=photon) *4.8*
* 201804111000 [Eclipse Oxygen (4.7) Documentation](help.html??v=oxygen) *4.7*
* 201705151400 [Eclipse Neon (4.6) Documentation](help.html?v=neon) *4.6*
* 201602261000 [Eclipse Mars (4.5) Documentation](help.html?v=mars) *4.5*
* 201502271000 [Eclipse Luna (4.4) Documentation](help.html?v=luna) *4.4*
* 201402280900 [Eclipse Kepler (4.3) Documentation](help.html?v=kepler) *4.3*
 

# script

`更新日志`[CHANGELOG.md](CHANGELOG.md)


 ```powershell
## 同步文件
rsync -avz  --safe-links   --recursive mirrors.nju.edu.cn::eclipse/releases/2024-12/ 2024-12

##下载目录
Invoke-WebRequest -Uri "https://help.eclipse.org/2024-12/advanced/tocfragment" -OutFile "toc/2024-12.tocfragment.xml"

## 解压文件
java ExtractHelpLib "toc/2022-09.tocfragment.xml"  "../help.eclipse.mirrors/2022-09/202209141001/plugins"

Copy-Item -Path "toc/2022-09.tocfragment.xml" -Destination "target/toc/2022-09/tocfragment.xml"

cd target/toc/2022-09/ 

git init
git add .
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:doc2401/help.eclipse.2022-09.git
git push -u origin main
cd ../../../



 ```

 ```bash
 
 # 代码中有中文注释 需要 指定 UTF-8编码(centos)
javac  -encoding UTF-8  ExtractHelpLib.java
 
## 查看目录
rsync download.eclipse.org::eclipseMirror/releases/

## 获取最佳镜像地址 Direct link to file (download starts immediately from best mirror)
curl -I "https://www.eclipse.org/downloads/download.php?file=/&r=1" |grep ocation
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
git remote add origin https://github.com/xy2401/help.eclipse.2018-09.git
#git push -u origin master
## 推送 gh-pages分支 就不用手动设置GitHub Pages
# push to the remote gh-pages branch with force
git push --force origin master:gh-pages
 


 ## 同步目录tip
 rsync  -avz  --safe-links   --recursive    mirrors.tuna.tsinghua.edu.cn::eclipse/eclipse/tips/ ../help.eclipse.tips


## org.eclipse.jst.ws.cxf.doc.user_1.0.300.v201802222200.jar 有一些另外都 jar 不在 xml 里面 。额外解压

```


# reference
 

`本地启动帮助中心` [Standalone help](https://help.eclipse.org/2019-06/index.jsp?topic=/org.eclipse.platform.doc.isv/guide/ua_help_setup_infocenter.htm&cp=2_0_19_1_0_2)

[Standalone help](help.html#/org.eclipse.platform.doc.isv/guide/ua_help_setup_standalone.htm)



[Tree-nav based on details/summary](https://codepen.io/dsheiko/pen/MvEpXm)


https://www.w3.org/TR/2017/NOTE-wai-aria-practices-1.1-20171214/examples/treeview/treeview-2/treeview-2b.html

 

