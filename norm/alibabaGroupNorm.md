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

