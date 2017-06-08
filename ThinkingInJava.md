# 第1章 对象导论
“我们之所以将自然界分解，组织成各种概念，并按其含义分类，主要是因为我们是整个口语交流社会共同遵守的协定的参与者，这个协定以语言的形式固定下来...除非赞成这个协议中规定的有关语言信息的组织和分类，否则我们根本无法交谈。”  --Benjamin LeeWhorf

面向对象程序设计

## 抽象过程
机器《--抽象-- 汇编 《-- 抽象--- 命令式 

## 面向接口编程
所以类的父类Object

## 封装 继承 多态 复用
封装：隐藏的具体实现
继承：替代原则
多态：默认动态绑定
复用：组合优于继承

## GC
自动控制对象的生命周期

## 异常处理
java内置异常处理


# 第2章 一切都是对象
“如果我们说另一种不同的语言，那么我们就会发觉一个有些不同的世界。” --Luduing Wittgerstein
- 所以的对象都是间接引用
## 必须由你创建对象
inew
### 存储地方
- 寄存器
- 堆栈
- 堆
Time存储清理 > 堆栈
- 常量存储
- 非RAM存储
序列化
### 基本类型
自动装箱
### 数组
会检查越界

## static 
1. 可以用this访问
2. 会自动初始化

## System.out 
系统相关
## 注释
javadoc -d /home/ file.java


# 第3章  操作符
- \>\>\> 零扩展

# 第4章 控制执行流程
就像有知觉的生物一样，程序必须在执行过程中控制它的世界，并做出选择。在java中你要使用执行控制语句来做出选择。
## return 
- 返回值
- 退出
## 臭名昭著的goto
```
label1: //一定要在outer-iteration之上，中间不能有语句
outer-iteration{
	inner-iteratio{
		continue; //(1)
		continue label1; // (2)
		back; //(3)
		break label1; // (4)
	}
}
```
(1).一般continue会退回最内层循环的开头，并继续执行
(2).带标签的continue会到达标签的位置，并重新进入紧接在那个标签后面的循环
(3).一般的break会中断并跳出当前循环
(4).带标签的break会中断并跳出标签所指的循环

## switch
()只能放整数类型，可以考虑用enum

# 第5章 初始化与清理
随着计算机革命的发展，"不安全"的编程方式已逐渐成为编程代价高昂的主因之一。

## 用构造器确保初始化
默认构造器：不接受任何参数的构造器，没有构造函数时自动生成。
## this关键字
- “发生消息给对象”，f(this)被隐藏在第一位中，消息发生给this
- this.pick(),不写this保证高级语言特性
- static函数没有带this

## 清理：终结处理和垃圾回收
1. 对象可能不被垃圾回收
2. 垃圾回收并不等于“析构”
3. 垃圾回收只与内存有关
## 初始化顺序
1）static 变量/块 一次 
1. 当首次生成这个类的一个对象时
2. 或者首次访问属于那个类的静态数据成员时
_ 也就是第一次加载.class文件时static所以成员被初始化一次 _
2）非static初始化 n次
每次new时都是初始化
3）构造函数初始化 n次
## 可变参数列表
public static void f(Object... args){
}
## 枚举类型
public enum Spiciness{
	NOT,MILD,MEDIUM,HOT,FLAMING
}

# 第6章 访问控制权限
"如何把变动的事物与保持不变的事物区分开来"
权限大小顺序：public > protected > 包访问权限 > private
1. 通过继承不能得到包的继承
protedcted 基类的创建者会希望有某个特定成员，把对它的访问权限赋予派生类而不是所有类
2. protected具有包访问权限
3. 默认构造器位public类型
## 类的访问权限
1). 每个文件（编译单元）都只能有一个public类
2). public类的名称必须完全与含有该文件（编译单元)的文件名相匹配
3). 文件（编译单元) 可不带


