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
|-|-|-|-|-|
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

12. 

