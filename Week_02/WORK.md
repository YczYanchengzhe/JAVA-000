作业

# 一。使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。

## 1.串行GC 
#### 情况一
参数 ： java -Xmx128m -Xms128m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 发生6次youngGC之后出现FullGC，之后内存溢出。
分析 ： 由于分配的内存较少，fullgc也无法释放足够分配的空间，可以看下下面的例子，Full GC钱内存87399K， GC之后87201K，基本无变化

```verilog
2020-10-25T18:31:32.808+0800: [Full GC (Allocation Failure) 2020-10-25T18:31:32.816+0800: [Tenured: 87399K->87201K(87424K), 0.0210892 secs] 126441K->126242K(126720K), [Metaspace: 2724K->2724K(1056768K)], 0.0351655 secs] [Times: user=0.02 sys=0.02, real=0.03 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at java.util.Arrays.copyOf(Arrays.java:3332)
        at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
        at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:674)
        at java.lang.StringBuilder.append(StringBuilder.java:208)
        at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:55)
        at GCLogAnalysis.main(GCLogAnalysis.java:24)
```
#### 情况2
参数 ： java -Xmx256m -Xms256m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 发生6次youngGC之后出现FullGC，并未出现内存溢出,分析大量时间耗费在FullGC上面,执行时间长一些的话还是会出现内存溢出,生成 3531对象

```verilog
2020-10-25T18:38:18.709+0800: [Full GC (Allocation Failure) 2020-10-25T18:38:18.710+0800: [Tenured: 174650K->174703K(174784K), 0.0392436 secs] 252994K->187393K(253440K), [Metaspace: 2724K->2724K(1056768K)], 0.0433333 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
```

#### 情况3
参数 ： java -Xmx512m -Xms512m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 并未出现FullGC ,此时内存足够使用 ,生成 6108对象

## 2.并行GC 
#### 情况一
参数 ： java -Xmx128m -Xms128m -XX：+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 发生6次youngGC之后出现FullGC，之后内存溢出。
分析 ： 由于分配的内存较少，fullgc也无法释放足够分配的空间，可以看下下面的例子,年轻代基本无变化,老年代也无变化,此时已经没有空间可供分配

```verilog
2020-10-25T18:45:27.825+0800: [Full GC (Allocation Failure) [PSYoungGen: 14784K->14784K(29184K)] [ParOldGen: 87481K->87462K(87552K)] 102265K->102246K(116736K), [Metaspace: 2724K->2724K(1056768K)], 0.0418295 secs] [Times: user=0.17 sys=0.00, real=0.04 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:47)
        at GCLogAnalysis.main(GCLogAnalysis.java:24)
```
#### 情况2
参数 ： java -Xmx256m -Xms256m  -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 发生6次youngGC之后出现FullGC，并未出现内存溢出,分析大量时间耗费在FullGC上面,执行时间长一些的话还是会出现内存溢出 , ,生成 3093对象

```verilog
2020-10-25T18:49:49.426+0800: [Full GC (Ergonomics) [PSYoungGen: 29320K->24477K(58368K)] [ParOldGen: 174979K->174997K(175104K)] 204299K->199474K(233472K), [Metaspace: 2723K->2723K(1056768K)], 0.0436584 secs] [Times: user=0.36 sys=0.00, real=0.04 secs]
```

#### 情况3
参数 ： java -Xmx512m -Xms512m -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 此时内存足够使用 ,生成 6108对象 , 一共发生两次FullGC , 以下面这次为例

```verilog
2020-10-25T18:50:47.977+0800: [GC (Allocation Failure) [PSYoungGen: 100736K->18470K(116736K)] 385525K->341944K(466432K), 0.0175564 secs] [Times: user=0.00 sys=0.02, real=0.02 secs]
2020-10-25T18:50:47.997+0800: [Full GC (Ergonomics) [PSYoungGen: 18470K->0K(116736K)] [ParOldGen: 323474K->238083K(349696K)] 341944K->238083K(466432K), [Metaspace: 2724K->2724K(1056768K)], 0.0455395 secs] [Times: user=0.23 sys=0.02, real=0.05 secs]
```

## 3.CMS-GC 
#### 情况一
参数 ： java -Xmx128m -Xms128m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 内存溢出。
分析 ： 由于分配的内存较少，FullGC也无法释放足够分配的空间，可以看下下面的例子,年轻代基本无变化,老年代也无变化,此时已经没有空间可供分配

```verilog
2020-10-25T18:56:21.191+0800: [Full GC (Allocation Failure) 2020-10-25T18:56:21.193+0800: [CMS: 87224K->87205K(87424K), 0.0227142 secs] 126316K->126297K(126720K), [Metaspace: 2724K->2724K(1056768K)], 0.0269391 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:41)
        at GCLogAnalysis.main(GCLogAnalysis.java:24)
```
#### 情况2
参数 ：  java -Xmx256m -Xms256m -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 并未出现内存溢出,分析大量时间耗费在FullGC上面,执行时间长一些的话还是会出现内存溢出 , 生成 3719对象

