package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.bean.Students;

/**
 * @ClassName: StudentApplication
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 20:45
 */
public class StudentApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Students students = (Students) applicationContext.getBean("students");
        System.out.println("students is " + students);
    }
}
