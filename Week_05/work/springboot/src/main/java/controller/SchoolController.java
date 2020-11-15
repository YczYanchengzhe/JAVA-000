package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SchoolService;

import javax.annotation.Resource;

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

    @GetMapping("/demo")
    public String demo() {
        schoolService.printSchool();
        return "true";
    }
}
