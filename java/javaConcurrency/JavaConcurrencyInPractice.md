# 序言

- 提供各种实用的设计规则 

基础知识：2-5       
结构化并运用程序：6-9   
活跃性、性能和测试：10-12   
高级主题:13-16 

# 第1章 简介


## 1.1 并发简史

- 串行编程模型的优势在于其直观性和简单性    

## 1.2 线程的优势

1. 发挥多处理器的强大能力
2. 建模的简单性
3. 异步事件的简化处理
4. 响应更快的用户界面



## 1.3 线程带来的风险

1. 安全性问题
2. 活跃性问题 
死锁 饥饿 活锁
3. 性能问题

## 1.4 线程无处不在




# 第一部分 基础知识

# 第2章 线程安全性

- 要编写线程安全的代码，其核心在于要对状态访问操作进行管理，特别是对共享的和可变的状态的访问。  
- 一个对象是否需要时线程安全的，取决于它是否被多线程访问 

多线程可变的变量没有合适的同步解决  
1. 不在线程之间共享该状态变量
2. 将状态变量修改为不可变的变量
3. 在访问状态变量时使用同步

-  当设计线程安全的类是，良好的面向对象技术、不可修改性，以及明晰的不变性规范都能起到一定的帮助作用
- 最好也只是当性能测试结果和应用需求告诉你必须提高性能，以及测量结果表明这种优化在实际环境中确实能带来性能提升是，才进行优化。


## 2.1 什么是线程安全性

当多个线程访问某个类时，这个类始终都能表现出正确的行为，那么就城这个类是线程安全的。


- 无状态对象一定是线程安全的。（无状态：不包含任何域，也不包含任何对其他类中域的引用）
如Servlet 

## 2.2 原子性

竞争条件：由于不恰当的执行时序而出现不正确的结果是一种非常重要的情况。


### 2.2.1 竞争条件

先检查后执行


- 在实践情况中，应尽可能地使用现有的线程安全对象来管理类的状态。与非线程安全对象相比，判断线程安全对象的可能状态及其状态转换情况要更容易，从而更容易维护和验证线程安全性。


## 2.3 加锁机制

- 要保持状态的一致性，就需要在单个原子操作中更新所有相关的状态变量

### 2.3.1 内置锁
目的：支持原子性    
同步代码块：    
1. 一个作为锁的对象引用，   
2. 作为由这个锁保护的代码块     

```
synchronized(lock){
}
// 块和锁
```
1.  横跨整个方法体的同步代码块，锁就是方法调用所在的对象
2. 静态的方法一Class对象作为锁

定义：内置锁 | 监视锁 ： 每个对象都可以用做一个实现同步的锁。也是互斥锁 最多只有一个线程持有这种锁

### 2.3.2 重入

1. 同线程可以重入已有锁的方法
2. 锁操以线程为丽都，不是调用


## 2.4 用锁来保护状态
每个共享的和可变的变量都应该只由一个锁来保护，从而使维护人员知道是哪一个锁。

## 2.5 活跃性和性能
缩小同步代码块的作用范围，保证并性能。


# 第3章 对象的共享
synchronized 原子性&临界区&内存可见性


## 3.1 可见性


### 3.1.1 失效数据
多线程情况下写入和读取没有同步   ：最低安全性

### 3.1.2 非原子的64位操作

非volatile类型的64位数值变量：long double 64位读写2步操作

### 3.1.3 加锁与可见性
加锁的含义不仅仅局限于互斥行为，还包括内存可见性。为了确保所有线程都能看到共享变量的最新值，所有执行读操作或者写操作的线程多必须在同一个锁上同步。

### 3.1.4 Volatile变量
弱同步机制，确保将变量的更新操作通知到其他线程。    
编译器与运行时都会注意到这个变量时共享的，因此不会将改变量上的操作与其他内存操作一起重新排序。volatile变量不会被缓存在寄存器或者对其他处理器不可见的地方。
I: 仅当volatile变量能简化代码的实现以及同步策略的验证是，才一个使用他们。

