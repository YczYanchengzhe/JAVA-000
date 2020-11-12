package work;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/8 19:53
 * @Description: main 函数启动一个新线程 , 运行一个方法 , 拿到他的返回值
 */
public class FutureTest {

    //submit -> future  获取
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(FutureTest::sum);

        int result = (int) future.get();
        System.out.println("get result is  : " + result);
        executorService.shutdown();
    }

    public static int sum() {
        int sumResult = fibo(36);
        return sumResult;
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
