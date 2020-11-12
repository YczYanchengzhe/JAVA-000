package work;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/12 10:24
 * @Description:
 */
public class LockConditionTest {
    private static Lock lock = new ReentrantLock();

    private static Integer sumResult = null;
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                sum();
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        //线程启动
        thread.start();

        try {
            lock.lock();
            condition.await();
        } finally {
            lock.unlock();
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
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }


}
