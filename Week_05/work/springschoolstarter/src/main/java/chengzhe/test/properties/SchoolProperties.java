package chengzhe.test.properties;

import chengzhe.test.bean.Klass;
import chengzhe.test.bean.School;
import chengzhe.test.bean.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: SchoolProperties
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 23:25
 */
@Data
@ConfigurationProperties(prefix = "school")
public class SchoolProperties {

    private String schoolName;
    private int studentId;
    private String studentName;
    private int classId;

    public School toSchool() {
        School school = new School();
        Klass klass = new Klass();
        klass.setClassId(classId);
        Student student = new Student();
        student.setId(studentId);
        student.setName(studentName);
        klass.setStudents(Collections.singletonList(student));
        school.setClass1(klass);
        school.setSchoolName(schoolName);
        return school;
    }

}
