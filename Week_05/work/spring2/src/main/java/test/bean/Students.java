package test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
    private int id = 1;
    private String name = "小明";
}
