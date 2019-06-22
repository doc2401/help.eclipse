# 201906


rsync -avz  --safe-links   --recursive     mirrors.tuna.tsinghua.edu.cn::eclipse/releases/2019-06/201906191000/plugins/*.jar   ./201906191000-plugins
wget https://help.eclipse.org/2019-06/advanced/tocfragment -O 2019-06.tocfragment.xml
java ExtractHelpLib 2019-06.tocfragment.xml ./201906191000-plugins
cp 2019-06.tocfragment.xml target/2019-06/tocfragment.xml
cd target/2019-06/
git init
git add .




