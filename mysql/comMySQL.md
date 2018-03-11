# 登入命令
mysql -uliguan2 -p2474696 -h 192.168.56.101
# 导出SQL文件
1. mysqldump -u liguan -p2474696 --skip-lock-tables --databases dbtbl_0 dbtbl_1 > dbtbl_1.sql
# 执行SQL
1. mysql –uroot –p123456 -Dtest\<ss.sql
2. msyql> source dbtbl_1.sql

# 主从配置
1. master要对于日志
mysql\> show master status;
+------------------+----------+--------------+------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB |
+------------------+----------+--------------+------------------+
| mysql-bin.000001 |      713 |              |                  |
+------------------+----------+--------------+------------------+
1 row in set (0.00 sec)
2. 开启slave进程
mysql> stop slave;
Query OK, 0 rows affected (0.00 sec)
mysql> change master to 
　　 -> master_host='192.168.0.107',
　　 -> master_user='repl',
　　 -> master_password='repl',
　　 -> master_log_file='mysql-bin.000001',
　　 -> master_log_pos=713;
mysql> start slave;
3. 显示
SHOW SLAVE STATUS\G;
4. 关闭
stop slave;
5. 开始
start slave;


# 用户权限
1、create schema [数据库名称] default character set utf8 collate utf8_general_ci;--创建数据库

　　采用create schema和create database创建数据库的效果一样。

 2、create mysql.user '[用户名称]'@'%' identified by '[用户密码]';--创建用户



# 配置文件
这里是指ubuntu配置文件/etc/mysql/mariadb.conf.d
## datadir 数据库目录
/var/lib/mysql

# 随机选择数据
SELECT * FROM tablename ORDER BY RAND()  LIMIT 10


# 建立数据库/表
create database test    
use test    
create table Person()   


# show 
## 查看单表大小  
show table status like 't_order_0'\G;
