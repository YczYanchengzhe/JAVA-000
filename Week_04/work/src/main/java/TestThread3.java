import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/9 00:08
 * @Description:
 */
public class TestThread3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int result =  CompletableFuture.supplyAsync( () -> TestThread2.testMethod()).join();
        //先执行  , 之后再获取
        System.out.println("get result is  : " + result);
    }


    public static int testMethod() {
        return 1;
    }

}
