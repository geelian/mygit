# Log4j2介绍和特性实例
1. 支持lambda表达式
2. 支持消息对象


# 介绍和特性
### log4j.xml放在资源下
```

<?xml version="1.0" encoding="UTF-8"?>   
<!-- 主要配置为error日志与debug日志分别打印文件，errorLog按照分钟存档，debugLog按照日志文件大小存档，最多保存10个。 -->  
<!-- 配置debug可以看到打印的信息 30s动态加载-->
<configuration status="debug" monitorInterval="30">   
<Properties>  
    <Property name="log.path">.</Property>  
</Properties>  
  
<appenders>  
    <!--Appender 1. 输出到Console，指定输出格式和过滤器等级为ERROR -->  
    <span style="white-space:pre">    </span><Console name="Console" target="SYSTEM_OUT">   
        <PatternLayout pattern="[%-5level][%d{yyyy-MM-dd HH:mm:ss}][%F:%L] - %m%n" />   
        <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>  
    </Console>   
      
    <!--Appender 2. 输出到滚动保存的文件, 触发保存日志文件的条件是日志文件大于3KB，只保存最新的10个日志-->  
    <RollingFile name="debugLog" fileName="${log.path}/debug.log" filePattern="${log.path}/debug-%i.log">  
        <ThresholdFilter level="DEBUG" onMatch="ACCEPT" onMismatch="DENY"/>  
        <PatternLayout pattern="[%-5level][%d{yyyy-MM-dd HH:mm:ss}][%F:%L] - %m%n" />  
        <SizeBasedTriggeringPolicy size="3KB" />  
        <!-- DefaultRolloverStrategy 中的参数max，可以限制 SizeBasedTriggeringPolicy中size超出后，只保留max个存档-->  
        <DefaultRolloverStrategy max="10"/>  
    </RollingFile>  
      
    <!--Appender 3. 输出到滚动保存的文件, 触发保存日志文件的条件是每分钟第一次的日志事件。ERROR日志是按分钟产生日志 -->  
    <RollingFile name="errorLog" fileName="${log.path}/error.log" filePattern="${log.path}/error-%d{yyyy-MM-dd_HH-mm}.log">  
        <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>  
        <PatternLayout pattern="[%-5level][%d{yyyy-MM-dd HH:mm:ss}][%C:%F:%L] - %m%n" />  
        <TimeBasedTriggeringPolicy />  
    </RollingFile>  
      
</appenders>   
  
<loggers>  
    <root level="trace">  
        <appender-ref ref="Console" />  
        <appender-ref ref="debugLog" />  
        <appender-ref ref="errorLog" />  
    </root>   
</loggers>  
</configuration> 
```
## 使用
testLog4j.java 
```
private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

logger.info("aaa")
```

## 加载
1. src的配置文件 
默认
2. 绝对路径下的配置文件
- ConfigurationSource source = new ConfigurationSource(new FileInputStream("D:\\log4j2.xml"))  
- public ConfigurationSource(InputStream stream,File file)
- public configurationSrouce(InputStream stream,URL url)

3. 相对路径下的配置文件 
- 使用getResource()
new ConfigurationSource(new FileInputStream(new File(url.getPath())),ConfigTest.class.getResource(path));
- 使用System.getProperty
new ConfigurationSource(new FileInputStream(System.getProperty("use.dir")+"log4j2.xml"))


# 日志的异步输出
需要加disruptor-3.0.0+
a. 全异步模式  
System.setProperty("Log4jContextSelector","org.apache.logging.log4j.core.async.AsyncloggerContextSelector");  
b. 异步和非异步混合mos
```
<loggers>
    <!--异步-->
    <AsyncLogger name="AsyncLogger" level="trace" includeLocation="true">
        <appender-ref ref="Console"/>
        <appender-ref ref="debugLog"/>
        <appender-ref ref="errorLog"/>
    </AsyncLogger>
    <!--异步-->
    <asyncRoot level="trace" includeLocation="true">
        <appender-ref ref="Console"/>
    </asycRoot>

    <Root levle="info" includeLocation="true">
        <AppenderRef ref="RandomAccessFile/">
    </Root>
</loggers>
```


## 过滤器Filter
配置文件的<configuration>,<loggers>,<appenders>



