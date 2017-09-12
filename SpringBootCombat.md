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

## 3.1 Spring Aware
Sparing依赖注入的最大亮点所有Bean对Spring容器的存在是没有意识的。可以将容器替换车成别的：Google Guice 
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
继续以上接口获取资源 


## 3.2 多线程
Spring通过任务执行器TaskExecutor来实现多线程和并发编程  
配置@EnableAsync开启对异步任务的支持，通过Bean的@Async注释来声明它是一个异步任务  


## 3.3 计划任务
配置类注释@EnableSheduling来启动对计划任务的支持  
在要执行计划任务的方法上注释@Scheduled声明这是个计划任务  
支持类型cron,fixDelay,fixRate在Service上
## 3.5 条件注释@Conditional
获取不同的Bean比@Profile更通用  
在配置类@Conditional(LinuxCondition.class)指定命中条件  
方法返回命中接口里面是实现  
## 3.6 组合注释与元注释
元注释：可以注解到别的注解上的注释  
组合注释：被注接的注释  
元注释组成组合注释  

```
@Target(ElementType.TYPE)
@Retantion(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@ComponentScan
public @interface WiselyConfiguration{
	String [] value() default {};
}
```
配置整合  
## @Enable*注解的工作原理
1. 第一部分
@EnableAspectJAutoProxy开启对AspectJ自动代理的支持  
@EnableAsync开启异步方法的支持  
@EnableScheduling开启计划任务的支持  
2. 第二部分
@EnableWebMvc 开启WebMVC的配置支持
3. 第三部分
@EnableConfigurationProperties开启对@ConfigurationProperties注解配置Bean的支持 
@EnableJpaRepositories开启对Spring Data JPA Repository的支持  
@EnableTransactionManagement开启注解式事务的支持  
@EnableCaching开启注解式的缓存支持  



### 第一类：直接导入配置类


```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SchedulingConfiguration.class) // 被导入的配置
@Documented
public @interface EnableScheduling{}
```

###　第二类：依据条件选择配置类
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AsyncConfigurationSalector.class)  // 根据AdviceMode 来设定
public @interface EnableAsync{
	Class<? extends Annotation> annotation() default Annotation.class;
	boolean proxyTargetClass() default false;
	AdviceMode mode() default AdviceMode.PROXY;
	int order() default Ordered.LOWEST_PRECEDENCE;
}
```



### 第三类：动态注册Bean


```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspctJAutoProxyRegistrar.class) // 自动添加Bean到已有的配置类
public @interface EnableAspectJAutoProxy{
	boolean proxyTargetClass() default false;
}
```



## 3.7 测试
Spring TestContex Framework对集成测试提供顶级支持  
基于Maven构建的有src/test/java,src/test/resources测试资源  
SpringJUnit4ClassRunner类提供功能  
通过@ContextConfiguration来配置Application Context,通过@ActiveProfiles确定活动的profile  


# 第二部分 点睛Spring MVC 4.x  


# 第4章 Spring MVC基础 

mvc     

## Spring MVC 快速搭建    

1. @EnableWebMvc 开启一些默认配置    
2. @Controller注明控制器  
3. @RequestMapping配置URL和方法之间的映射   
tomcat部署war  
1. war放入tomcat/webapps  
2. jar 放入lib中  
3. conf/server.xml

```
<Context path="url" docBase=".war" debug="0" privileged="true"/>

```


idea 部署

1. 配置http://localhost:8080/highlight_springmvc4/index  
2. Deployment设置为 .war  


## 4.3 Spring MVC的常用注释
1. @Controler
SpringMVC中的Controller，声明为Spring Bean ,Dispatcher Selvlet会自动扫描注解，并将Web请求元素到@RequestMapping的方法上， Spring MVC声明的控制器Bean的时候，只能使用@Controller   
2. @RequestMapping
映射Web请求、处理类和方法  
3. @ResponseBody 
支持将返回值放在response体内
4. @RequestBody
允许request的参数在request体内
5. @PathVariable
接收路径参数
6. @RestController
@Controller+@ResponseBody  


## 4.4 Spring MVC 基本配置
需要配置类继承WebMvcConfigurerAdapter类，并在此类使用@EnableWebMvc注解 

### 4.4.1 静态资源映射

```


@Configuration
@EnableWebMvc
@ComponentScan("com.wisely.highlight_springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
    }

