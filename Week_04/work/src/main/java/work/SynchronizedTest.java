package work;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/11 21:21
 * @Description:
 */
public class SynchronizedTest {

    private static Object lock = new Object();

    private static  Integer sumResult = null;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(() -> {
            sum();
        });
        //线程启动
        thread.start();
        synchronized (lock){
            lock.wait();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + getMethod());

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    public static Integer getMethod(){
        return sumResult;
    }

    public  static int sum() {
        synchronized(lock){
            sumResult = fibo(36);
            lock.notifyAll();
            return sumResult;
        }
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
