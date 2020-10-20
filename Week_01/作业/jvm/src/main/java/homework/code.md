```java
$ javap -c -verbose JavaTest.class
Classfile /D:/IdeaProjects/aJiKeStudy/study/JAVA-000/Week_01/作业/jvm/src/main/java/homework/JavaTest.class
  //上次修改时间
  Last modified 2020-10-21; size 1778 bytes
  MD5 checksum 32ba495642060e8b5182e2f7f8212725
  Compiled from "JavaTest.java"
  //类修饰符 public
public class homework.JavaTest
  //JDK1.8
  minor version: 0
  major version: 52
  //访问修饰符
  flags: ACC_PUBLIC, ACC_SUPER
//常量池
Constant pool:
    //方法引用 : 调用->Object.init() ,返回值void
    #1 = Methodref          #18.#56       // java/lang/Object."<init>":()V
    //类 : 类型是#57
    #2 = Class              #57           // java/math/BigInteger
    //字符串 : 数值为 #58
    #3 = String             #58           // 123456789012345678
    //调用 BigInteger的初始化,返回值string
    #4 = Methodref          #2.#59        // java/math/BigInteger."<init>":(Ljava/lang/String;)V
    //链表
    #5 = Class              #60           // java/util/ArrayList
    //链表初始化
    #6 = Methodref          #5.#56        // java/util/ArrayList."<init>":()V
    //初始化 字符串a
    #7 = String             #61           // a
    //调用list的add方法初始化
    #8 = InterfaceMethodref #62.#63       // java/util/List.add:(Ljava/lang/Object;)Z
    //初始化字符串
    #9 = String             #64           // b
   #10 = String             #65           // c
    //对于多个字符的添加,jvm会优化为迭代器方式
   #11 = InterfaceMethodref #62.#66       // java/util/List.iterator:()Ljava/util/Iterator;
   #12 = InterfaceMethodref #67.#68       // java/util/Iterator.hasNext:()Z
   #13 = InterfaceMethodref #67.#69       // java/util/Iterator.next:()Ljava/lang/Object;
   #14 = Class              #70           // java/lang/String
   #15 = Methodref          #14.#71       // java/lang/String.toCharArray:()[C
   #16 = Fieldref           #72.#73       // java/lang/System.out:Ljava/io/PrintStream;
   #17 = String             #74           // list > i list is %s , i is %d
   #18 = Class              #75           // java/lang/Object
   #19 = Methodref          #76.#77       // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
   #20 = Methodref          #78.#79       // java/io/PrintStream.printf:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
   #21 = String             #80           // list = i list is %s , i is %d
   #22 = String             #81           // list < i list is %s , i is %d
   #23 = Methodref          #78.#82       // java/io/PrintStream.println:()V
   #24 = String             #83           // ------------test--------------
   #25 = Methodref          #78.#84       // java/io/PrintStream.println:(Ljava/lang/String;)V
   #26 = Double             1.2d
   #28 = Float              1.3f
   #29 = Long               123l
   #31 = String             #85           // test [+]
   #32 = Methodref          #78.#86       // java/io/PrintStream.println:(D)V
   #33 = String             #87           // test [-]
   #34 = String             #88           // test [*]
   #35 = String             #89           // test [/]
   #36 = String             #90           // test [%]
   #37 = String             #91           // test [~]
   #38 = Long               -1l
   #40 = Methodref          #78.#92       // java/io/PrintStream.println:(J)V
   #41 = Class              #93           // homework/JavaTest
   #42 = Utf8               <init>
   #43 = Utf8               ()V
   #44 = Utf8               Code
   #45 = Utf8               LineNumberTable
   #46 = Utf8               main
   #47 = Utf8               ([Ljava/lang/String;)V
   #48 = Utf8               StackMapTable
   #49 = Class              #94           // "[Ljava/lang/String;"
   #50 = Class              #57           // java/math/BigInteger
   #51 = Class              #95           // java/util/List
   #52 = Class              #96           // java/util/Iterator
   #53 = Class              #70           // java/lang/String
   #54 = Utf8               SourceFile
   #55 = Utf8               JavaTest.java
   #56 = NameAndType        #42:#43       // "<init>":()V
   #57 = Utf8               java/math/BigInteger
   #58 = Utf8               123456789012345678
   #59 = NameAndType        #42:#97       // "<init>":(Ljava/lang/String;)V
   #60 = Utf8               java/util/ArrayList
   #61 = Utf8               a
   #62 = Class              #95           // java/util/List
   #63 = NameAndType        #98:#99       // add:(Ljava/lang/Object;)Z
   #64 = Utf8               b
   #65 = Utf8               c
   #66 = NameAndType        #100:#101     // iterator:()Ljava/util/Iterator;
   #67 = Class              #96           // java/util/Iterator
   #68 = NameAndType        #102:#103     // hasNext:()Z
   #69 = NameAndType        #104:#105     // next:()Ljava/lang/Object;
   #70 = Utf8               java/lang/String
   #71 = NameAndType        #106:#107     // toCharArray:()[C
   #72 = Class              #108          // java/lang/System
   #73 = NameAndType        #109:#110     // out:Ljava/io/PrintStream;
   #74 = Utf8               list > i list is %s , i is %d
   #75 = Utf8               java/lang/Object
   #76 = Class              #111          // java/lang/Integer
   #77 = NameAndType        #112:#113     // valueOf:(I)Ljava/lang/Integer;
   #78 = Class              #114          // java/io/PrintStream
   #79 = NameAndType        #115:#116     // printf:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
   #80 = Utf8               list = i list is %s , i is %d
   #81 = Utf8               list < i list is %s , i is %d
   #82 = NameAndType        #117:#43      // println:()V
   #83 = Utf8               ------------test--------------
   #84 = NameAndType        #117:#97      // println:(Ljava/lang/String;)V
   #85 = Utf8               test [+]
   #86 = NameAndType        #117:#118     // println:(D)V
   #87 = Utf8               test [-]
   #88 = Utf8               test [*]
   #89 = Utf8               test [/]
   #90 = Utf8               test [%]
   #91 = Utf8               test [~]
   #92 = NameAndType        #117:#119     // println:(J)V
   #93 = Utf8               homework/JavaTest
   #94 = Utf8               [Ljava/lang/String;
   #95 = Utf8               java/util/List
   #96 = Utf8               java/util/Iterator
   #97 = Utf8               (Ljava/lang/String;)V
   #98 = Utf8               add
   #99 = Utf8               (Ljava/lang/Object;)Z
  #100 = Utf8               iterator
  #101 = Utf8               ()Ljava/util/Iterator;
  #102 = Utf8               hasNext
  #103 = Utf8               ()Z
  #104 = Utf8               next
  #105 = Utf8               ()Ljava/lang/Object;
  #106 = Utf8               toCharArray
  #107 = Utf8               ()[C
  #108 = Utf8               java/lang/System
  #109 = Utf8               out
  #110 = Utf8               Ljava/io/PrintStream;
  #111 = Utf8               java/lang/Integer
  #112 = Utf8               valueOf
  #113 = Utf8               (I)Ljava/lang/Integer;
  #114 = Utf8               java/io/PrintStream
  #115 = Utf8               printf
  #116 = Utf8               (Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
  #117 = Utf8               println
  #118 = Utf8               (D)V
  #119 = Utf8               (J)V
{
  public homework.JavaTest();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         //默认会给类一个无参的构造
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 14: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=6, locals=9, args_size=1
//	将单字节的常量值(-128~127)推送至栈顶 初始化 int
         0: bipush        98
//将栈顶int型数值存入指定本地变量
         2: istore_1
         3: new           #2                  // class java/math/BigInteger
//复制栈顶数值并将复制值压入栈顶
         6: dup
//将int,float或String型常量值从常量池中推送至栈顶
         7: ldc           #3                  // String 123456789012345678
//初始化操作
         9: invokespecial #4                  // Method java/math/BigInteger."<init>":(Ljava/lang/String;)V
//将栈顶引用型数值存入第三个本地变量
        12: astore_2
        13: new           #5                  // class java/util/ArrayList
        16: dup
        17: invokespecial #6                  // Method java/util/ArrayList."<init>":()V
//将栈顶引用型数值存入第四个本地变量
        20: astore_3
        21: aload_3
//对于链表的初始化-> a, b,c
        22: ldc           #7                  // String a
        24: invokeinterface #8,  2            // InterfaceMethod java/util/List.add:(Ljava/lang/Object;)Z
//将栈顶数值弹出(数值不能是long或double类型的)
        29: pop
        30: aload_3
        31: ldc           #9                  // String b
        33: invokeinterface #8,  2            // InterfaceMethod java/util/List.add:(Ljava/lang/Object;)Z
        38: pop
        39: aload_3
        40: ldc           #10                 // String c
        42: invokeinterface #8,  2            // InterfaceMethod java/util/List.add:(Ljava/lang/Object;)Z
        47: pop
        48: aload_3
        49: invokeinterface #11,  1           // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
        54: astore        4
        56: aload         4
        58: invokeinterface #12,  1           // InterfaceMethod java/util/Iterator.hasNext:()Z
//当栈顶int型数值等于0时跳转->184
        63: ifeq          184
        66: aload         4
        68: invokeinterface #13,  1           // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
//检验类型转换, 检验未通过将抛出 ClassCastException
        73: checkcast     #14                 // class java/lang/String
        76: astore        5
        78: aload         5
        80: invokevirtual #15                 // Method java/lang/String.toCharArray:()[C
//	将int型0推送至栈顶        
        83: iconst_0
//将char型数组指定索引的值推送至栈顶
        84: caload
        85: iload_1
//比较栈顶两int型数值大小, 当结果小于等于0时跳转
        86: if_icmple     117
//获取指定类的静态域, 并将其压入栈顶
        89: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
        92: ldc           #17                 // String list > i list is %s , i is %d
        94: iconst_2
//创建一个引用型(如类, 接口, 数组)的数组, 并将其引用值压入栈顶
        95: anewarray     #18                 // class java/lang/Object
        98: dup
        99: iconst_0
       100: aload         5
//将栈顶引用型数值存入指定数组的指定索引位置
       102: aastore
       103: dup
       104: iconst_1
       105: iload_1
       106: invokestatic  #19                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       109: aastore
       110: invokevirtual #20                 // Method java/io/PrintStream.printf:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
       113: pop
//无条件跳转
       114: goto          181
       117: aload         5
       119: invokevirtual #15                 // Method java/lang/String.toCharArray:()[C
       122: iconst_0
       123: caload
       124: iload_1
       125: if_icmpne     156
       128: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       131: ldc           #21                 // String list = i list is %s , i is %d
       133: iconst_2
       134: anewarray     #18                 // class java/lang/Object
       137: dup
       138: iconst_0
       139: aload         5
       141: aastore
       142: dup
       143: iconst_1
       144: iload_1
       145: invokestatic  #19                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       148: aastore
       149: invokevirtual #20                 // Method java/io/PrintStream.printf:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
       152: pop
       153: goto          181
       156: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       159: ldc           #22                 // String list < i list is %s , i is %d
       161: iconst_2
       162: anewarray     #18                 // class java/lang/Object
       165: dup
       166: iconst_0
       167: aload         5
       169: aastore
       170: dup
       171: iconst_1
       172: iload_1
       173: invokestatic  #19                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       176: aastore
       177: invokevirtual #20                 // Method java/io/PrintStream.printf:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/io/PrintStream;
       180: pop
       181: goto          56
       184: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       187: invokevirtual #23                 // Method java/io/PrintStream.println:()V
       190: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       193: ldc           #24                 // String ------------test--------------
       195: invokevirtual #25                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
//将long或double型常量值从常量池中推送至栈顶(宽索引)
       198: ldc2_w        #26                 // double 1.2d
//将栈顶double型数值存入指定本地变量
       201: dstore        4
       203: ldc           #28                 // float 1.3f
       205: fstore        6
       207: ldc2_w        #29                 // long 123l
       210: lstore        7
       212: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       215: ldc           #31                 // String test [+]
       217: invokevirtual #25                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       220: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       223: dload         4
       225: fload         6
//	将栈顶float型数值强制转换为double型数值并将结果压入栈顶
       227: f2d
//将栈顶两double型数值相加并将结果压入栈顶
       228: dadd
//将指定的long型本地变量推送至栈顶
       229: lload         7
       231: l2d
       232: dadd
       233: invokevirtual #32                 // Method java/io/PrintStream.println:(D)V
       236: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       239: ldc           #33                 // String test [-]
       241: invokevirtual #25                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       244: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       247: dload         4
       249: fload         6
       251: f2d
       252: dsub
       253: lload         7
       255: l2d
       256: dsub
       257: invokevirtual #32                 // Method java/io/PrintStream.println:(D)V
       260: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       263: ldc           #34                 // String test [*]
       265: invokevirtual #25                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       268: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       271: dload         4
       273: fload         6
       275: f2d
       276: dmul
       277: lload         7
       279: l2d
       280: dmul
       281: invokevirtual #32                 // Method java/io/PrintStream.println:(D)V
       284: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       287: ldc           #35                 // String test [/]
       289: invokevirtual #25                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       292: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       295: dload         4
       297: fload         6
       299: f2d
       300: ddiv
       301: lload         7
       303: l2d
       304: ddiv
       305: invokevirtual #32                 // Method java/io/PrintStream.println:(D)V
       308: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       311: ldc           #36                 // String test [%]
       313: invokevirtual #25                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       316: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       319: dload         4
       321: fload         6
       323: f2d
//将栈顶两double型数值作取模运算并将结果压入栈顶
       324: drem
       325: lload         7
       327: l2d
       328: drem
       329: invokevirtual #32                 // Method java/io/PrintStream.println:(D)V
       332: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       335: ldc           #37                 // String test [~]
       337: invokevirtual #25                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       340: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
       343: lload         7
       345: ldc2_w        #38                 // long -1l
//将栈顶两long型数值"按位异或"并将结果压入栈顶
       348: lxor
       349: invokevirtual #40                 // Method java/io/PrintStream.println:(J)V
       352: return
      LineNumberTable:
        line 17: 0
        line 18: 3
        line 19: 13
        line 20: 21
        line 21: 30
        line 22: 39
        line 23: 48
        line 24: 78
        line 25: 89
        line 27: 117
        line 28: 128
        line 30: 156
        line 32: 181
        line 33: 184
        line 34: 190
        line 35: 198
        line 36: 203
        line 37: 207
        line 39: 212
        line 40: 220
        line 41: 236
        line 42: 244
        line 43: 260
        line 44: 268
        line 45: 284
        line 46: 292
        line 47: 308
        line 48: 316
        line 49: 332
        line 50: 340
        line 51: 352
      StackMapTable: number_of_entries = 5
        frame_type = 255 /* full_frame */
          offset_delta = 56
          locals = [ class "[Ljava/lang/String;", int, class java/math/BigInteger, class java/util/List, class java/util/Iterator ]
          stack = []
        frame_type = 252 /* append */
          offset_delta = 60
          locals = [ class java/lang/String ]
        frame_type = 38 /* same */
        frame_type = 250 /* chop */
          offset_delta = 24
        frame_type = 250 /* chop */
          offset_delta = 2
}
SourceFile: "JavaTest.java"

```