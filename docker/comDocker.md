# 镜像


## 搜索

docker search redis

## 下载
docker pull redis   
sudo docker pull registry.docker-cn.com/library/redis    
## 列表

docker images

## 删除

docker rmi image-id     
所有    
docker rmi $(docker images -q)  

# 基本命令
* 需要root权限

1. 运行 
docker run --name test-redis -d library/redis   
下载的镜像要带搜索的前缀    
2. 容器列表
docker ps   
docker ps -a 运行还是停止   

3. 停止和启动
docker stop test-redis  
docker start test-redis  

4. 端口映射 
docker run -d -p 6378:6379 --name port-redis redis  

5. 删除容器
docker rm container-id      
docker rm $(docker ps -a -q)    

6. 日志
docker logs container-name/container-id     
docker logs port-redis  

7. 当然容器
docker exec -it container-id/container-name bash   
可以不写container-name  

8. 容器详情
docker  inspect 
# 工具
boot2docker ssh linux轻量级发行版本用于运行docker


