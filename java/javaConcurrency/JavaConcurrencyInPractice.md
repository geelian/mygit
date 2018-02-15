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
阻塞队列提供可阻塞的put和take 定时的offer poll  ,可选择有界和无界。     
生产者-消费者模式简化开发过程，消除生产者和消费者之间的代码依赖性，生产和处理数解耦     
BlockingQueue 实现，

> 在构建高可靠的应用程序时，有界队列是一种强大的资源管理工具：它们能抑制并防止生产过多的工作项，使应用程序在负荷过载的情况下变得更加健壮。  

BlockingQueue实现 LinkedBlockingQueue和ArrayBlockingQueue PriorityBlockingQueued堆   SynchronousQueue维护一组线程，每一个存储功能


Executor也是生产者消费者的模式  


### 5.3.2 串行线程封闭
可变对象从A--B 对象的所有权转移     


### 5.3.3 双端队列与工作密取
J6 增加 Deque和BlockingDeque  对Queue和BlockingQueue扩展    
Deque实现ArrayDeque LinkedBlockingDeque     


## 5.4 阻塞方法与中断方法
    
线程被阻塞或暂停：等待IO 锁 Thread.sleep 另一线程计算结果 。        
当方法抛出InterruptedException该方法是阻塞方法。        

Thread.interrupt()中断或者.interrupted()查询被中断 // 命名注意 动词为操作 完成时为是否 线程中标志位表示是否被中断   
A Thread.interrupt() --> B .interrupted()  变为 true    如果在运行Thread.sleep Thread.join Obejct.wait .. 阻塞方法 会抛出 InterruptedException      
InterruptedException处理方法：  
1. 传递InterruptedException 向上抛
2. 恢复中断  在Runnable中调用Thread.interrupt() 中断本身    



## 5.5 同步工具类
阻塞队列：保存对象容器，协调生产者和消费者线程之间的控制流。    
同步工具类：封装一些状态，状态决定执行同步工具类的线程是继续执行还是等待，提供对状态操作的方法，用于高效等待同步工具类进入到预期状态。  
信号量Semaphore 栅栏Barrier 闭锁 Latch 

### 5.5.1 闭锁  
功能：延迟线程的进度直到其到达终止状态。    

实现：CountDownLatch 状态包含一个计数器，是个正数 到0发生   
countDown - await 等待为0 
```
public long timeTasks(int nThreads,final Runnable task)
    throws InterruptedException {
    //起始门
    final CountDownLatch startGate = new CountDownLatch(1);
    //结束门
    final CountDownLatch endGate = new CountDownLatch(nThreads);

    for(int i = 0; i < nThreads ; i++){
        Thread t = new Thread(){
            public void run(){
                try{
                    startGate.await();
                    try{
                        tast.run();
                    }finally{
                        endGate.countDown();
                    }
                }catch (InterruptedException ignored){}
            }
        };
        t.start();
    }

    long start = System.nanoTime();
    startGate.countDown();
    endGate.await();
    long end = System.nanoTime();
    return end - start;

}
```

### 5.5.2 FutureTask

可以用做闭锁，实现Future抽象的可以生成结果的计算    异步计算的结果
Future.get() 阻塞等待或者是抛异常 结果   


### 5.5.3 信号量
用来控制同时访问某个特定资源的操作数量，同时执行某个指定操作的数量。 资源池 容器施加边界。      
Semaphore sem(n);   sem.acquire()获取许可 sem.release()  释放许可


### 5.5.4 栅栏
阻塞一组线程直到某个事件发生，
vs 闭锁 所以线程必须同时到达栅栏位置，才能继续执行。闭锁用于等待事件，而栅栏用于等待其他线程。  
用途定义协议    
CyclicBarrier 反复在栅栏位置汇集。

|--|-用处-|-eg-|--|
|闭锁|等待事件||CountDownLatch|
|FutureTask|生成结果|||
|信号量|资源控制|资源池|Semaphore|
|栅栏|等待其他线程|分布式计算|CyclicBarrier|



## 5.6 构建高效且可伸缩的结果缓存

