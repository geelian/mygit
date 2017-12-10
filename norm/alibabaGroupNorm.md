# 一 编程规范
## (-) 命名规范
1. 代码命名中不能使用 _ $
> $ 和jvm有关 常量怎么办？ 可以使用

2. 不能使用中英混合 

3. 类名使用 大驼峰 UpperCamelCase 

4. 方法名，参数名，成员变量， 局部变量使用小驼峰 lowerCamelCase

5. 常量用大写，单词使用 _ 分隔 ,力求语义清楚，不要嫌名长    
MAX\_STACK\_COUNT

6. 抽象类以Abstract或Base开头，异常类以Exception结尾 测试类以Test结尾       

7. 中括号是数组的一部分，数组定义 String[] args;
> 不建议使用 String args[]; C语言的形式

8. POJO 普通javaBean 中的布尔类型的变量，不要使用is 框架解析序列号报错

9. 包名小写 一个单词 点分隔

10. 不要使用不规范的缩写 AbstractClass 不要写成 AbsClass

11. 在编程命名是使用完整单词表达意思 PullCodeFromRemoteRepository   

12. 使用设计模式，可以命名体现设计模式OrderFactory

13. 接口类中的的方法和属性不用加任何修饰符（public也不加） 加上 Javadoc注解

14. 接口和实现类

1） Service 和 DAO 类 实现类加Impl 区别 CacheServiceImpl 实现CacheService 接口

2）如果是形容能力的接口名称，取对应的形容词接口名（-able)   
AbstractTranslator 实现 Translatable    


15. 枚举类类名带上Enum 后缀 ，成员全用大写，单词用_ 隔开    
I: 枚举是特殊的常量类，构造方法默认强制私有     

16. 各层包名规范

a) Service/DAO 
    1) 单个对象get  
    2) 多对象 list  
    3) 统计方法 count   
    4) 插入save推荐 insert  
    5) 删除remove推荐 delete    
    6) 修改update前缀   

b)  领域模型命名规范
    1) 数据对象 xxxDO   
    2) 数据传输对象 xxxDTO  
    3) 展示对象 xxxVO   
    4) POJO 是DO/DTO/BO/VO的统称，禁止使用xxxPOJO   


## (二) 常量定义

1. 不允许出现任何魔法值（没有定义的常量）   
 eg: String key="Id#taobao_" + tradeId  

 > 有时候还是要用的变量的key ???
 > String key = "ID#taoboa_" 使用时是 key+tradeId


2. 给Long 或 long 初始化时 用大写L  


3. 不要使用一个常量类维护所有常量，一个按常量功能进行分类，分开维护。CacheConsts ConfigConsts   

4. 常量的复用层次

1) 跨应用共享常量：client.jar 中的constant目录下    
2）应用内共享常量：modules下的constant下    
3）子工程内部共享常量：子工程constant下
4）包内共享常量：包下constant下
5）类内共享常量：private static final 定义

5. 如果变量值仅在一个范围内变化用Enum类，如果还带有名称之外的延伸属性，必须是用Enum列 如果星期


## (三) 格式约定

1. 大括号 {} 内空不换行 
    1) { 前不换行
    2) { 后换行
    3) } 前换行
    4）} else 不换行

2. 左括号和后一个字符之间不出现空格，右括号和前一个字符之间不出现空格

3. if/for/while/switch/do 和括号之间要加空格    

4. 任何运算符左右都加一个空格   
I: 运算符 逻辑运算符 加减乘除 三目运算符    

5. 缩进采用4个空格，禁止tab     

6. 单行字符数限制不超过120个，超出需要换行，原则    
    1)  第二行相对第一行缩进4个空格
    2)  运算符与下文一起换行
    3)  方法调用的点返回和下文一起换行
    4)  在多个参数超长，逗号后换行××    
    5)  在括号前不要换行    

7. 方法参数在定义和传入是，多个参数逗号后边必须加空格   

8. IDE的text file encoding 设置UTF-8; IDE 换行使用Unix格式

9. 没有必要增加若干空格来使某一行的字符与上一行相应字符对齐

