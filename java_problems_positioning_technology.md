
## 1.2 如何解读线程堆栈？

### 1.2.1 线程的解读

Low Memory Detector
CompilerThread()
Signal Dispatcher
Finalizer
Reference Handler
main
VM Thread
VM Periodic Task Thread

- 本地线程

`"main" prio=1 tid=0x0805c988 nid=0xd28 runnable [0xfff65000...0xfff659c8]`

pe -ef|grep java
pstack pid

`Thread 1 (Thread 4160560000 (LWP 3368)):`

Java线程堆栈，本地线程堆栈

- runnable

处于Runnable的线程只能说明该线程没有阻塞在java的wait或者sleep等待状态，同时也没等待在锁上面。注意区别调用Native Method。
因此我们在分析哪个线程在大量CPU时，不能以这个"runnable"字样作为判断该线程是否消耗CPU的依据。

`.<clinit>`表示当前正在执行类的初始化。
`.<init>`表示正在执行对象的构造函数。

- 类的初始化和对象的初始化

	- 类初始化
	- 对象初始化

### 1.2.2 锁的解读

- wait()和sleep()的区别

	- wait() 当线程执行到wait()方法上，当前线程会释放监视锁，此时其他线程可以占有该锁
	- sleep() 与锁操作无关，如果该方法恰好在一个锁的保护范围之内，当前线程即使在执行sleep()的时候，仍然继续保持监视锁。sleep()方法并不是锁上面的一个方法，而是线程的一个静态方法

- 一般情况下，如果有的线程在等待一个锁，该锁必然被另一个线程占有了。
- 实际上，在一个线程释放锁和另一个线程被唤醒之间有一个时间窗，在这期间，如果恰巧进行了堆栈转储

### 1.2.3 线程状态的解读

- runnable:
- timed_waiting(on object monitor):
- timed_waiting(sleeping):
- timed_waiting(parking): 当前线程被挂起一段时间
- waiting(on object monitor):

## 1.3 如何借助线程堆栈进行问题分析？

### 1.3.1 线程死锁分析

死锁的两个或多个线程是不消耗CPU的，像socket或者数据库等IO操作是不怎么消耗CPU的。

### 1.3.2 Java代码死循环等导致的CPU过高分析

导致死循环的原因大致有如下几个：
- HashMap等线程不安全的容器，用在多线程读/写的场合，导致HashMap的方法调用形成死循环
- 多线程场合，对共享变量没有进行保护，导致数据混乱，从而使循环退出的条件永远不满足，导致死循环的发生，如for,while循环中的退出条件永远不满足导致的死循环，链表等数据结构首尾相接，导致遍历永远无法停止。
- 其它错误的编码。

### 1.3.3 高消耗CPU代码的常用分析方法

- 借助操作系统提供的性能分析工具进行CPU消耗分析

1. top -p `pid`
2. 输入`H`查看该进程所有线程的统计情况（CPU等）

1. 通过top -p `jvm pid`获取最消耗CPUS的本地线程ID
2. 通过kill -3打印Java线程堆栈
3. 通过pstack `java pid`打印本地线程堆栈
4. 在Java线程堆栈中查找nid=`第1步获得的最耗CPU时间的线程id`

- Xrunprof协助分析

- JProfiler或者OptimizeIt等工具

- 多次打印堆栈

### 1.3.4 资源不足等导致的性能下降分析

- 大量的线程停在同样的调用上下文上。

### 1.3.5 线程不退出导致的系统挂死分析

### 1.3.6 多个锁导致的锁链分析

### 1.3.7 通过线程堆栈进行性能瓶颈分析

### 1.3.8 线程堆栈不能分析什么问题？

# 2. 通过Java线程堆栈进行性能瓶颈分析

# 3. Java内存泄漏分析和堆内存设置

# 4. 关于并发和多线程

# 5. 幽灵代码

# 6. 常见的Java泥潭

# 7. JVM

# 8. 关于字符集与编码

# 9. 常用工具

# 10. Java最佳实践

# 11. 关于数据库

# 12. 工程实践

# 13. 常见的案例

# 附录 A JProfiler内存泄漏精确定位

# 附录 B SUN JDK自带故障定位
- 挺多的

# 附录 C 在Solaris下，查找占用指定的端口的进程

# 附录 D 如何在solaris下面分析IO瓶颈

# 附录 E AIZ操作系统下，32位进程的最大内存占有情况

# 附录 F 关于TCP/IP

# 附录 G windows 2003/XP下，一个端口可以多个监听

# 附录 H Suse9.0下，线程创建的数量和堆内存/永久内存的关系

# 附录 I JConsole

# 附录 J gcviewer

# 附录 K IBM JDK下定位引起CoreDump的JIT方法

# 附录 L 如何解读Java Core文件？

# 附录 M 几个奇怪的现象

# 附录N 感谢TEX