I: 加锁机制既可以确保可见性又可以确保原子性，而volatile变量只能确保可见性。
实现原理：
1. 当把变量声明为volatile类型后，编译器与运行时都会注意到这个变量是共享的， 因此不会将该变量上的操作与其他内存操作一起重排序
2. volatile变量不会被缓存在寄存器或者对其他处理器不可见的地方
3. 写入volatitle变量相当与退出同步代码块，读取进入
volatile变量是一种比sychronized 关键字更轻量及的同步机制

> 使用方式：确保它们自身状态的可见性，确保它们所引用对象的状态的可见性，以及标识一些重要的程序生命周期事件的发生

使用规则：
- 对变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值
- 该变量不会与其他状态变量一起纳入不变性条件中
- 在访问变量时不需要加锁

## 3.2 发布与逸出
定义：  
发布publish 使对象能够在当前作用域之外的代码中使用  
逸出Escape 当某个不应该发布的对象被发布时       

> 封装能够使得对程序的正确性进行分析变得可能，并使得无意中破坏设计约束条件变得更难

### 安全的对象构造过程

> 不要在构造过程中使用this引用逸出
如在构造方法中传入对象，通过匿名构造函数将this传出  
因为：在对象向未完全构造之前，新的线程就可以看到它      
```
public class SafeListener{
    private final EventListener listener;
    private SafeListener(){
        listener = new EventListener(){
            public void onEvent(Event e){
                doSomething(e);
            }
        }
    }

    public static SafeListener newInstance(EventSource eventSource){
        SafeListener safe = new SafeListener();
        eventSource.registerListener(safe.listener);
        return safe;
    }
}
```


## 3.3 线程封闭
定义： 如果仅在单线程内访问数据，就不需要同步   
eg:Swing JDBC   

### 3.3.1 Ad-hoc 线程封闭
定义：维护线程封闭性的职责完全由程序实现来承担。    

### 3.3.2 栈封闭
定义：在栈封闭中，只能通过局部变量才能访问对象。

### 3.3.3 ThreadLocal 类
ThreadLocal提供get与set访问接口或方法，这些方法为每一个使用该变量的线程都有一份独立的副本，因此get总是返回当前执行线程在调用set时设置的最新值   
原理：ThreadLocal\<T\> 可视为Map\<Thread,T\> 对象   

ThreadLocal 变量类似于全局变量，它能降低代码的可重用性，并在类之间引入隐含的耦合性，因此在使用时要格外小心。


## 3.4 不可变
定义：如果某个对象在被创建后其状态就不能被修改，那么这个对象就称为不可变对象    
不可表条件是由构造函数创建的 。

> 不可变对象一定是线程安全的。

### 3.4.1 final 域
除非需要某个域是可变的，否则应将其声明为final域。

> 3---4 是不被发布


## 3.5 安全发布

### 3.5.1 不正确的发布：正确的对象被破坏
在未被正确发布的对象中存在2个问题
1. 除了发布对象的线程外，其他线程可以看到Holder域是一个无效值，因此将看到一个空引用之前的旧值
2. 线程看到holder引用的值是最新的，但是状态的值却是旧的

### 3.5.2 不可变对象与初始化安全性

> 任何线程都可以在不需要额外同步的情况下安全访问不可变对象，即使在发布这些对象时没有使用同步。

如果final 类型的域所指向的是可变对象，那么在访问这些域所指向的对象的状态是仍然需要同步

### 3.5.3 安全发布的常用模式

> 在静态初始化函数中初始化一个对象引用
> 将对象的引用保存到volatile类型的域或者AtomicReferance对象中
> 将对象的引用保存到某个正确构造对象的final类型域中
> 将对象的引用保存到一个由锁保护的域中

