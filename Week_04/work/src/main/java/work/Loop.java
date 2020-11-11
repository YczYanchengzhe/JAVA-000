package work;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/11 20:43
 * @Description:
 */
public class Loop {

    private static volatile Integer sumResult = null;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(Loop::sum);
        //线程启动
        thread.start();
        while (sumResult == null) {
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + sumResult);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    public static int sum() {
        sumResult = fibo(36);
        return sumResult;
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