# 第7章 复用类
复用代码是java众多引入注明的功能之一，但要想成为极具更明显的语言，仅仅能够辅助代码并对之加以改变还是不够的，它还必须能做更多的事情
"is-a是一个" 继承 
"has-a有一个" 组合
## 组合
只需在新的类中产生现在类的对象
## 继承
按照现有类的类型来创建新类
### 初始化顺序
1. 自动从基类开始初始化 
加载类所有的static --\>父类中变量--\>父类构造函数--\>子类中变量--\>子类构造函数
2. 构造函数，调研父类方法super()必须放在第一位，保证上面的顺序 
### 重载
子类可以和父类的方法重载 // ps(C++不支持)
@Override

### 向上转型 ： 具有多态性
新类是现有类的一种类型

## final关键性
### 数据 
作用
1. 一个永不改变的编译是常量
2. 一个在运行是被初始化的值，而你不希望它被改变
空白final：必须在构造器中初始化
final参数：不能改变 
### 方法 
1. 把方法锁定，以防如何继承类修改它的意义（不能继承）
2. 效率（内联）
final&private 类中所有的private方法都隐式地指定为是final的
### 类 
不能被继承

# 第8章　多态
三大特效：多态　继承　数据抽象
目的：消除类型之间的耦合关系
别名：动态绑定、后期绑定、运行时绑定
## 向上转型
对象既可以作为它自己本身的类型使用，也可以作为它的基类型使用。
## 转机
绑定：一个方法调用同一个方法主体关联起来被称作绑定。
前期绑定：程序执行前进行绑定，明确知道用什么方法
后期/动态/运行时绑定：运行时根据对象的类型进行绑定－－>不管怎样都必须在对象中安置某种“类型信息”
> java中除了static方法和final方法(private方法属于final方法)之外，其他方法都是后期绑定。

# 第9章 接口
接口和内部类为我们提供了一种将接口与实现分离的更加结构化的方法
抽象类：普通的类与接口之间的一种中庸之道
## 抽象类和抽象方法
抽象类：本身并不完整 abstract class{}
- 不能实例化
- 可以有实例化方法
抽象方法： abstract void f(); 
- 不完整的
- 只有声明没有方法体
- 如果一个类有>=1 个抽象方法，必须声明为抽象的
- 子类非抽象类必须实现抽象方法
## 接口 interface 
implements 实现
1. 一个完全的抽象类
2. 允许人们通过创建一个能够被向上转型为多种基类的类型，来实现某种类似多重继变种的特性
3. 只有声明
4. 内默认都是public，不能用private和protected，interface默认还是包权限
## 完全解耦
策略模式：创建一个能够根据所传递的参数对象的不同而具有不同行为的方法
适配器模式：将接受你所拥有的接口
## 多继承
- 如果要创建不带任何方法定义和成员变量的基类，那么就应该选择接口而不是抽象类
- 如果知道某事物应该成为一个基类，那么第一选择应该是使它成为一个接口
- 接口可以继承其他接口，不能继承其他类

# 第10章 内部类
可以将一个类的定义放在另一个类的定义内部，这就是内部类 
* 内部类与组合是完全不同的概念 *
```
public class Parcel1{
	class Contents{
	}
}
```
访问外部类：
OutterClassName.InnerClassName  
目的：
> 每个内部类都能独立的继承自一个实现，所以无论外围类是否已经继承了某个实现，对与内部类都没有影响。

> 设计模式总是将变化的事物与保持不变的事物分离

## 链接到外部类
内部类对象可以访问他的外围对象的所有成员，内部类还有外围了的所有元素的访问权
原理
> 当某个外围类的对象创建了一个内部类对象时，此内部类必定会秘密的捕获一个指向那个外围类对象的引用。
> 然后，在你访问此外围类的成员时，就是用那个引用来选择外围类对象的成员
> 有外部类才能有外部类(非static内部类) 
内-》外： OutterClassName.this 
外部建立内部类： OutterClassName.InnerClassName = outterClass.new InnerClassName(); // 必须有外部类outterClass

## 嵌套类（静态内部类static）
* 不需要外部类对象的引用 *
* 普通内部类不能有static数据和static字段但是嵌套类可以
要求：
1. 要创建嵌套类对象，并不需要外围类的对象
2. 不能从嵌套类的对象中访问非静态的外围类对象。
### 接口内部的嵌套类
```
public interface ClassInInterface{
	class Test implementes ClassInInterface{}
}
```
空格


