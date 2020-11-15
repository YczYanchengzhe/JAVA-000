import bean.Students;
import controller.SchoolController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: StudentApplication
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 20:45
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = SchoolController.class)
public class StudentApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(StudentApplication.class, args);
        Students students = (Students) configurableApplicationContext.getBean("students");
        System.out.println("students is " + students);

    }
}