10. 方法体内执行语句组、 变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。

## (四）OOP 规约
1. 避免通过一个类的对象引用访问此类的静态变量或静态方法，无畏增加编译器解析成本，直接用类名来访问即可  

2. 所有覆盖方法必须加@Override注解

3. 相同参数类型，相同业务含义，长可以使用java的可变参数，避免使用Object


4. 对外暴露的接口签名，原则上不允许修改方法签名。过时接口必须加@Deprecated

5. 不能使用过时的类或方法

6. Obejct 的equals 方法容易抛出空指针使用 "test".equals(object)

7. 所有的相同类型的包装对象之间值的比较，全部使用equals ***     


I: Integer -128-127被复用==相等 但是后面的不相等 ***


8. 关于基本数据类型与包装数据类型的使用标准 
    1) 所有POJO类属性必须使用包装数据类型
    2) RPC 方法的返回值和参数必须使用包装数据类型
    3) 所有的局部变量使用基本数据类型

9. 定义DO/DTO/VO等POJO类是，不要实战任何属性默认值

10. 序列化类新增属性是，不要修改serialVersionUID字段，避免反序列化失败。

11. 构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在init方法中
> 初始化为0能不能放在这里   

12.  POJO类必须写toString方法，如果继承了另一个POJO类要加super.toString

13. 使用索引访问String的split方法得到的数组是，需要最后一个分隔符后有无内容检查。

14. 当一个类有多个构造方法，或者多个同名方法，这些方法应该按属性放置在一起

15. 类内方法定义顺序依次为 公有方法或保护方法 > 私有方法 > getter/setter 方法  

16. setter 方法中国，参数名称与类成员变量名称一致，this.成员名=参数名，子在getter/setter方法中，尽量不要增加业务逻辑。


17. 循环体内，字符串拼接使用StringBuilder 线程不安全 StringBuffer 安全      

18. final 可提高程序响应效率，情况
    1) 不需要重新赋值的变量，包括类属性，局部变量
    2）对象参数前加final,表示不允许修改引用的指向
    3) 类方法确定不允许被重写

19. 慎用Object的clone 方法来拷贝对象。

20. 类成员与方法访问控制从严

    1) 如果不允许外部直接通过new来创建对象，那么构造方法必须是private
    2) 工具类不允许有public或者default 构造方法
    3) 类非static成员变量并且与子类共享，必须是protected
    4) 类非static成员变量并且仅在本类使用，必须是private
    5) 类非static成员变量并且仅在本类使用，必须是private
    6) 若是static成员变量，必须考虑是否为final
    7) 类成员方法只供类内部调用，必须是private
    8) 类成员方法只对继承类公开，那么选择为protected



## (五) 集合处理

1. 关于hashCode 和equals
    1) 只要重写equals 就必须重写hashCode
    2) Set 存储不重复对象，依据hashCode和equals所以Set存储对象必须重写这2个方法
    3) 自定义对象做为Map的键，必须重写hashCode 和equals     

2. ArrayList的subList 结果不可强转为ArrayList ,抛出ClassCastExceptiion  * 

I: subList 返回的是ArrayList的内部类SubList

3. 在subList场景中，高度注意对原集合元素个数的修改，会导致子列表的遍历、增加、删除抛出ConcurrentModificationException   

4. 使用集合转数组的方法，必须使用集合的toArray(T[] array) 传入的是类型安全一样的数组，大小就是list.size()   

5. 使用工具类Arrays.asList()把数组转换成集合是，不能使用其修改集合相关的方法，他的add/remove/clear抛UnsupportedOperationExcetption  
I: asList 返回是Arrays的内部类，体现的适配器模式，只是转换了接口，后台的数据仍是数组。


6. 泛型通配符\< ? extends T > 来接收返回的数据，此写法的泛型集合不能使用add方法

7. 不要在foreach中使用remove/add    
> 本质使用的是迭代器 没有next是都会判断长度有变化会抛出checkForComodification


