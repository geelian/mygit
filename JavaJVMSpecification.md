# 第4章 Class文件格式

## 4.10 Class 文件校验
2种不同的检查策略：类型检查和类型推导
### 4.10.1 类型检查验证

### 4.10.2 类型推导验证

不包含StackMapTable <=49.0 

#### 过程
校验每个方法的code[]    

#### 字节码验证器
1. code[] 指令转载，验证器遍历分析
2. 数据流分析器初始化
3. 数据流分析器运行

#### long和double 
被特殊处理，占2个位置

#### 实例初始化方法与创建对象


#### 异常和finally

<= 50 使用jsr跳到程序子片段 ret程序子片段返回   



## 4.11 java虚拟机限制
1. 类和接口constant\_pool 最大为65535


# 第5章 加载、链接和初始化
1. 加载-根据特定的名称找到类和接口的Class并创建
2. 链接-将类&接口并入jvm运行时状态
3. 初始化-执行类|接口初始化方法< cinit > 

## 5.1 运行时常量池
在方法区中	
1. ("a"+"b"+"c")lintern() == "abc"  true  会指向相同的CONSTANT\_String\_info 结构


## 5.2 虚拟机启动
通过引导类加载器创建一个初始化类来完成(虚拟机指定类)，jvm链接这个类，再初始化main,后main再去链接其他类。


## 5.3 创建和加载
接口|类C的创建由另外一个类|接口D触发的，D引用了C	
加载器：引导类加载器&用户自定义加载器ClassLoader	
JVM运行时，由二进制名称和它定义类加载器共同确定		 	

> 创建标记为N的类或接口C

1. 非数组
-  引导类加载器
- 自定义类加载器
2. N数字类
- 先JVM创建，再类加载器创建

没有加载&初始化类，企图验证|解析这个类，记载抛出ClassNotFoundException，丢抛NoClassDefFoundError    

### 5.3.1 使用引导类加载器来加载类型
创建标记为N的非数组类型类或接口C    
1. 是否加载
2. 通过文件路径找到C
3. 特定算法加载C

### 5.3.2 使用用户自定义加载旗来加载类型
自定义加载器L 加载标记为N的类或接口C
1. 是否加载
2. 使用L加载C

### 5.3.3 创建数组类
类加载器L 加载标记为N的类或接口C
1. 是否存在
2. 引用类型，使用L递归加载和创建C的数组类型
3. Java使用显示的组件类型和数组维度来创建新的的数组类


### 5.3.4 加载限制
> 问题 类型的安全链接问题
当两个不同的类型加载器加载标记为N的类或者接口时，每个加载器中的N表示不同的类|接口   



##  5.5 初始化

> 执行初始化方法

- new getstatic putstatic invokestatic 
- java.lang.invoke.MethodHandler 
- 反射 Class java.lang.reflect
- 子类初始化
- JVM初始化类



> 每个类或接口C,都有一个唯一的初始化锁LC



## 5.6 绑定本地方法实现

使用java之外的语言编写函数集成到JVM的过程，


## 5.7 JVM退出
1. Runtime类halt或System类的exit
2. JNI API加载和卸载JVM




# 第6章 JVM指令集

一条JVM指令由一个团队操作的操作码和0+个操作所使用的操作数构成

## 6.2 保留操作码
2540xfe impdep1 后门    
2550xff impdep2 陷阱
2020xca breakpoint 断点     

## 6.3 JVM错误

VirtualMachineError     
- InternalError 异步异常
- OutOfMemoryError
- StackOverflowError
- UnknowErrror


## 6.4 指令描述格式
aaload  aastroe  aconst\_null 




