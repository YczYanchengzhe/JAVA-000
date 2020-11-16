package bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {
    /**
     * 学生id
     */
    private int id;
    /**
     * 学生姓名
     */
    private String name;


    public String sayHello(){
        return "StudentsProperties{" +
                "studentId=" + id +
                ", studentName='" + name + '\'' +
                '}';
    }
}