```

### 4.4.2 拦截器配置
普通Bean实现HanlderInterceptro接口|继承HandlerInterceptorAdapter类来实现自定义拦截器。  
通过WebMvcConfigurerAdapter的addInterceptors方法来注册自定义的拦截器  

### 4.4.3 @ControllerAdvice     
可以将对于控制器的全局配置放置在同一个位置  
@ExceptionHandler用于全局处理控制器里的异常  
@InitBinder:用来设置WebDataBinder,WebDataBinder用来自动绑定前台请求参数到Model中     
@ModelAttribute：本来是绑定键值对到Model里,此处是让全局的@RequestMapping都能获得在此处设置的键值对。    


### 4.4.4 其他配置
1. 快捷的ViewController
重写addViewControllers
```
public void addViewControllers(ViewControllerRegistry registry)
{
    registry.addViewController("/index").setViewName("/index");

}
```

2. 路径匹配参数配置
重写configurePathMatch方法不忽略"."
```
public void configurePathMatch(PathMatchConfigurer configurer){
    configurer.setUseSuffixPatternMatch(false);
}

```

## 4.5 Spring MVC 的高级配置

### 4.5.1 文件上传配置 
使用MultipartFile 

### 4.5.2 自定义HttpMessageConverter
用来处理request和response里的数据

### 4.5.3 服务器推送技术
基于：当客户端向服务器端发送请求，服务器端会抓住这个请求不放，等有时间更新的时候才返回客户端，当客户端接收到消息后，再向服务端发送请求。  
其他技术：双向通信的技术WebSocket   

## 4.6 Spring MVC 的测试
测试驱动开发（ Test Driven Development,TDD)  
JUnit和Spring TestContext frmework  


# 第三部分 实战Spring Boot


# 第5章 Spring Boot 基础


## 5.1 Spring boot 概述

"习惯优于配置"  

### 5.1.2 Spring boot 核心功能
1. 独立运行的Spring项目
java -jar xx.jar
2. 内嵌Servlet容器
可选择内嵌Tomcat，Jetty,Undertow
3. 提供starter简化Maven配置
4. 自动配置Spring
会根据在类路径中的jar包、类，为jar包里的类自动配置Bean,这样会极大的减少我们要使用的配置。
5. 准生产的应用监控
提供基于http ssh telnet对运行时的项目进行监控
6. 无代码生成和xml配置
通过条件注解来实现  
提倡使用Java配置和注解配置组合  

### 5.1.3 Spring Boot 的优缺点
#### 优点
1. 快速构建项目
2. 对主流开发框架的无配置集成
3. 项目可独立运行，无须外部依赖Servlet容器
4. 提供运行是的应用监控
5. 极大的提高了开发、部署效率
6. 与云计算的天然集成

## 5.2 Spring Boot 快速搭建

### 5.2.1 http://start.spring.io
选择web
### 5.2.3 Intellij IDEA
建立Spring Initializr  
选择web   
```
@RestController
@SpringBootApplication //核心配置
public class Ch522Application {
    @RequestMapping("/")
    String index(){
        return "Hello Spring boot";
    }

    public static void main(String [] args){
        SpringApplication.run(Ch522Application.class,args);//启动项目入口
    }
}

```


# 第6章 Spring Boot核心

## 6.1 基本配置

### 6.1.1 入口类和@SpringBootApplication
SpringBoot以×Application的入口类	
```
@Target(ElementType.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@EnableAutoCofiguration  // 让Spring Boot 根据类路径中的jar包依赖为当前项目进行自动配置
@ComponentScan
public @interface SpringBootApplication{
	Class<?> [] exclude() default[];
	String[] excludeName() defautl[];
}
```

### 6.1.2 关闭特定的自动配置
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})

### 6.1.3 定制Banner
在resource下建立banner.txt定制

关闭banner
```
//启动时更改
  SpringApplication application = new SpringApplication(Ch522Application.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

```

### 6.1.4 SpringBoot 的配置文件
支持全局配置文件application.properties | application.yml 放在resources|/config下
```
//file application.properties

server.port=9090
server.context-path=/helloboot
```

### 6.1.5 starter pom
官方好非官方的开始配置

### 6.1.6 使用xml配置
```
@importResource({"classpath:some-context.xml","classpath:another-context.xml"})
```


## 6.2 外部配置
使用properties ,yaml 或命令行作为外部配置

### 6.2.1 命令行参数配置

java -jar xx.jar --server.port=9090

### 6.2.2 常规配置
通过@propertySource指明properties文件的位置，通过@Value注入值


### 6.2.3 类型安全的配置（基于properties）
通过@ConfigurationProperties将properties属性和一个Bean及属性关联



## 6.3 日志配置
配置日志级别
logging.file=D:/log.log
配置日志文件
logging.level.org.springframework.web=DEBUG

## 6.4 Profile配置
针对不同的环境对不同的配置提供支持的，全局Profile配置使用application-{profile}.properties	
通过在application.properties中设置spring.profiles.active=prod来指定活动的Profile	


## Spring Boot 运行原理

源码在spring-boot-autoconfiure.jar中	

1. 运行jar时--debug
2. 在application.properties中设置
debug=true
3. 在STS中设置
### 6.5.1 运行原理
@SpringBootApplication注解的核心功能由@EnableAutoConfiguration注解提供  

```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ EnableAutoConfigurationImportSelector.class,
		AutoConfigurationPackages.Registrar.class
})
public @interface EnableAutoConfiguration{
	Class<?> [] exclude() default{};
	String [] excludeName() default{};
}

