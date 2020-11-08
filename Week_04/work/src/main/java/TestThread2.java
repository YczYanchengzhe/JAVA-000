import java.util.concurrent.*;

/**
 * @auther: yanchengzhe
 * @Date: 2020/11/9 00:08
 * @Description:
 */
public class TestThread2 {

    //FutureTask

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable callable = (Callable<Integer>) () -> TestThread2.testMethod();

        FutureTask futureTask = new FutureTask<>(callable);
        //先执行  , 之后再获取
        futureTask.run();
        int result = (int) futureTask.get();
        System.out.println("get result is  : " + result);
    }


    public static int testMethod() {
        return 1;
    }
}
