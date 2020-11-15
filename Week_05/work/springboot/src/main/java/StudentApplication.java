import bean.Students;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: StudentApplication
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 20:45
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = Students.class)
public class StudentApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(StudentApplication.class,args);
        Students students = (Students) configurableApplicationContext.getBean("students");
        System.out.println("students is " + students);
    }
}