8. 在JDK7+ Comparator要满足自反性，传递新，对称性 否则sort抛IllegalArgumentException 异常   
    1) 自反性，x,y 比较结果和y,x 的比较结果相反
    2) 传递性，x>y,y>z 则 x>z
    3) 对称性 x=y 则 x,z 比较和y,z 比较结果相同


9. 集合初始化是，建立指定集合初始值大小

10. 使用entrySet遍历Map集合KV,而部署使用keySet方法。JDK8 Map.foreach()

11. Map k/v 能不能存储null

|集合类|key|value|Super| 说明|
|---|---|--|---|---|
|Hashtable|N|N|Dicitionary|线程安全|
|ConcurrentHashMap|N|N|AbstractMap|分段锁技术|
|TreeMap|N|Y|AbstractMap|不安全|
|HashMap|Y|Y|AbstractMap|不安全|


12. 合理利用好集合的有序性（sort) 和稳定性(order) 避免集合的无序性（unsort) 和不稳定性（unorder)带来的负面影响。

13. 利用Set元素唯一的特效，可以快速的对一个集合进行去重操作，避免使用List的contains方法进行遍历、对比、去重操作。

## (六) 并发处理

1. 或取单例对象需要保证线程安全，其中的方法也要包装线程安全

2. 创建线程或线程池时请指定有意义的线程名称，方便回溯
I: super.setName("")

3. 线程资源必须通过线程池提供，不允许在应用中自行显示创建线程   

4. 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，
    1) FixedThreadPool 和 SingleThreadPool;
    允许的请求队列长度为Integer.MAX_VALUE,可能会堆积大量的请求，而导致OOM
    2) CachedThreadPool 和ScheduledThreadPool
    允许的创建线程数量为Integer.MAX_VALUE,可能会创建大量的线程，而导致OOM

5. SimpleDateFormat 是线程不安全的类，一般不要定义为static变量，如果定义为static,必须加锁，或者使用DateUtils工具类  
线程安全使用
```
private static final ThreadLocal<DateFormat> df = new ThreadLoacl<DateFormal>(){
    @Override
    portected DateFormat initialValue(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }
}
```
N: JKD8的应用，可以使用Instant代替Date,LocalDateTime代替Calender,DateTimeFormatter替代SimpleDateFormart

6.  高并发是，同步调用应该去考量锁的性能损耗。能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体，能用对象锁，就不要用类锁。
N: 尽可能使加锁的代码块工具量尽可能的小，避免在锁代码中调用RPC方法

7.  对多个资源、数据库表、对象同时加速时，需要保存一致性的加锁顺序，否则可能会造成死锁。


8. 并发修改同一记录时，避免更新丢失，需要加锁，要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用version作为更新依据

9. 多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中一个没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService没有这个问题

10. 使用CountDownLatch(计数器)进行异步转同步操作，每个线程退出前必须调用countDown(-1) 方法，线程执行代码注意catch异常，保证countDown方法被执行到，避免主线程无法执行至await方法，直到超时才返回结果。

11. 避免Random实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一seed（随机数种子）导致新下降。
I: jdk7 使用ThreadLocalRandom       

12. 在并发场景下，通过双重检查锁double-checked locking 实现延迟初始化的优化问题，推荐是用最简单的，将目标熟悉声明为volatile型


13. volatile解决多线程内存不可见问题，对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题。 JDK8 使用LongAdder对象

14. HashMap在容量不够进行resize时由于高并发可能出现死锁,导致cpu飙升，在开发过程中可以使用其它数据结构或加锁来规避此风险。

15. ThreadLocal无法解决共享对象的更新问题，ThreadLocal对象建议使用static修饰。这个变量时针对一个线程内所有操作共享的，所以设置为静态变量，所有此类实例共享此静态变量，也就是说在类第一次被使用时装载，只分配一块存储空间，所有此类的对象多可以操控这个变量。


##（七）控制语句

1.  在一个switch块内，每一个case要么通过breadk/return 等来终止，要么注解说明程序将继续执行到哪一个case为止: 在一个switch块内，都必须包含一个default 语句并且放在最后，即使它什么代码也没有


2. 在if/else/for/while/do 语句使用{}