```
public class Memoizer<A,V> implements Computable<A,V>{
    private final ConcurrentMap<A,Future<V>> cache
        = new ConcurrentMap<A,Future<V> >();
    private final Computable<A,V> c;

    public Memoizer(Computable<A,V> c) {this.c=c;}

    public V compute(final A arg) throws InterruptedException{
        while(true){
            Future<V> f =  cache.get(arg);
            if (f == null){
                Callable<V> eval = new Callable<V>() {
                    public V call() thows InterruptedException{
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V> (eval);
                f = 
            }
        }
    }
}
```





# 第二部分 结构化并发应用程序

#  第6章 执行任务

任务通常是一些抽象的且离散的工作单元    


## 6.1 在线程中执行任务     

### 1. 串行执行 
每次只处理有关用户请求

### 2. 显示创建线程
- 处理与接收分开 
- 任务并行处理
- 任务处理代码线程安全

### 3. 无限制创建线程的不足
2 的问题    
- 线程生命周期的开销非常高 
- 资源消耗
- 稳定性 


## 6.2 Executor 框架
串行执行的问题在于其糟糕的响应性和吞吐量，而“为每个任务分配一个线程”的问题

java.util.concurrent 提供线程池实现框架 Executor 
```
public interface Executor{
    void execute(Runnable command);
}
```
功能： 
1. 将任务提交和执行分离  用Runnable执行任务
2. 支持生命周期
3. 统计信息收集，应用程序管理机制和性能监控机制。

基于生产者者消费者： 提交任务-生产者 执行任务-消费者  

> 每一个请求建立一个新线程的 Executor实现 
```
public class ThreadPerTaskExecutor implements Executor{
    public void execute(Runnable r){
        new Thread(r).start();
    }
}
```

### 6.2.2 执行策略

what where when how   
每当看到new Thread(runnable).start(); 并且你希望获得一种更灵活的执行策略时，可以考虑用Executor替代Thread


### 6.2.3 线程池
与工作队列密切相关  
任务：从工作队列中获取一个任务，执行任务，然后返回线程池并等待下一个任务。  

优点
1. 减少创建和销毁过程中产生的开销   
2. 不等待创建线程时间   
实例
- newFixedThreadPool 固定线程池
- newCachedThreadPool 可缓存的线程池  60s 
- newSingleThreadExecutor 单线程的线程池，死重新建立一个 顺序执行
- newScheduledThreadPool 固定，推迟|定时执行任务
- newFixedThreadPool 和 newCachedThreadPool 工厂通用ThreadPoolExecutor实例。
- TaskExecutionWebServer web服务器使用一个带有有界线程池的Executor.

### 6.2.4 Executor的生命周期
平稳关闭：所有都完成，且不在接受任务    
接口ExecutorService 扩展Executor 接口：解决执行服务的生命周期问题。
```
public interface ExecutorService extends Executor{
    void shotdown(); // 平稳关闭
    List<Runnable> showdownNow(); // 粗暴关闭
    boolean isShutdown();
    boolean isTerminated();
    boolean awaitTermination(long timeout,TimeUnit unit) throw InterruptedException; 
}
```

状态：运行、关闭、已终止。


### 6.2.5 延迟任务与周期任务 
Timer 管理延迟任务以及周期任务。可以考虑使用ScheduledThreadPoolExecutor来代替它。   
Timer 问题
1. 一个线程 长时间执行破坏TimerTask精准性 
2. TimerTask 抛出未检查的异常是将终止定时线程。这个都被取消。


## 6.3找出可利用的并行性。


### 6.3.2 携带结果的任务Callable与Future
Runnable 局限性不能返回一个值或抛出一个受检查的异常
Callable: 主入口点Call将返回一个值，并可能抛出一个异常。  
Future：表示一个任务的生命周期，提供相应的方法来判断是否已经完成或取消，以及获取任务的结果和取消任务。只能进不能退。

```
public interface Callable<V>{
    V call() throws Exception;
}

public interface Future<V>{
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean idDone();
    V get() throws InterruptedException,ExecutionExcepiton,CancellationException;
    V get(long timeout,TimeUnit unit) throws InterruptedException,ExecutionException,CancellationException,TimeoutExcepiton;
}
```
ExecutorService 中的submit方法将返回Futura，将Runnable或Callable提交给Executor,得到

ThreadPoolExecutor 中newTaskFor的默认实现 
```
protected <T> RunnableRuture<T> newTaskFor(Callable<T>  task){
    return new FutureTask<T>(task);
}
```