## 在方法和作用域内的内部类
目的
1. 实现了某类型的接口，于是可以创建并返回对其的引用
2. 要决绝一个复杂问题，想创建一个类来辅助你的解决方案，但是又不希望这个类是公共可用的。

## 匿名内部类
内部a类的简化
```
public new Contents()
	return new Contents(){};
}
```
注意：
1. 如果定义一个匿名内部类，并希望它使用一个在其外部的定义的对象，那么编译器会要求其参数引用是final的
2. 匿名内部类即可以扩展类，也可以实现接口，但是太难同时兼备，而且如果是实现接口，也只能实现一个接口
不使理由：需要不止一个内部类对象

## 继承内部类
```
class WithInner{
	class Inner{}
}

class Inner2 extends WithInner.Inner{
	Inner2(WithInner wf){
		wf.super();
	}
}
```
1. 必须带他的外部类到构造函数中 
2. 内部类不能被覆盖


## 闭包和回调
闭包：是一个可调用的对象，它记录了一些信息，这些信息来自于创建它的作用域：内部类是面向对象的闭包

## 内部类标识符
LoclaInnerClass$1.class 匿名的
LoclaInnerClass$LoalCounter.calss

# 第11章 持有对象
如果一个程序只包含固定数量的且其生命周期都是已知的对象，那么这是一个非常简单的程序

## 加入一组元素
collection.AddAll只能接受Collection参数
Arrays.asList|Collections.addAll更灵活，可以接受可变参数
_ 优化是一个很棘手的问题，最好的策略就是置之不理，直达你发现要担心它了 _
## 迭代器
1. 使用方法iterator()要求容器返回一个Iterator
2. 使用next()获取序列中的下一个元素
3. 使用hasNext()检查序列中是否还有元素
4. 使用remove()将迭代器新近返回的元素删除

# 第12章 通过异常处理错误
Java的基本理念是“结构不佳的代码不能运行”
- 对于构件大型、健壮、可维护的程序而言，这种错误处理模式已经成为了主要障碍。
- 异常机制使代码的阅读、编写和调试工作更加井井有条
异常情景：阻止当前方法或作用域继续执行的问题
异常处理程序：将使用new在堆上创建异常对象，然后，当前的执行路径被终止，并且从当前环境中弹出对异常对象的引用，此时异常处理机制接管程序，并开始寻找一个恰当的地方来继续执行程序。
事务：基本保障是我们所需的在分布式计算中的异常处理，事务是计算机中的合同法，如果出了什么问题，我们只需要放弃这个计算。
异常参数：throw new NullPointerException("t = null") // str是信息
监控区域：它是一段可能产生异常的代码，并且后面跟着处理这些异常的代码
_ 被捕获后的代码都可以运行 _
异常处理程序：抛出的异常必须在某处得到处理 catch
try{}
catch(Type1 ){}
finally{}
终止模型：在这种模型中，将假设错误非常关键，以至于程序无法返回到异常发生的地方继续执行。一旦异常被抛出，就表示错误已无法挽回，也不能回来继续执行。
## 创建自定义异常
class MyException extends Exception(){}
### 日志
java.util.logging
static Logger logger = Logger.getLogger("MyLogger");
logger.server(str..)
## 异常说明
void f()throws
可以预留抛出异常的可能
异常代码处理
1. 要么处理这个异常
2. 要么就在异常说明中表明此方法将产生异常

## 捕获异常
Throwable 方法
fillInStackTrace 在异常堆栈中填充异常
getCause
getMessage
getLocalizedMessage
getStackTrace
initCause(Throwable) 将次throwable的cause初始化为指定值
printStackTrace() //输出栈轨迹
setStackTrace
toString

### 程序抛出异常
1. throw e
丢失信息
2. e.fillInStackTrace(); //成为新的异常发生地 
3. throw new Exception(); //抛出新的异常
丢失信息2
1. try{try{}finally{}}catch(){} 2层try
2. try{}finally{return;} return导致

