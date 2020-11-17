package chengzhe.test.service;

import chengzhe.test.bean.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: SchoolService
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/16 0:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolService {

    private School school;

    public void printSchool(){
        if (school != null) {
            System.out.println(school);
        }
    }

}
