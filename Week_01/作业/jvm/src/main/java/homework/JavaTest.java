package homework;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanchengzhe
 * Date 2020/10/21 0:28
 * Description
 *
 * @author yanchengzhe
 */
public class JavaTest {
    //基本类型，四则运行，if和for
    public static void main(String[] args) {
        int i = 'b';
        BigInteger i2 = new BigInteger("123456789012345678");
        List<String> testList = new ArrayList<>();
        testList.add("a");
        testList.add("b");
        testList.add("c");
        for (String s : testList) {
            if (s.toCharArray()[0] > i) {
                System.out.printf("list > i list is %s , i is %d", s, i);

            } else if (s.toCharArray()[0] == i) {
                System.out.printf("list = i list is %s , i is %d", s, i);
            } else {
                System.out.printf("list < i list is %s , i is %d", s, i);
            }
        }
        System.out.println();
        System.out.println("------------test--------------");
        double myDouble = 1.2;
        float myFloat = 1.3F;
        long myLong = 123L;

        System.out.println("test [+]");
        System.out.println(myDouble + myFloat + myLong);
        System.out.println("test [-]");
        System.out.println(myDouble - myFloat - myLong);
        System.out.println("test [*]");
        System.out.println(myDouble * myFloat * myLong);
        System.out.println("test [/]");
        System.out.println(myDouble / myFloat / myLong);
        System.out.println("test [%]");
        System.out.println(myDouble % myFloat % myLong);
        System.out.println("test [~]");
        System.out.println(~myLong);
    }
}