### 异常链
常常会想在捕获一个异常后抛出另一个异常，并希望原始异常的信息保存下来

## java标准异常
Throwable
### 特例 RuntimeException 不受检查的异常
编译器不需要把异常说明，其输出被报告给System.err

## finally进行起来
最终都运行
使用场景：打开文件&网络链接，屏幕上画的图形，开关
finally总回执行就算是在return后


## 异常的限制-- 继承
当覆盖方法的时候，只能抛出在基类方法的异常说明里列出的那些异常。

## 异常匹配
异常处理系统回按照代码的书写顺序找出“最近”的处理程序


# 第13章 字符串
可以证明，字符串操作是计算机程序设计中最常见的行为
难道你真的希望Function()改变器参数吗？
* 对于数学而言z = x + y参数应该是不变的 *
> 除非你用代码将系统实现，并让它动起来，否则你无法真正了解它会有什么问题
## 重载“+”和StringBuilder
String s = "abc" + "mango" + "def" + 47;
= new StringBuilder() .append().append().append().append();

* append("abc" + "mange"); 会先建立StringBuilder完成+再append

## 陷阱--无意识的递归
1. ArrayList.toString会遍历ArrayList中所的对象 
2. toString中使用this+字符串加入死循环
```
public String toString(){
	return "my address" + this + "\n"; 
	// this会递归调用toStirng();
}
```
## String API
方法	参数
构造器	Sting StringBuilder StringBuffer char[] byte[]
length()
charAt()
getChars() getBytes()
toCharArry()
equals() equalsIngnoreCase()
compareTo()
contains()
contentEquals()
regionMatcher()   		段比较
startsWith()
endsWith()
indexOf(),lastIndexOf()
subString(),
concat()			+
replace()			代替
toLowerCase(),toUpperCase()
trim()
valueOf()
intern()			

## 格式输出
System.out.format() = System.out.printf()
### Formatter类 翻译类
(new Formatter(System.out)).format();
同java的大部分IO的功能叠加
格式化语法 %[argument_index$][flags][width].[precision]conversion
(new Formatter(System.out)).format("%-15.15s","--");
## 正则表达式
解决问题：匹配，选择，编辑，验证
String:
.matches("pattern") 验证
.split("pattern") 编辑
.replaceFirst("pattern") .replaceAll("pattern")  替换

Pattern 正则匹配
Matcher 匹配后处理
```
Pattern p = Pattern.compile("pattern");
Matcher m = p.matcher("数据");
```
Pattern:
Pattern.compile("pattern")
Pattern.matcher(regex,input)// 直接对比
.matcher("数据") 生成Matcher
.split(input,3)切分
Matcher:
matches()   | 只能在开始的地方	整个正则匹配才会成功
lookingAt() |			一部分就会成功
find() //多匹配	有组 可以输入任意位置定位正则
find(int start)
> 组是用括号划分的正则表达式，可以根据组的编号类引用某个组。组号为0表示整个表达式，组号1表示被第一对括号括起来的组
```
while(matcher.find()){
	for(int i = 0; i <= m.groupCount();i++){
		m.group(i);
	}
}
```
> 替换
replaceFirst()
replaceAll()
appendReplacement()部分替换
```
whlie(m.find())
	m.appendReplacement(sbuf,m.group().toUpperCase());
```
reset(“数据”) 替换数据

## 扫描输入
1. readLine + split + Interger.parseInt()
2. Scnner(String) 
改匹配
scanner.useDelimiter("\\s*.\\s*");
scanner.hasNextInt()
scanner.nextInt()

正则
while(scanner.hasNext(pattern)){
	scanner.next(pattern);
	MatchResult match = scnner.match();
	match.group(1);
}

# 第14章 类型信息 
运行时类型信息使得你可以在程序运行是发现和使用类型信息
运行是识别对象和类的信息
1. RTTI 它假定我们在编译时已经知道了所有的类型
2. 反射 它允许我们在运行是发现和使用类的信息

