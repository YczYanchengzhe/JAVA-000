import bean.Klass;
import bean.School;
import bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import properties.SchoolProperties;
import service.SchoolService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SpringBootConfiguration
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 23:48
 */
@EnableConfigurationProperties(SchoolProperties.class)
@ConditionalOnProperty(name = "school.enabled", havingValue = "true")
@Configuration
public class SpringBootConfiguration {

    @Autowired
    private SchoolProperties schoolProperties;

    @ConditionalOnMissingBean
    @Bean
    SchoolService schoolBeanProcessor() {
        Student student = new Student(schoolProperties.getStudentId(), schoolProperties.getStudentName());
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        Klass klass = new Klass(studentList, schoolProperties.getClassId());
        School school = new School(klass, schoolProperties.getSchoolName());
        return new SchoolService(school);
    }
}
