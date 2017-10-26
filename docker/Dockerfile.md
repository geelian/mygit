
# FROM 命令
继承镜像

# MAINTAINER 
作者

# RUN 
运行时动作
```
RUN /bin/bash -c "echo helloworld"

```

# CMD
默认行为    
CMD echo "this is a test"   

# EXPOSE 

运行时监听的端口

# ENV 

环境变量

# ADD 
当前工作目录复制文件到镜像目录中

# ENTRYPOINT

让容器像可执行程序运行  


# eg

Dockerfile
```
FROM java:8
MAINTAINER liguan

ADD ch10docker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
```

## 建立
docker build -t docker/file .

## 运行
docker run -d --name ch10 -p 8080:8080 docker/file