## RTTI 运行时识别一个对象的类型
多态：在不同情况下的不同形态
使用RTTI,可以查询某个Shape引用所指向的对象的确切类型，然后选择或者剔除特例
1. 传统的类型转换
2. 代表对象的类型的Class对象
3. instanceof
## Class对象
运行是信息表示：Class对象 
1. 用来建立常规对象
2. java用Class对象来执行其RTTI
A.class ---> Class对象 ---> A对象
		加载		链接	初始化.newInstance();
获取Class对象 

```
Class.forName("A"); //A必须是全限定名
Object.getClass();
A.class;   // 使用类字面常量 
A.getInterface()

```

Class Fun  
Create:  
forName  
newInstance()   
Read:   
desiredAssertionStatus()如果要在调用此方法时将要初始化该类，则返回将分配给该类的断言状态  
getAnnotaion返回注释  
getCanonicalName 返回Java Language Specification中所定义的底层类的规范化名称  
getClasses() Class对象数组  
getClassLoader()类加载器  
getComponentType() 表示数组组件类型的Class  
getConstructor 返后构造方法  
getConstructors  
getDeclaredAnnotations存在此元素上的注解  
getDeclaredClasses 返回类和接口的数组  
getDeclaredConstructor 构造器  
getDeclaredConstructors  
getDeclaredField(name)  
getDeclaredFields()  
getDeclaredMethod(name,parameter)  
getDeclaredMethods  
getDeclaringClass()深层对象  
getEnclosingClass() 返回底层了的立即封闭类  
getEnclosingConstructor()     
getEnclosingMethod()  
getEnumConstans()   
getField(name)  
getFields()  
getGenericInterfaces()   
getGenericSuperclass()  
getInterfaces()  
getMethod(name,parame)  
getMethods()  
getModifiers() //java修饰  
getName()  
getPackage()  
getProtectionDomain()  
getResource()  
getResourceAsStream(name)   
getSigners() //标记  
getSimpleName()  
getSuperClass()    
getTypeParameters()    
isAnnotation()    
isAnnotationPresent(annotationClass)  
isAnonymousClass()  
isArray()  
isAssignableFrom(cls)  
isEnum()  
isInstance(obj)// 是否兼容  
isInterface()  
isLocalClass()  
isMemberClass()  
isPrimitive() //基本类型  
isSynthetic() //复合类型  
toString()  
Update:  
asSubclass(clazz)跟换该Class对象  
cast(obj) 将obj改为这个对象类型  
> getDeclaredField是可以获取一个类的所有字段.  
> getField只能获取类的public 字段.


### 泛型和Class引用
1. 可以限定Class引用的类型
```
Class<Interger> gen;
Class<?> = Class; // 通配符
Class<? extends Number>
Class<? super Nummber>
```
2. Interger Class 对象不是Number Class对象的子类

## instanceof&Class的等价性
instancoef和Class.isInstance() 结果相同  
equals和==也一样
1. instanceof保持了类型的概念，“你是这个类吗|派生类”
2. == 对比的是实际的Class对象

## 反射 
目的
1. java通过JavaBeans提供了基于构件的编程框架
2. 希望通过跨网络的远程平台上创建和运行的能力，RML(远程方法调用)

Class java.lang.reflect Field Method Constructor 类型对象是由JVM在运行是创建的  
对于JVM.class文件要么在本机要么在网络
1. 对于RTTI编译器在编译时打开和检查.class文件
2. 反射，.class在编译时不可获取，在运行是打开和检查.class文件

## 动态代理
```
Class D implements InvocationHandler{
	private Object proxied;
    public D(Object o){
    	proxied = o;
    }
    public Object 
    invoke(Object proxy,Method,Obejct[] args)
    throws Throwable{
    	return method.invoke(proxy,args);
    }    
}
class SimpleDynameProxy{
	public static void main(String [] args){
    	Interface proxy = (Interface) Proxy.newProxyInstance(
        	Interface.class.getClassLoader(),
            new Class[]{Interface.class},
            new D(new RealObject()
        );
    }
}
```
## 空对象代替null

> 没有任何方式可以阻止反射到达并调用那些非公共范围权限的方法
# 第15章