3. 表达异常的分支是，少用if else 写if(){return}

4. 除常用方法（get/is）外，不要在if()中执行复杂的语句，提炼出bool变量 

```
final boolean existed = (...)
if(existed){
}

```

5. 循环体中的语句要考量性能，以下操作尽量移至循环体外处理，如定义对象、变量、获取数据库连接、进行不必要的try-catch操作

6. 接口入参保护，这种场景常见的是用于做批量操作的接口。

7. 要进行参数校验
    1)  调用频次低的方法
    2) 执行时间开销很大的方法。 此情形中，参数校验时间几乎可以忽略不计，但如果因为参数错误导致中间执行回退，或者错误，那得不偿失
    3) 需要极高稳定性和可用性的方法
    4) 对外提供的开放接口 RPC api http
    5) 敏感权限入口

8. 不需要参数校验
    1) 极有可能被循环调用的方法，但在方法说明里必须注明外部参数检查要求
    2) 底层调用频率比较高的方法，
    3) 被声明成private只被自己调用的方法

## （八） 注解规范

1. 类、类属性、类方法的注解必须使用javadoc规范，使用/××××/ 不能使用//××

2. 所有的抽象方法必须使用Javadoc注解、除了返回值、参数、异常说明外，还必须指出该方法做声明事情，实现声明功能。

3. 所有的类都必须添加创建着和创建日期

5. 所有的枚举类型字段必须有注释，说明每个字段的用途

6. 用中文注释清楚（除专有名词）

7. 修改代码 ，修改注释

8. 谨慎注释代码（加说明）

9. 注释要求 1) 能够准确的反应设计思路和代码逻辑 2) 描述业务的含义，了解代码的逻辑

11. 特殊注解
    1) 代办 todo 人时间
    2) 错误 fixme 人时间

## (九) 其他

1. 在使用正则表达式是，利用好其预编译功能，可以加快匹配速度

4. Math.random() 返回的double类型 [0,1) 

5. 使用System.currentTimeMillis() 而不是使用new Date().getTime() JDK8 使用Instant类

6. 不要在视图模板加入任何复杂逻辑

7. 任何数据结构的构造或初始化，都应指定大写，避免数据结构无限制的增长

8. 清理不必要的代码


# 二、异常日志

## (一) 异常处理

1. java类库中定义的一类RuntimeException可以通过预先检查来进行规避，而不应该通过catch来处理。 IndexOutBoundsException NullPointerException 

2. 异常必要用来做流程控制，条件控制，因为异常的处理效率比条件分支低

3. 对大段代码进行try-catch，这是不负责任的表现，catch时请分请稳定代码和非稳定代码，稳定代码指的是无论任何不会出错的代码，对于非稳定代码的catch尽可能进行区分异常类型，再做对应的异常处理。


4. 捕获异常是为了处理它，不要捕获了却什么都不处理而抛弃，不想处理返回给上层服务

5. try块放到了事务代码中，catch异常后，如果需要回滚事务，一定要手动回滚事务。

6. finally 必须对资源对象、流对象进行关闭

7. 不能在finally块中使用return,finally 块中的return返回后方法结束执行，不会再执行try块中的return语句。

8. 捕获异常和抛出异常，必须是完全匹配，或者捕获异常是抛异常的父类


9. 方法的返回值可以为null,不强制返回空集合，或者空对象等，必须添加注解充分说明什么情况下会返回null值，调用需要进行null判断防止NPE问题。


10. 防止NPE,是程序员的基本修养
    1) 返回类型为基本数据类型，return包装数据类型的对象是，自动拆箱有可能产生NPE
    2) 数据库的查询结果可能为null
    3) 集合里的元素即使isNotEmpty，取出的数据元素也可能为null
    4) 远程调用返回对象是，一律要求进行空指针判断，防止NPE
    5) 对于Session中获取的数据，建议NPE检查，避免空指针
    6) 级联调用obj.getA().getB().getC()：容易产生NPE

11. 定义时区分unchecked/checked异常，避免直接抛出new RuntimeException(),更不允许抛出Exception或者Throwable,应使用有业务含义的自定义异常，DAOException/ServiceException 等

