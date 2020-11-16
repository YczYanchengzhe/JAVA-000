package properties;

import bean.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yanchengzhe
 * @auther: yanchengzhe
 * @Date: 2020/11/16 12:54
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "students")
public class StudentsProperties {
    private int studentId;
    private String studentName;

    public Student toStudent(){
        Student student = new Student();
        student.setId(studentId);
        student.setName(studentName);
        return student;
    }

}