eg
```
public class PutureRenderer{
    private final ExecutorService executor = ....;

    void readerPage(CharSequence source){
        final List<ImageInfo>  imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = 
            new Callable<List<ImageData>>(){
                public List<ImageData> call(){
                    List<ImageData> result 
                        = new ArrayList<ImageData>();
                    for(ImageInfo imageInfo:imageInfos){
                        result.add(imageInfo.downloadImage());
                        
                    }
                    return result;
                }
            };
        Future<List<ImageData>> future = executor.submit(task);
        renderText(source);
        try{
            List<ImageData> imageData = future.get();
            for(ImageData data:imageData){
                rederImage(data);
            }
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
            future.cancel(true);
        } catch (ExecutionException e){
            throw launderThrowable(e.getCause());
        }
    }
}
```


### 6.3.5 CompletionService:Executor  与 BlockingQueue
完成服务（CompletionService) 将 Executor 和 BlockingQueue 功能融合在一起。  
ExecutorCompletionService实现了CompletionService,并将计算部分委托一个Executor。     
实现： 构造函数创建BlockingQueue来保存计算结果。  完成时Future-Task中的done方法，提交任务包装为QueueingFuture ，然后再改写子类done方法。并将结果放入BlockingQueue中。take poll 委托给BlockingQueue  


### 6.3.7 为任务设置时限
Future.get(timeLeft,NANOSECONDS)
invokeAll 定时，返回保持状态和结果的Future列表。


# 第7章 取消与关闭

中断Interruption 协作机制，使一个线程终止另一个线程 

1. 实现取消和中断的机制
2. 编写任务和服务，对取消请求做出响应

## 7.1 任务取消
外部操作在正常完成之前置为“完成状态”，操作称为可取消的Cancellable   
- 用户请求取消
- 有时间限制的操作
- 应用程序事件  
- 错误
- 关闭 

java没有安全的抢占式方法来停止线程。使请求取消的任务和代码都遵循一种协商好的协议 。--- 通知标志位取消       

### 7.1.1 中断

通知标志位取消问题：阻塞不会到标志位，不能结束。    

每个线程都有一个boolean类型的中断状态。(在结构里面) 当中断线程是，这个线程的中断状态将被设置为true.
interrupt() 中断目标线程 isInterrupted目标线程中断状态  interrupted查询&去除中断 
```
public class Thread{
    public void interrupt(){}
    public boolean isInterrupted(){}
    public static boolean interrupted(){}
}
```
阻塞库方法，Thread.sleep Object.wait 检查线程何时中断，发现中断提前返回-> 清除中断状态，抛出InterruptedException,表示阻塞操作由于中断而提前结束。   
- 有阻塞方法interrupt才有意义 -
> 调用interrupted并不意味着立即停止目标线程正在进行的工作，而只是传递了请求中断的消息。

### 7.1.2 中断策略

线程如何解释某个中断请求---发现中断请求是，应该做哪些工作，哪些工作单元对应中断来说是原子操作，以及以多快的速度来响应中断。

线程级取消服务级取消：尽快推出，在必要时进行清理，通知某个所在者该线程已经退出。    

由于每个线程拥有各自的中断策略，因此除非你知道中断对该线程的含义，否则就不应该中断这个线程。


### 7.1.3 响应中断

调用可中断函数Thread.sleep BlockingQueue.put 中断抛InterruptedException  
处理方法：
1. 传递异常 eg f() throws InterruptedException{}
2. 恢复中断状态  eg try{} catch(InterruptedException e){}

只是实现了线程中断策略的代码才可以屏蔽中断请求。在常规的任务和库代码中都不应该屏蔽中断请求。    

### 7.1.5 通过Future来实现取消(计划运行)
管理任务的生命周期，处理异常，以及实现取消。    

取消那些不再需要结果的任务。

```
public static void timedRun(Runnable r,
        long timeout,TimeUtilt unit) throws InterruptedException{
    Future<?> task = taskExec.submit(r);
    try{
        task.get(timeout,unit);

    }catch(TimeoutException e){
        // 任务将被取消
    }catch(ExecutionException e){
        throw launderThrowable(e.getCause());
    }finally{
        task.cancel(true);
    }

}
```