12. 在代码中使用"抛异常"还是返回错误码，开发的http/api必须使用错误码，内部应用推荐抛异常，跨RPC调用考虑使用Result方式，封装isSuccess()方法，"错误码"，"错误简短信息";


13. 避免出现重复的代码。

## (二) 日志规约

1.  不能直接使用日志系统Log4j 的API,应该依赖日志框架中的API,使用门面模式的日志框架。

2. 日志保存15d

3. 应用中的扩展日志命名方式
appName_logType_logName.log     

4. 对于trace/debug/info日志输出，必须使用条件输出形式   
if(logger.isDebugEnabled()){}

5. 避免打重复的日志 additivity=false

6. 异常信息应该包括两类信息：案发现场信息和异常堆栈信息，出来了throws抛出

7. 线上不使用debug,使用info

I: 这些日志有人看吗？看日志能干什么？能给排查带来好处吗？


8. warn日志记录参数错误，error日志记录系统逻辑错误、异常等重要的错误信息。



# 三、单元测试

1. 好的单元参数遵守AIR原则（A:automatic I:Independent R:repeatable）

2. 单元参数应该是全自动执行的，并且非交互的。测试框架通常是定期执行的。执行过程必须完全自动化才有意义。输出结果需要人工检查的测试不是一个好的单元测试。单元参数中不能使用System.out人肉检查，必须使用assert来检验。

3. 保持单元测试的独立性，单元测试之间不能相互调用，也不能先后依赖。


4. 可重复执行，不受外界影响。

5. 保证小粒度

6. 核心业务、核心应用、核心模块的增量代码确保单元测试通过

7. 单元测试写在src/test/java下

8. 目标 语句覆盖70% 核心模块的语句覆盖率和分支覆盖100%

9. 编写单元测试代码BCDE原则
    B: Border 边界值测试，包括循环边界、特殊值、特殊时间点、数据顺序
    C: Correct 正确的输入，并得到预期的
    D: Design 与设计文档相结合，来编写单元参数
    E: Error 强制错误信息输入，并得到预期的结果

10. 对于数据库相关的查询，更新，删除等操作，不能假设数据库里的数据是存的，或者直接操作数据库把数据插入进行，请使用程序插入或者导入数据的方式来准备数据。

11. 和数据相关的单元参数，可以设置自动回滚

12. 对不可测试的代码进行重构

13. 设计评审，开发和测试一起确定单元测试范围

14. 项目发布前进行单元测试

15. 单元测试，业务代码的要求
    构造方法中做事情过多
    存在过多的全局变量和静态方法
    存在过多的外部依赖
    存在过多的条件语句

16. 误解
    那是测试同学干的事。文本是开发手册，凡是文本内容都是与开发同学强相关的。
    单元测试代码是多余的。汽车的整体功能与各单元部件的测试正常与否强相关的
    单元测试代码不需要维护，一年半载后单元测试几乎废弃
    单元测试与线上故障没有辨证关系，好的单元测试能够最大限定地规避线上故障


# 四、安全规范

1. 隶属于用户个人的页面或者功能必须进行权限控制校验

2. 用户敏感数据禁止直接展示，必须对展示数据进行脱敏

3. 用户输入的SQL 参数严格使用参数绑定或者METADATA字段值限定，防止SQL注入，禁止拼接SQL访问数据库

4. 用户请求传入参数必须校验
    a. page size 过大导致内存溢出
    b. 恶意order by 导致数据库慢查询
    c. 任意重定向
    e. SQL注入
    f. 反序列化注入
    g. 正则输入源串拒接服务ReDos

5. 禁止向HTML页面输出未经安全过滤或未正确转义的用户数据

6. 表单、AJAX提交必须执行CSRF安全过滤

7. 在使用平台资源，短信，邮件，电话，下单，支付，必须实现正确的防重放限制，如数量限制、疲劳度控制、验证码校验、避免被滥刷、资损

8. 发帖、评论、发送即时消息等用户生成内容的场景必须实现防刷、文本内容违禁词过滤风控策略。


