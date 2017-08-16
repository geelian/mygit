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

### 1.3.2 Java配置
实现
- @Configuration声明当前类是一个配置类 
- @Bean注解在方法上，声明当前方法的返回值为一个Bean
场景： 
全局配置使用java配置，业务Bean使用注释配置（@Service,@Component,@Repository,@Controller) 
java配置是一个集中化管理的的思路，在不同的地方和系统配置，可以通过它调试。而注释配置的代码本身在一起，集中化需求比较小。

### 1.3.3 AOP面向切面编程
目的：解偶，可以让一组共享相同的行为。
AspecJ的注释式切面编程 
1. 使用Aspect声明一个切面
2. 使用@After,@Before,@Around定义建言（advice),可直接将拦截规则（切点）作为参数
3. 其中@After,@Before,@Around参数的拦截规则为切点（PointCut),为了使切点复用，可使用@PointCut专门定义拦截规则，然后在@After,@Before,@Around的参数中调用
4. 其中符合条件的每一个被拦截处为链接点
基于注释拦截和基于方法拦截
基于注释  
config中@EnableAspectJautoProxy开启Spring对象AspectJ代理的支持 


# 第2章 Spring 常用配置 
介绍
## 2.1 Bean的Scope
@Scope("singleton")默认单例 
@Scope("Prototype")每次新键 

## 2.2 Spring EL和资源调用
包：commons-io
```
@Value("I love you") // 普通字符串
private String normal;

@Value("#{systemProperties['os.name']}") //系统属性
@Value("#{ T(java.lang.Math).random() * 100.0}") //表达式结果
@Value("#{demoService.anther}") //注入其他Bean属性
@Value("classpath:com/.../text.txt") 
@Value("http://www.baidu.com")
@Value("${book.name}")

```

## 2.3 Bean 的初始化和销毁
1) 使用java配置：@Bean的initMethod和destroyMethod 
@Bean(initMethod = "init",destroyMethod = "destroy")
2) 使用注解：JSR-250 @PostConstruct和PreDestroy  


## 2.4 Profile轮廓
作用：不同环境下使用不同的配置提供支持 
1. 通过数组Environment的ActiveProfiles来设置当前context需要的配置环境，在开发中使用@Profile注释或方法，达到不同效果
```
@Bean  
@Profile("dev")

context.getEnironment().setActiveProfiles("dev");//选择
context.register(ProfileConfig.class);//加载
context.refresh();//刷新容器
```
2. jvm设置spring.profiles.active
3. Web在Servlet的context parameter中


## 2.5 事件 Applicaiton Event
功能：Bean与Bean之间的消息通信提供了支持 
流程： 
1. 自定义事件，集成ApplicaitonEvent 
2. 定义事件监听器，实现ApplicationListener 
3. 使用容器发布事件 


# 第3章 Spring 高级话题
- 

## 3.1 Spring Aware
Spring依赖注入的最大亮点所有Bean对Spring容器的存在是没有意识的。可以将容器替换车成别的：Google Guice 
使用Spring Aware,你的Bean将会和Spring框架解耦 
目的：让Bean 获取Spring容器的服务
表3-1 Spring提供的Aware接口 
|--|--|
|BeanNameAware| 获取到容器中Bean的名称|
|BeanFactoryAware| 获取当前bean factory,这样可以调用容器的服务|
|ApplicationContextAware*|当前的application context,....|
|MessageSourceAware| 获取message source,获取文本信息|
|ApplicationEventPublisherAware|引用时间发布器，可以发布事件|
|ResourceLoaderAware|获取资源加载器，可以获取外部资源文件|





