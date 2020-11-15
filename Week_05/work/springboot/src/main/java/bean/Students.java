package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

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
@Configuration
public class Students {
    private int id = 1;
    private String name = "小明";

    @Bean
    public Students getStudents(){
        return new Students();
    }
}
