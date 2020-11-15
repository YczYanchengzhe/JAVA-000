package properties;

import bean.School;
import bean.Student;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Optional;

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
    private String className;
    private int studentId;
    private String studentName;
    private int classId;

}
