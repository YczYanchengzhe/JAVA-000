package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.bean.Students;

/**
 * @ClassName: StudentsService
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 22:32
 */
@Service
public class StudentsService {

    @Autowired
    private Students students;


    public void printStudent(){
        System.out.println("students is " + students);
    }

}