### 7.1.6 处理不可中断的阻塞

使用类似于中断的手段来停止这些线程，
1. Java.io包中的同步Socket I/O 通过关闭底层套接字，read | write被阻塞线程抛出一个SocketException
2. Java.io包中的同步I/O  中断一个正在InterruptibleChannel上等待的线程是，将抛出ClosedByInterruptException并关闭链路
3. Selector的异步I/O 【数据是否立即返回】如果一个线程在调用Selector.select方法时阻塞了，那么调用close或wakeup方法会使线程抛出ClosedSelectorException并提前返回。    
4. 获取某个锁。等待某个内置锁而阻塞，无法响应中断，特例Lock类提供了lockInterruptibly方法允许在等待一个锁的同事仍然响应中断。    
```
public class ReaderThread extends Thread{
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException{
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt(){
        try{
            socket.close();
        }catch(IOException ignored){

        }finally{
            super.interrupt();
        }
    }

    public void run(){
        try{
            byte[] buf = new byte[BUFSE];
            while(true){
                int count = in.read(buf);
                if(count < 0){
                    break;
                }else if(count > 0){
                    processBuffer(buf,count);
                }
            }
        }catch(IOException e){}
    }
}
```

### 7.1.7 采用newTaskFor 来封装非标准的取消
把一个Callable提交给ExecutorService时，submit方法会返回一个Future,我们可以通过这个Future来取消任务。


## 7.2 停止基于线程的服务 
封装原则：除非拥有某个线程，否则不能对该线程进行操控    
对于持有线程的服务，只要服务的存在时间大于创建线程的方法的存在时间，那么就应该提供生命周期方法。


### 日志服务
```
public class LogService{
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    @GuarderBy("this") private boolean isShutdown;
    @GuarderBy("this") private int reservations;

    public void start() {loggerThread.start();}

    public void stop(){
        synchronized(this){ isShutdown = ture;}
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException{
        synchronized(this){
            if(isShutdown){
                throw new IllegalStateException(....);
            }
            ++reservations;
        }
        queue.put(msg);
    }

    private class LoggerThread extends Thread{
        public void run(){
            try{
                whlie(true){
                    try{
                        synchronized(LogService.class){
                            if(isShutdown && reservations == 0){
                                break;
                            }
                            String msg = queue.take();
                            synchronized (LogService.this){--reservations;}
                            writer.println(msg);
                        } catch(InterruptedException e){}
                    }
                } finally {
                    writer.close();
                }
            }
        }
    }
}
```


### 7.2.3 “毒丸” 对象
Poison Pill ：当得到这个对象时，立即停止。队列中

### 7.2.5 shutdownNow局限性

我们无法通过常规方法找出那些认为是开始但是没有结束的：中间状态   
使用 try{}finally{ if(isShutDown() && Thread.currentThread().isInterrupted()){ 加入到list中}} 来记录中断状态的线程。


## 7.3 处理非正常的线程终止

默认线程的异常不会上传到父线程 默认输出到System.err 中
方法1. try{}catch(){}
```
public void run(){
    Throwable thrown = null; // 所有异常的父类
    try{
        while(!isInterrupted()){
            runTask(getTaskFromWorkQueue());
        }
    } catch(Throwable e){
        thrown = e;
    }finally{
        threadExited(this,thrown);
    }
}
```

方法2 设置未捕获异常处理函数 Thread.UncaughtExceptionHandler 接口   
默认为system.err
1. 一般线程使用 
thread.setUncaughtExceptionHandler(new ***);    
2. 线程池   

- Executer.execute(thread) 要在run中使用
Thread.currentThread().setUncaughtExceptionHandler(new ***);

- future = ExecuterService.submit(thread) 所有异常都被认为是任务返回状态的一部分，Future.get() 抛出 。


## 7.4 JVM关闭

###  关闭钩子 
使用实例
```
 class HookTest {
    public void start(){
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Execute Hook ...");
            }
        }));
    }

    public static void main(String [] args){
        new HookTest().start();
        System.out.println("lggg  ");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


```
场景 
1. 程序正常退出
2. 使用System.exit()
3. 终端使用Ctrl+C触发中断
4. 系统关闭
5. OutOfMemory宕机
6. 使用Kill pid 命令干掉进程 kill -9 pid 不会

