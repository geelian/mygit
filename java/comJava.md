# 多文件编译
javac file.java
java 有main的file 相关文件
# 带package 编译
javac -d . file.java
java com.page.file
# 输出文档
javadoc -d /home/lg file.java

# JVM输出 反编译
javac Concatenation.java
javap -c Concatenation
javap -v C...// 信息更全
## 本地变量表输出
javac -g ** 必须在debug下   
javap -l opetion    
javap -c -v -l -s   


