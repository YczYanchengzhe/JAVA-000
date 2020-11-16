package service;

import bean.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName: SchoolService
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/16 0:00
 */
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolService {

    @Autowired
    private School school;


    public void printSchool(){
        if (school != null) {
            System.out.println(school);
        }
    }

}