_线程安全类_
1. Hashtable synchronizedMap ConcurrentMap
2. Vector CopyOnWriteArrayList CopyOnWriteArraySet synchronizedList synchronizedSet 
3. BlockingQueue ConcurrentLinkedQueue
4. public static Object o  = new Object();

### 3.5.4 事实不可变对象
### 3.5.5 可变对象
1. 不可变对象任意
2. 事实不可变，安全方式发布
3. 可变对象安全发布，加锁保护

### 3.5.6 安全的共享对象

策略
1. 线程封闭
2. 只读共享
3. 线程安全共享
4. 保护对象





# 第4章 对象的组合

介绍一些组合模式，使一个类更容易成为线程安全的。

## 4.1 设计线程安全的类 

基本要素
1. 找出构成对象状态的所有变量
2. 找出约束状态变量的不变性条件 
3. 建立对象状态的并发访问管理策略   

同步策略 Synchronization Policy 定义如何在不违背对象不变条件或后验条件的情况下对其状态的访问操作进行协同 。

### 4.1.1 收集同步需求

如果一个不变性条件包含多个变量，那么在执行任何访问相关变量的操作时，都必须有保护这些变量的锁

> 如果不了解对象的不变性条件与后验条件，那么就不能确保线程安全性。要满足在状态变量的有效值或状态转换上的各种约束条件，就需要借助原子性与封装性。 

### 4.1.2 依赖状态的操作    
先验证条件再操作 等待和通知 
想要实现等待先验条件为真时执行的操作，通内库阻塞队列，信号量 阻塞类BlockingQueue Semaphore      
### 4.1.3 状态的所有权
I: 对象封装它拥有的状态, 对象对它封装的状态拥有所有权   


## 4.2 实例封闭
定义：当一个对象封装都另一个对象中时，能够访问被封装对象的所有代码路径都是已知的。
> 将数据封装在对象内部，可以将数据的访问限制在对象的方法中，从而更容易确保线程在访问数据时总能持有正确的锁

M:synchronized(this) 方法 体现的是内置锁 	 synchronized(Object) 锁对象

使用包装工厂Collections.synchronizedList将   ArrayList转为线程安全的类 ---修饰器模式 将容器封装在一个同步的包装容器对象中，而包装器能将接口中的每一个方法都实现为同步方法，并将调用请求转发到底层的容器对象上。	
ia


实现片段  

```
static class SynchronizedCollection<E> implements Collection<E>, Serializable {
        private static final long serialVersionUID = 3053995032091335093L;

        final Collection<E> c;  // Backing Collection
        final Object mutex;     // Object on which to synchronize

        SynchronizedCollection(Collection<E> c) {
            this.c = Objects.requireNonNull(c);
            mutex = this;
        }

        SynchronizedCollection(Collection<E> c, Object mutex) {
            this.c = Objects.requireNonNull(c);
            this.mutex = Objects.requireNonNull(mutex);
        }

        public int size() {
	    // 每次同步锁点一个对象来操作
            synchronized (mutex) {return c.size();}
        }
}
```

### 4.2.1 java监视器模式
实现类Vector 和HashTable M：jdk1.8 没有看到实现？？？			
通过一个私有锁来保护状态	
```
public class PrivateLock{
	private final Object myLock = new Object();
	@GuardedBy("myLock") Widget widget;
	void someMethod(){
		synchronized(myLock) { // 私有锁
			// 访问或者修改Widget的状态
		}
		
	}
}
```

优点	
1. 私有的锁对象可以封装


## 4.3 线程安全性的委托


### 4.3.4 发布底层的状态变量

> 如果一个状态变量是线程安全的，并且没有任何不变性条件来约束它的值，在变量的操作上也不存在任何不允许的状态转换，那么就可以安全的发布这个变量    



## 4.4 在现有的线程安全类中添加功能

> 重用能降低开发工作量，开发风险以及维护成本

1. 扩展线程安全类

