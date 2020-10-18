package study;


/**
 * Created by yanchengzhe
 * Date 2020/10/8 21:00
 * Description
 *
 * @author yanchengzhe
 */
public class TestMove {
    public static void main(String[] args) {
        MoveAvg moveAvg = new MoveAvg();
        int num1 = 15;
        int num2 = 26;
        moveAvg.move(num1);
        moveAvg.move(num2);
        double result = moveAvg.getAvg();
        System.out.println(result);
    }
}
