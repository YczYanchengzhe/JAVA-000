package work;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/9 00:08
 * @Description:
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int result = CompletableFuture.supplyAsync(CompletableFutureTest::sum).join();
        //先执行  , 之后再获取
        System.out.println("get result is  : " + result);
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
