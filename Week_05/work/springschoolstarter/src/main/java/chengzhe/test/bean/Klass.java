package chengzhe.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Klass {
    /**
     * 班级的学生列表
     */
    List<Student> students;
    /**
     * 班级序号
     */
    int classId;

    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