### 守护进程
JVM 停止还存在的守护进程将被抛弃

### 终结器
finalize 避免使用
垃圾回收器对那些定义了finalize方法的对象进行特殊处理：在回收器释放它们后，调用它们的finalize方法，释放资源 不建议使用 建议使用 finally关闭 。


# 第8章 线程池的使用
主要是配置和调优 

## 在任务与执行策略之间的隐性耦合 -- 需要定制化 ThreadPoolExecutor  
1. 依赖性任务
2. 使用线程封闭机制的任务
3. 对响应时间的灵敏任务
4. 使用ThreadLocal的任务 

线程饥饿死锁: 如果所有正在执行任务的线程都由于等待其他仍处于工作队列中的任务而阻塞，那么会发生同样的问题 。     

## 8.2 设置线程池的大小

1. 线程数 = cpu 数 * 目标CPU利用率 * ( 1 + 任务的等待是/ 计算时间)  
2. 用该资源的可用总量除以每个任务的需求量

## 8.3 配置定制化的 ThreadPoolExecutor 

```
public ThreadPoolExecutor(
            int corePoolSize,
            int maximumPoolSize, // 超量被销毁
            long keepAliveTime,  
            TimeUnit unit, // 创建销毁  超时被销毁 newFixedThreadPool 线程池的基本大小和最大大小守则为参数中的值 
                            //  new CachedThreadPool 线程池最大为Integer.MAX_VALUE 基本大小设置为0 
            BlockingQueue<Runnable> workQueue, // 管理队列 1无界 2. 有界 3. 同步移交 SynchronousQueue
            ThreadFactory threadFactory, // 线程工厂
            RejectedExecutionHandler handler // 饱和策略 AbortPolicy终止 CallerRunsPolicy 回退调用者 DiscardPolicy拒接 DiscardOldestPolicy
        ){}
```

构造后还能在设置这些属性 


## 8.4 扩展 ThreadPoolExecutor
1. beforeExecute 线程前置处理
2. afterExecute
3. terminated 线程池完成时处理 


# 第三部分 活跃性、性能与测试 
# 第10章 避免活跃性危险性

## 10.1 死锁

定义：每个人都拥有其他人需要的资源，同时又等待其他人已经拥有的资源，而且每个人在获取所有需要的资源之前都不会放弃已经拥有的资源。

产生原因 
1. 竞争资源 (资源池和线程饥饿死锁)
2. 进程的推进顺序不当-- 使用开放调用（在调用某个方法时不需要持有锁 ，缩小synchronized范围）

必要条件： 互斥条件 + 请求和保存 + 不可剥夺 + 环路等待 --> 死锁 

解决方法    
预防    
避免：支持定时的锁      
检测: 通过线程转储信息分析 kill -3  | jstack pid     
解除    

## 10.3其他活跃性危险

1. 饥饿
2. 响应速度 
3. 活锁： 发生在处理事务消息的运用程序中：如果不能成功处理某个消息，那么消息处理机制将回滚整个事务，并将它重新放到队列的开头。  


# 第11章 性能与可伸缩性

## 11.1 对性能的思考
io密集型vs 计算密集型   

开销： 线程协作（加锁，信号，内存同步） 上下文切换 线程建立销毁 线程调度    

方式    
1. 更有效地利用现有处理资源 
2. 及时的出现新的处理资源时程序尽可能利用这些新资源     

### 性能&可伸缩性
可伸缩性： 当增加计算资源时，程序的吞吐量或者处理响应的增加 

### Amdahl 阿姆德尔定律
SpeedUp <= 1 /(F + (1 -  F) / N)    
加速比  
F 必须串行的部分百分比  
N 处理器数目    

应用：
锁分解：一个锁分解为2个锁   
锁分段：一个锁分解为多个锁  

## 11.3 线程引入的开销
1. 上下文切换 5um
> vmstat  看system的 cs

2. 内存同步 
synchronized 和volatile 提供可见性保证中可能会使用一些特殊指令 内存栅栏     

3. 阻塞
锁竞争引发阻塞

## 11.4 减少锁的竞争

> 在并发程序中，对可伸缩性的最主要的威胁就是独占方式的资源  

