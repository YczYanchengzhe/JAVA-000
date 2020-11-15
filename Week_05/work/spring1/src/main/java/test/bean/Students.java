package test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: Students
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 20:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Students {
    private int id;
    private String name;
}
