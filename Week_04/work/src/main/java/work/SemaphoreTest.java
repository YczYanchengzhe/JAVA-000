package work;

import java.util.concurrent.Semaphore;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/12 10:16
 * @Description:
 */
public class SemaphoreTest {

    private static  Integer sumResult = null;

    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(()->{
            try {
                semaphore.acquire();
                sum();
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        //线程启动
        thread.start();
        //让子线程先执行 , 获取信号
        Thread.sleep(1);
        semaphore.acquire();

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