```verilog
2020-10-25T18:59:10.760+0800: [Full GC (Allocation Failure) 2020-10-25T18:59:10.764+0800: [CMS: 174497K->174204K(174784K), 0.0508979 secs] 252988K->232826K(253440K), [Metaspace: 2724K->2724K(1056768K)], 0.0579315 secs] [Times: user=0.05 sys=0.00, real=0.06 secs]
```

#### 情况3
参数 ： java -Xmx512m -Xms512m -XX:+UseConcMarkSweepGC -XX:+PrintGC -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 此时内存足够使用 ,生成 5958对象 , 一共发生两次FullGC , 以下面这次为例

```verilog
2020-10-25T19:01:48.738+0800: [GC (CMS Initial Mark)  325842K(506816K), 0.0041930 secs]
2020-10-25T19:01:48.783+0800: [Full GC (Allocation Failure)  465241K->302186K(506816K), 0.0655748 secs]
```

## 3.G1-GC 
#### 情况一
参数 ： java -Xmx128m -Xms128m -XX:+UseG1GC -XX:+PrintGC -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 内存溢出。
分析 ： 由于分配的内存较少，FullGC也无法释放足够分配的空间，可以看下下面的例子,年轻代基本无变化,老年代也无变化,此时已经没有空间可供分配

```verilog
2020-10-25T19:04:55.298+0800: 2020-10-25T19:04:55.298+0800: [GC concurrent-root-region-scan-end, 0.0035954 secs]
[GC pause (G1 Evacuation Pause) (young)2020-10-25T19:04:55.311+0800: [GC concurrent-mark-start]
 101M->100M(128M), 0.0193044 secs]
2020-10-25T19:04:55.315+0800: [GC pause (G1 Evacuation Pause) (young)-- 101M->101M(128M), 0.0021567 secs]
2020-10-25T19:04:55.319+0800: [Full GC (Allocation Failure)  101M->101M(128M), 0.0085770 secs]
2020-10-25T19:04:55.328+0800: [Full GC (Allocation Failure)  101M->101M(128M), 0.0151682 secs]
2020-10-25T19:04:55.344+0800: [GC concurrent-mark-abort]
2020-10-25T19:04:55.345+0800: [GC pause (G1 Evacuation Pause) (young) 101M->101M(128M), 0.0132485 secs]
2020-10-25T19:04:55.358+0800: [GC pause (G1 Evacuation Pause) (young) 101M->101M(128M), 0.0029855 secs]
2020-10-25T19:04:55.362+0800: [Full GC (Allocation Failure)  101M->634K(128M), 0.0119738 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:41)
        at GCLogAnalysis.main(GCLogAnalysis.java:24)
```
#### 情况2
参数 ： java -Xmx256m -Xms256m -XX:+UseG1GC -XX:+PrintGC -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 并未出现内存溢出,分析大量时间耗费在FullGC上面,执行时间长一些的话还是会出现内存溢出 , 生成 3301对象

```verilog
2020-10-25T19:12:21.828+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 199M->196M(256M), 0.0028159 secs]
2020-10-25T19:12:21.832+0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:12:21.833+0800: [GC concurrent-root-region-scan-end, 0.0011221 secs]
2020-10-25T19:12:21.834+0800: [GC concurrent-mark-start]
2020-10-25T19:12:21.837+0800: [GC pause (G1 Evacuation Pause) (young)-- 203M->203M(256M), 0.0062824 secs]
执行结束!共生成对象次数:3301
2020-10-25T19:12:21.843+0800: [GC concurrent-mark-end, 0.0092329 secs]
2020-10-25T19:12:21.844+0800: [GC remark, 0.0029110 secs]
2020-10-25T19:12:21.849+0800: [GC cleanup 204M->204M(256M), 0.0024251 secs]
```

#### 情况3
参数 ： java -Xmx512m -Xms512m -XX:+UseG1GC -XX:+PrintGC -XX:+PrintGCDateStamps  GCLogAnalysis
现象 ： 并未出现FullGC ,此时内存足够使用 ,生成 6127对象 , 一共发生两次FullGC , 以下面这次为例

```verilog
2020-10-25T19:14:39.112+0800: [GC concurrent-root-region-scan-end, 0.0016779 secs]
2020-10-25T19:14:39.113+0800: [GC concurrent-mark-start]
2020-10-25T19:14:39.121+0800: [GC concurrent-mark-end, 0.0082433 secs]
2020-10-25T19:14:39.123+0800: [GC remark, 0.0033495 secs]
2020-10-25T19:14:39.127+0800: [GC cleanup 383M->383M(512M), 0.0043715 secs]
执行结束!共生成对象次数:6127
```

# 二。使用压测工具（wrk或sb），演练gateway-server-0.0.1-SNAPSHOT.jar 示例

