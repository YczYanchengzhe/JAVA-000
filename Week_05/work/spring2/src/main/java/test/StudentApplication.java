package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import test.bean.Students;
import test.service.StudentsService;

/**
 * @ClassName: StudentApplication
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 20:45
 */
public class StudentApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentsService studentsService = (StudentsService) applicationContext.getBean("StudentsService");
        studentsService.printStudent();
    }
}
