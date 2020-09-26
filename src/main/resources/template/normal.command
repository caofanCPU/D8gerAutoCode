# mvn编译检查详细未检查异常信息
mcf compile -Xlint:unchecked

http --verify=no -v -f POST https://1.d8ger.com/uploadFile param1:=1 file@~/tmpUploadFile/xxx.png

# 使用chrome的插件下载cookies.txt
youtube-dl --cookies cookies.txt  --proxy http://127.0.0.1:1087/ -F  网址
youtube-dl --cookies cookies.txt  --proxy http://127.0.0.1:1087/ https://www.youtube.com/watch\?v\=Y4WgD0FljKU   -x --audio-format mp3

# AsciiDoc转markdown
brew install asciidoc
brew install pandoc
asciidoc -b docbook acm.adoc
iconv -t utf-8 acm.xml | pandoc -f docbook -t markdown_strict --wrap=none | iconv -f utf-8 > acm.md
