# 背景
蘑菇街，问题的具体的原因
# 常见问题的基本思路
方面：网络 内存 磁盘io 进程
## 网络 
tcp重传
压测，业务RT抖动，QPS减小
工具：tcpdump(老方法) --->tcpretrans(改进) 

## 内存
内存泄漏
1. 虚拟地址越来越少
2. 物理内存越来越少
方法
编译：sanitizers Asn Tsan Msan
运行：Valgrind
不打断分析：难度比较大
根源
- 长运行deamon
- 处理requset，短时间没什么问题
### 运行是不断任务分析
API     APP
	mmap 	malloc
系统调用 
	mmap	brk
kernal
	增加虚拟地址空间
pidstat 
strace mmap
cat /proc/PID/smaps
## 磁盘io
buffer IO //很难搞定
影响：业务性能波动
IO: 同步写IO 异步写 Direct I/O //异步到缓存就返回
### writeback失控
1. 内存页面回收时的dirty pages 
2. 异步io的突发导致io行为不稳定
#### dirty pages
内存水位
dirty pages --->disk 导致内存分配很慢
解决方案
调整内存水位，限制page cache大小，保证free page 增加水位
#### 异步写io的突发
1. kernel4.10 深度改进
2. 权限
## 进程
问题： Cpu sys使用率高
性能问题，稳定问题
解决：看各个任务栈
sysrq


systemtap
firace 
perf tracepiont kpeobe



