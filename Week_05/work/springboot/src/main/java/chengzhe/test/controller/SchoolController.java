package chengzhe.test.controller;

import chengzhe.test.bean.Student;
import chengzhe.test.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ScfoolController
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/16 0:24
 */
@RestController
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/demo2")
    public String demo2() {
        schoolService.printSchool();
        return "yun";
    }

}
