 Java EE开发的颠覆者Spring Boot 实战  
原则：习惯优于配置  
# 1.Spring介绍
Ioc容器、AOP、数据访问、Web开发、消息、测试  
### 1.1.2 Spring 概述
1. 核心容器 
Spring-Core: 核心工具类 
Spring-Beans:Spring定义Bean的支持  
Spring-Context:容器  
Spring-Context-Support:第三方包的集成支持  
Spring-Expression:使用表达式语音在运行时查询和操作对象？是不是调试方案  
2. AOP 
Spring-AOP:基于代理的AOP支持   
Spting-Aspects：基于AspectJ的Aop支持  
3. 消息
Spring-Messaging:对消息框架和协议的支持  
4. Web 
Spring-Web:web中提供Spring容器 
Spring-Webmvc:提供基于Servlet的Spring MVC  
Spring-WebSocket:提供WebSocket功能 
Spring-Webmvc-Portlet:通过Portlet环境支持
5. 数据访问/集成
Spring-JDBC: 
Spring-TX:提供编程式和声明式的事务支持 
Spring-ORM:提供对对象/关系映射技术的支持 
Spring-OXM:提供对对象/xml映射技术的支持
Spring-JMS:提供对JMS的支持

#### Spring生态
Spring boot：使用默认开发配置来实现快速开发 

##  1.2 Spring项目快速搭建
### 1.2.1 Maven介绍
管理项目的依赖、编译、文档 
基于项目对象模型Project Object Mode 
### 1.2.3 Maven的pom.xml 
1. dependencies
多个项目依赖
2. dependency 
groupId:组织唯一标识 
artifactId:项目唯一标识 
version:版本
3. 变量定义
```
<properities>
	<spring-framework.version>4.1.5.RELEASE<gspring-framework.version>
</properties>
<dependency>
	<groupId> org.springframwork</groupId>
	<artifactId> spring-webmvc</artifactId>
	<version>${spring-framework.version}</version>
</dependency>
```
4. 编译插件
```
<build>
	<pugins>
		<plugin>
			<groupId> org.apache.maven.plugins</groupId>
			<artifactId> maven-compiler-plugin</artifaceId>
			<version>2.3.2</version>
			<configuration>
				<source>1.7</source>
				<target>1.7</target>
			</configuration>
		</plugin>
	</pugins>
</build>
```


## 1.3 Spring 基础配置
四大原则 
1. 使用POJO进行轻量级和最小侵入式开发
2. 通过依赖注入和基于接口编程实现松耦合
3. 通过AOP和默认习惯进行声明式编程
4. 使用AOP和模板减少模块化代码
### 1.3.1 依赖注入
IOC Inversion of Control 控制反转通过依赖注入实现  
介绍：容器负责创建对象和维护对象间的依赖关系 
目的：解耦,体现组合 
功能：Spring IOC容器（ApplicationContext)负责创建Bean,通过容器将功能类Bean注入到你需要的Bean中 
方式：通过xml,注解，java配置，groovy配置-元配置来进行Bean初始化、配置和管理依赖 
声明Bean的注解：基本等效 
- @Component组件 
- @Service在Service使用
- @Repository在Dao使用
- @Controller表现层使用
注入Bean的注解：基本等效
- @Autowired:Spring提供注解 成员变量自动注入
- @Inject:JSR_330
- @Resource:JSR_250

其他 
@ComponetScan自动扫描包名下的@Service,@Component,@Repository,@Contoller

### Java配置
实现
- @Configuration声明当前类是一个配置类 
- @Bean注解在方法上，声明当前方法的返回值为一个Bean
场景： 
全局配置使用java配置，业务Bean使用注释配置（@Service,@Component,@Repository,@Controller)