```

EnableAutoConfigurationImportSelector使用SpringFactoriesLoader.loadFactoryNames方法扫描具有META/spring.factories文件的jar包

### 6.5.2 核心注解 
条件注解    
@ConditionalOnBean 当容器里有指定的Bean的条件下     
@ConditionalOnClass 当类路径下有指定的类的条件下    
@ConditionalOnExpression基于SpEL表达式为判断条件    
@ConditionalOnJava基于JVM版本作为判断条件   
@ConditionalOnJndi在JNDI存在的条件下查找指定的位置  
@ConditionalOnMissingBean当容器里没有指定Bean的情况下   
@ConditionalOnMissingClass当类路径下没有指定的类的条件下    
@ConditionalOnNotWebApplication:当前项目不是Web项目的条件下     
@ConditionalOnProperty指定的属性是否有指定的值  
@ConditionalOnResource类路径是否哟指定的值  
@ConditionalOnSigleCandidate当指定Bean在容器中有一个，或者多个但是指定首选的bean    
@ConditionalOnWebApplication当前项目Web项目的条件下

```
@Target({ ElementType.Type,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnWebApplicationCondition.class)
public @interface ConditionalOnWebApplicaion{
}
```

注释条件使用的是OnWebApplicationCondition   

是因为代码代替配置      
            







# 第7章 Spring Boot的Web开发    



web开发的核心内容主要包括内嵌Servlet容器和Spring MVC    

## 7.1 Spring Boot 的Web开发支持

spring-boot-starter-web内嵌Tomcat和Spring MVC   
配置在spring-boot-autoconfigure.jar的org.springframework.boot.autoconfigure.web下   

- ServerPropertiesAutoConfiguration和ServerProperties自动配置内嵌Servlet容器
- HttpEncodingAutoConfiguration和HttpEnocodingProperties用来自动配置http编码
- MultipartAutoConfiguration和MultipartProperties用来自动配置mappingJackson2HttpMessageConverter和mappingJackson2XmlHttpMessageConverter;
- WebMvcAutoConfiguration和WebMvcProperties配置SpringMVC

## 7.2 Thymeleaf 模板引擎
Spring boot 支持的模板引擎FreeMarker、Groovy、Thymeleaf、Velocity和Mustache

### 7.2.1 Thymeleaf 基础知识
java类库 xml/xhtml/html5的模板引擎 做mvc的web应用的View层   

1. 引入Thymeleaf
```
<html xmlns:th="http://www.thymeleaf.org"> <!--将镜头转换为动态的视图 进行动态处理用"th:"为前缀-->  
通过@{} 引入Web静态资源
```

2. 访问model中的数据

<span th:text="${siglePerson.name}"> </span>

3. model中的数据迭代

```
<li class="list-group-item" th:each="person:${people}">
    <span th:text="${person.name}"> </span>
    <span th:text="${person.age}"> </span>
</li>
```

4. 数据判断

<div th:if="${not #lists.isEmpty(people)}">     
```
支持>, < , >= <= == !=      
```

5. 在JavaScript中访问model

```
<script th:inline="javascript">
    var single = [[${siglePerson}]];
    console.log(single.name+"/"+sigle.age)
</script>
```

html

```
<li class="list-group-item" th:each="person:${people}">
    <span th:text="${person.name}"></span>
    <span th:text="${person.age}"></span>
    <button class="btn" th:onclick="'getName(\'' + ${person.name} + '\');'"> 获取得名字</button>
</li>
```



###  7.2.2 与Spring MVC 集成
定义ViewResolver    
1. ThymeleafView & ThymeleafViewResolver 为View 
2. SpringTemplateEngine 驱动在SpringMVC下的Thymeleaf模板引擎
3. TemplateResolver 来设置通用的模板引擎


### Spring Boot的Thymeleaf支持

通过org.springframework.boot.autoconfigure.thymeleaf 的ThymeleafAutoConfiguration对Thymeleaf进行自动配置    


## 7.3 Web 相关配置


### 7.3.1 Spring Boot 提供的自动配置

1. 自动配置的ViewResolver
1)  ContentNegotiantionViewResolver代理给不同的ViewResolver来处理不同的View
2)  BeanNameViewResolver    
在控制器(@Controller)中的一个@Map的方法返回字符串，根据这个字符串通过定义一个BeanNameViewResolver的Bean去查找以这个字符串定义的Bean来返回View      
3)  InternalResoureViewResolver     
通过设置前缀，后缀，以及控制器中的方法来返回视图名的字符串，从而得到视图    

2. 自动配置的静态资源   