# 五、MYSQL数据库

## (一) 键表规约

1. 表达是否概念的字段，必须使用is_xxx的命名，数据类型 unsigned tinyint 1 是 0 否   
I： id_deleted 1删除 0没有

2. 表名、字段名必须使用小写字母或数字，禁止出现数字开头，禁止两个下划线中间只出现数字。

3. 表名不使用复数名词

4. 禁用保留字 desc、range、match、delayed

5. 主键索引名为pk_字段名；唯一索引名为uk_字段名；普通索引名为idx_字段名。

6. 小数类型为decimal,禁止使用float和double

7. 如果存储的字符串长度几乎相等，使用char

8. varchar 长度不过5000

9. 表必备3个字段 id, gmt_create,gmt_modified ！！

10. 表命名最好是加上"业务名称_表的作用"

11. 库表与应用名称尽量一致

12. 修改字段含义加注释

13. 自段允许适当冗余，
    1) 不是频繁修改的字段
    2) 不是varchar超长字段，更不能是text字段

14. 单表超过500w行容量2GB分库分表

15. 合适的字段存储

## (二) 索引规约

1. 业务上具有唯一特效的字段，计算是多个字段的组合，也必须键成唯一索引。

2. 超过三个表禁止join。需要join的字段，数据类型必须绝对一致：多表关联查询是，保证被关联的字段需要有索引


3. varchar字段上建立索引是，必须指定索引长度，没有必要对全字段建立索引，根据事件文本区分度决定索引长度即可。

4. 页面搜索严禁左模糊或者全模糊，需要使用搜索

5. order by 的有序性 
I: where a=? and b=? order by c; 索引 a_b_c

6. 利用覆盖索引来进行查询操作，避免回表

7. 利用延迟关联或者子查询优化超多分页场景

8. SQL目标 range 小于ref 小于 consts

    1) consts单表 最多只有一个匹配行
    2) ref 使用普通索引
    3）range对索引进行范围检查 

9. 建组合索引的时候，区分度最高的在最左边

10. 防止因字段不同造成的隐时转换，导致索引失效

11. 误解
    1) 宁滥勿缺 认为一个查询一个索引
    2）宁缺毋滥 索引会消耗空间，严重拖慢更新和新增速度
    3)  抵制唯一索引，认为业务的唯一性一律需要在应用通过“先查后查”方式解决。

## （三）SQL语句

1. 不要使用count(列名)或count(常量）来替换count(*) ，count(*)是标准用法

2. count(distinct col) 计算该列除NULL之外的不重复行数。

3. 当某一列全为NULL时，count(col)返回0但是sum(col)返回NULL
    NPE问题：   
    SELECT IF(ISNULL(SUM(g)),0,SUM(g)) FROM table;

4. 使用ISNULL()来判断是否为NULL值
    1) NULL<> NULL 结果是NULL // >
    2) NULL=NULL 结果是NULL
    3) NULL <> 1 结果是 NULL //>

5. 在代码中写分页查询逻辑，若count为0直接返回，避免执行后面的分页语句


6. 不得使用外键级联

7. 禁止使用存储过程

8. 数据订正时，删除和修正记录是，要先select,避免出现误删除，确认无误才能执行更新语句

9. in 操作能避免则避免，若实在避免变量，需要仔细评估in后面的数据 1000内

10. 使用Utf-8

11. truncate table 比 DELETE块 ，但可能造成事故。


## (四) ORM 映射

1. 在表查询中，一律不要使用 * 作为查询的字段列表??加字段麻烦 /(ㄒoㄒ)/


2.  POJO类布尔属性不能加is,而数据库字段必须加is_***

3. 不要用resultClass当返回参数，

4. sql.xml配置使用：#{} #param# 不要使用${} 可能出现sql注入

5. ibatis不推荐使用queryForList()

6. 不允许直接拿HashMap与Hashtable作为查询结果集的输出

7. 更新数据记录是，必须同时更新记录对应的gmt_modified字段为当前时间

8. 不要写一个大而全的数据更新接口

9. @Transactional 事务不能滥用


# 六、工程结构


