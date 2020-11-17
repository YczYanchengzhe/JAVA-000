package chengzhe.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @ClassName: chengzhe.test.bean.StudentApplication
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 20:45
 */
@SpringBootApplication
public class StudentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(StudentApplication.class, args);
//        Students students = (Students) configurableApplicationContext.getBean("students");
//        System.out.println("students is " + students);
    }


}