## 情况1 : 串行GC
```shell
 java -Xmx128m -Xms128m  -XX:+UseParallelGC -XX:+UseParallelOldGC  -jar .\gateway-server-0.0.1-SNAPSHOT.jar
sb -u http://localhost:8088/api/hello -c 20 -N 60
```
```
Starting at 2020/10/25 19:46:29
[Press C to stop the test]
78945   (RPS: 1155.1)
---------------Finished!----------------
Finished at 2020/10/25 19:47:38 (took 00:01:08.4941946)
Status 200:    78949

RPS: 1292.1 (requests/second)
Max: 386ms
Min: 0ms
Avg: 1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 1ms
  95%   below 3ms
  98%   below 9ms
  99%   below 20ms
99.9%   below 87ms
```
## 情况2 : 并行GC
```shell
java -XX：+UseParallelGC -XX:+UseParallelOldGC  -jar .\gateway-server-0.0.1-SNAPSHOT.jar
sb -u http://localhost:8088/api/hello -c 20 -N 60
```
```
Starting at 2020/10/25 19:39:00
[Press C to stop the test]
95969   (RPS: 1406.5)
---------------Finished!----------------
Finished at 2020/10/25 19:40:08 (took 00:01:08.4261849)
Status 200:    95981

RPS: 1569.2 (requests/second)
Max: 439ms
Min: 0ms
Avg: 0.9ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 1ms
  95%   below 3ms
  98%   below 8ms
  99%   below 19ms
99.9%   below 78ms
```

## 情况3 : CMS-GC
```shell
java -XX：+UseConcMarkSweepGC  -jar .\gateway-server-0.0.1-SNAPSHOT.jar
sb -u http://localhost:8088/api/hello -c 20 -N 60
```
```
Starting at 2020/10/25 22:35:03
[Press C to stop the test]
88886   (RPS: 1304.7)
---------------Finished!----------------
Finished at 2020/10/25 22:36:11 (took 00:01:08.2842572)
Status 200:    88888

RPS: 1453.9 (requests/second)
Max: 411ms
Min: 0ms
Avg: 0.8ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 1ms
  95%   below 2ms
  98%   below 7ms
  99%   below 16ms
99.9%   below 81ms
```


## 情况4 : G1-GC
```shell
java -XX:+UseG1GC -XX:MaxGCPauseMillis=50  -jar .\gateway-server-0.0.1-SNAPSHOT.jar
sb -u http://localhost:8088/api/hello -c 20 -N 60
```
```
Starting at 2020/10/25 22:37:46
[Press C to stop the test]
75088   (RPS: 1089.7)
---------------Finished!----------------
Finished at 2020/10/25 22:38:56 (took 00:01:09.1292283)
Status 200:    75096

RPS: 1226.6 (requests/second)
Max: 550ms
Min: 0ms
Avg: 1.2ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 1ms
  95%   below 2ms
  98%   below 9ms
  99%   below 24ms
99.9%   below 139ms
```

# 三。对于不同 GC 的总结

### 概述 :
1.串行GC : 
    (1)介绍
    对年轻代使用 mark-copy（标记-复制）算法，对老年代使用 mark-sweep-compact（标记-清除-整理）算法.都是单线程,在进行GC时候会STW.
    (2)优点
    因为使用的是单线程,所以复杂度更低,占用内存更少.
    (3)缺点
    不能有效利用多核优势
    (4)适用场景
    适合堆内存不高、单核甚至双核CPU的场合。
2.并行GC :
	(1)介绍
	在年轻代使用 标记-复制（mark-copy）算法，在老年代使用 标记-清除-整理（mark-sweep-compact）算法。
    (2)优点
    在 GC 期间，所有 CPU 内核都在并行清理垃圾，所以总暂停时间更短；
    在两次 GC 周期的间隔期，没有 GC 线程在运行，不会消耗任何系统资源。
    (3)缺点
    年轻代和老年代的垃圾回收都会触发 STW 事件。
    (4)适用场景
    适用于多核服务器，主要目标是增加吞吐量。因为对系统资源的有效使用，能达到更高的吞吐量;
3.CMS-GC:
	(1)介绍
	对年轻代采用并行 STW 方式的 mark-copy (标记-复制)算法，对老年代主要使用并发 mark-sweep (标记-清除)算法。
    (2)优点
    系统在进行GC是STW时间更少,可以实现GC和业务一起执行,避免了在老年代垃圾收集时出现长时间的卡顿.
    (3)缺点
    老年代内存碎片问题,因为对于老年代没有进行压缩,吞吐量不如并行GC.
    (4)适用场景
    业务要求低延时,响应速度有较高要求的情景
4.G1GC
	(1)介绍
	JDK-9 之后默认使用的GC方式 , 堆不在分为年轻代和老年代,而是分为一个一个的小块堆区域.并且每次垃圾回收不必处理全部的数据,可以根据配置处理一定比例的数据,保留一部分在下一次GC时候处理.并且会统计每一个小堆块的存活对象总数,存货最多的会被优先处理.是对CMS的一个优化提升.
    (2)优点
    将STW停顿的时间和分布，变成可预期且可配置的。
    (3)缺点
    吞吐量不如并行GC,G1触发了Full GC,之后会使用串行GC来完成垃圾清理,GC的暂停时间会更长
    (4)适用场景
	业务要求低延时,响应速度有较高要求的情景

# 四。写一段代码，使用HttpClient或OkHttp访问 http://localhost:8801，代码提交到github。

工程 - netty模块

# 五。 （可选）运行课上的例子，以及Netty的例子，分析相关现象。