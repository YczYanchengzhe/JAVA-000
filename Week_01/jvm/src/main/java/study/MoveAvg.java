package study;

/**
 * Created by yanchengzhe
 * Date 2020/10/8 20:58
 * Description
 *
 * @author yanchengzhe
 */
public class MoveAvg {
    int count;
    double sum;

    public void move(int move) {
        count++;
        sum += move;
    }

    public double getAvg() {
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }

}