方法 
- 减少锁的持有有时间 (1.缩小锁范围  2. 减小锁的粒度 锁分解和锁分段
- 降低锁的请求频率  避免热点域
- 使用带有协调进制的独占锁，这些机制允许更高的并发性 ：并发容器 读写锁 不可变对象 原子变量  

锁分解：一个锁分解为2个锁   
锁分段：一个锁分解为多个锁  
锁分解问题： 一个拥有多个处理器的系统中仍然无法给可伸缩性带来极大的提高     
锁分段： 与采用单个锁来实现独占访问相比，要获取多个锁来实现独占访问将更加困难并且开销更大  

### 监控cpu利用率
vmstat mpstat

- 负载不充分
- io密集
- 外部局限
- 锁竞争


> 少使用对象池 


# 第12章 并发程序的测试

1. 安全性测试：不发生任何错误的行为
2. 活跃性测试：某个良好的行为终究会发生
    - 吞吐量 
    - 响应量

## 正确性测试 
1. 单元测试 后验条件和不变条件
2. 阻塞测试 自我中断自我对比
3. 安全性测试 校验验值
4. 资源测试 
5. 使用回调 
6. Thread.yield 释放cpu 产生交替操作

## 性能测试 
计时 算法 响应 

## 陷阱
- gc 
- 动态编译
- 编译器优化

## 其他方法
1. 代码审核 人工
2. 静态分析工具 findbugs   
3. 监控工具JMX

# 第四部分 高级主题 
# 第13章 显式锁 
ReentrantLock --> Lock 

ReentrantReadWriteLock --> ReadWriteLock 

ReentrantReadWriteLock.ReadLock --> Lock
ReadtrantReadWriteLock.WriteLock --> Lock


## 13.1 Lock 和 ReentrantLock  可重入互斥锁
互斥锁： 每次最多只有一个线程能持有ReentrantLock
Lock 提供无条件、可轮询的、定时的以及可中断的锁获取操作     

```
public interface Lock{
    void lock(); // *内置锁 无法无限等待
    void lockInterruptibly() throws InterruptedException; // 可中断的锁操作 * 内置锁无法中断
    boolean tryLock(); // 轮询锁 * 内置锁无法实现非阻塞加锁   
    boolean tryLock(long timeout,TimeUnit unit) throws InterruptedException;  // 定时锁 可防止死锁 
    void unlock();
    Condition newCondition();   // 
}
```

> 缺点： 危险，程序执行控制离开被保护代码块是，不会自动清除锁

使用
```
Lock lock = new ReentrantLock();
lock.lock();
try{}finally{lock.unlock();}
```

性能 ReentrantLock 和 synchronized 在java6后趋同    
ReentrantLock vs synchronized   
当需要一些高级功能时才应该使用ReentrantLock:可定时的，可轮询的与可中断的锁获取操作，公平队列，以及非块结构的问题，否使用synchronized    


## 13.3公平性
公平锁：线程按请求上锁 
非公平锁：允许插队 ：当一个线程请求非公平的锁时，如果在发出请求的同时该锁的状态为可用，那么这个线程将跳过队列中所有的等待线程并获得这个锁。（默认）

* 内置加锁并不会提供确定的公平性保证

## 读写锁 (共享锁)
一个资源可被多个读，或者被一个写

```
public interface ReedWriteLock{
    Lock readLock();
    Lock WriteLock();
}
```



# 第14章 构建自定义的同步工具

## 状态依赖性管理 
1. 直接判断 失败throw
2. 轮询 + 休眠 while sleep
3. 条件队列 wait notify notifyAll

条件队列： 他使得一组线程能够通过某种方式来等待特定的条件变成真，队列里是一个个等待的线程   
每个对象可以作为一个锁，每个对象也可以作为一个条件队列 ，Object wait notify notigyAll 方法 注册api      
在 cpu效率 上下文切换开销 和响应性 

## 14.2 使用条件队列

- 条件谓词 关键找出对象在哪个条件谓词上等待     


问题
1. 过早唤醒 
2. 信号丢失 :notify 超前 
3. 子类的安全问题 


使用标准 
```
void sateDependentMethod() throws InterruptedException{
    synchronized(lock){  // 必须获取 锁 lock 的monitor 
        while(!conditionPredicate()){
            lock.wait();  // 释放 lock的monitor
        }
    }
}
```
要求
- 通常都有一个条件谓词--包括一些对象状态的测试，线程在执行前必须首先通过这些测试
- 在调用wait之前测试条件谓词，并且从wait中返回时再次进行测试
- 在一个循环中调用wait
- 确保使用与条件队列相关的锁来保护构成条件谓词的各个状态变量 
- 当调用wait notify notifyAll时 要持有与条件队列相关的锁
- 在检查条件谓词之后以及开始执行相应的操作之前，不要释放锁 


## 14.3 显示Condition对象 
Condition 和Lock 《---》 条件队列和一个内置锁  


## 14.5 AbstractQueuedSynchronizer 
用于构建锁和同步器的框架      
ReentrantLock Semaphore CountDownLatch FutureTask ReetrantReadWriteLock 



# 第15章 原子变量与非阻塞同步机制 


非阻塞算法：用底层的原子机器指令替代锁来确保数据在并发访问中的一致性 。 
设计和实现复杂，但是可伸缩性和活跃性比较强  


## 锁的劣势
1. 在挂起和恢复线程等过程中存在着很大的开销，并且通常存在着较长时间的中断 
2. 当一个线程正在等待锁时，它不能做任何其他事情

## 15.2 硬件对并发的支持 
独占锁时一项悲观计算 // 悲观锁  
乐观锁 需要借助冲突检查机制来判断更新过程中是否在来自其他线程的干扰，如果存在，这个操作将失败，并且重试 。。

### CAS 比较交换  
我认为V的值应该为A,如果是将V的值设置为B，否则不修改并告诉V实际值是多少      原子操作 


## 15.3 原子变量类

标量类: AtomicInteger AtomicLong AtomicBoolean AtomicReference 
更新器类
数组类：只支持Integer Long Reference 
复合变量类


## 15.4 非阻塞算法

非阻塞算法： 一个线程的失败或挂起不会导致其他线程页失败或挂起 
无锁算法：如果在算法的每个步骤中都存在某个线程能够执行下去  
如果算法仅将CAS用于协调线程之前的操作，并且能正确的实现，那么它既是一种无阻塞算法，也是一种无锁算法。






# 第16章 Java 内存模式 

只要程序的最终结果与在严格串行环境中执行的结果相同，那么上述所有操作都是允许的  重排序

JMM 规定JVM必须遵循一组最小包装，在这组保证规定了对变量的写入操作在任何时候将对于其他线程可见 。    

JMM 为程序所有操作定义了一个偏序关系 Happens-Before
- 程序顺序规则 如果程序中操作A在操作B之前，那么在线程A操作将在B操作之前执行     
- 监视锁规则    在监视器锁上的解锁操作必须在同一个监视器锁上的加锁操作之前执行 
- volatile变量  对volatile变量的写入操作必须在该变量的读操作之前执行    
- 线程启动  在线程上对Thread.Start 的调用必须在改线程中执行任何操作之前执行 
- 线程结束  线程中的任何操作都必须在其他线程检测到该线程已经结束之前执行，或者从Thread.join中成功返回，或者在调用Thread.isAlive时返回false  
- 中断  当一个线程在另一个线程上调用interrupt时必须在被中断线程检测到interrupt调用之前执行 
- 终结器 对象的构造函数必须在启动该对象的终结器之前执行完成 
- 传递性 如果操作A在操作B之前执行，并且操作B在操作C之前执行，那么操作A必须在操作C之前执行   


## 发布 单例模式
懒汉模式

```
public class C{
    private static Object object ;
    public synchronized static Object getInstance(){
        if(object == null){
            object = new Object();
        }
        return object;
    }
}
```
饿汉模式 
```
public class C{
    private static Object object  = new Object();
    public static Object getInstance(){
        return object;
        }
}
```
双重检查加锁 DCL    
问题：当在没有同步的情况下读取一个共享对象时，可能发生的最糟糕事情只是看到一个生效的值。
```
public class C{
    private static Object object ;
    public  static Object getInstance(){
        if(object == null){
            synchronized (C.this){
                if(obejct == null){
                    object = new Object();
                }
            }
        }
        return object;
    }
}
```
