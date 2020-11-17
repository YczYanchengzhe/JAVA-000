package chengzhe.test.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: School
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 23:26
 */
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class School {
    /**
     * 学校的班级
     */
    @Autowired
    private Klass class1;
    /**
     * 学校的名字
     */
    @Autowired
    private String schoolName;



}