```
public class BetterVerctor<E> extends Vector<E>{
    public synchronized boolean putIfAbsent(E x){
        boolean absent = !contains(x);
        if(absent)
            add(x);
        return absent;
    }
}
```

2. 客户端加锁

```
public class ListHelper<E>{
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x){ // 为什么不是ListHelper上的锁有线程安全问题
        boolean absent = !list.contains(x);
        if(absent)
            add(x);
        return absent;
    }

}
```


使用对象锁
```
public class ListHelper<E>{
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());
    
    public  boolean putIfAbsent(E x){ // 为什么不是ListHelper上的锁有线程安全问题
        synchronized(list){
            boolean absent = !list.contains(x);
            if(absent)
                add(x);
            return absent;
        }
    }
```

3. 组合模式

同java监视器模式    

```
public class ListHelper<T> implements List<T>{
    public final List<T> list ;
        
    public ListHelper(List<T> list){this.list = list;}

    public synchronized boolean putIfAbsent(E x){ // 为什么不是ListHelper上的锁有线程安全问题
        boolean absent = !list.contains(x);
        if(absent)
            add(x);
        return absent;
    }
```

## 4.5 将同步策略文档化



# 第5章 基础构建模块

介绍j5 j6 引入的新并发构建模块。常用的模式  

## 5.1 同步容器类

Vector 和Hashtable JDK1.2 加的由Collections.synchronizedXxx等工厂方法创建的。   
实现方式：将它们的状态封装起来，并对每个公有方法都进行同步，使得每次只有一个线程能访问容器的状态

### 5.1.1 同步容器的问题

1. 容器复合操作（迭代 跳转 条件运算)  需要客户端加锁同步        
对Vector加锁    
```
public static Object getLast(Vector list){
    synchronized(list){
        int lastIndex = list.size() - 1;
        return list.get(listIndex);
    }
}
```

### 5.1.2 迭代器与ConcurrentModificationException

ConcurrentModificationException容器再迭代过程中被修改时。   
实现将计数器的变化与容器关联起来：如果在迭代期间计数器被修改，那么hasNext或next将抛出ConcurrentMondificationException
```
for(Long l: longList){
            longList.remove(1);
            System.out.println(i++);
        }

// 反编译效果
  Iterator var3 = longList.iterator();

        while(var3.hasNext()) {
            Long l = (Long)var3.next();
            longList.remove(1);
            System.out.println(i++);
        }
```

### 5.1.3 隐藏迭代器

集合的toString使用迭代

AbstractCollection 

```
    /**
     * Returns a string representation of this collection.  The string
     * representation consists of a list of the collection's elements in the
     * order they are returned by its iterator, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space).  Elements are converted to strings as
     * by {@link String#valueOf(Object)}.
     *
     * @return a string representation of this collection '
     */
    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
```

内部的tostring引起的迭代循环    

## 5.2 并发容器

1. 同步容器 
1） vector Hashtable 使用内置锁     
2)  synchronizedXXX 对类型进行封装  
对容器状态的访问串行化了    

2. 并发容器
ConcurrentHashMap ConcurrentSkipListMap     
分段锁 ：大锁拆小锁 

锁住每一个节点  

CopyOnWriteArrayList 


> 通过并发容器来代替同步容器，可以极大地提高伸缩性并降低风险

J5 Quere BlockingQueue 实现ConcurrentLinkedQueue PriorityQueue  

ConcurrentSkipListMap 和ConcurrentSkipListSet 替代SortedMap 和SortedSet     

### 5.2.1 ConcurrentHashMap
在并发访问环境下将实现更高的吞吐量，而在单线程环境中只损失非常小的性能。    


### 5.2.2 额外的原子Map操作

部分复合操作 在ConcurrentMap接口中存现  

putIfAbsent remove replace 


### 5.2.3 CopyOnWriteArrayList 
写入时复制Copy-On-Write 在每次修改时，都会创建并重新发布一个新的容器副本，从而实现可变性。



## 5.3 阻塞队列和生产者-消费者模式  


