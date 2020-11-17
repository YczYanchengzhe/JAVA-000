package chengzhe.test;

import chengzhe.test.bean.School;
import chengzhe.test.properties.SchoolProperties;
import chengzhe.test.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName: chengzhe.test.SpringBootConfiguration
 * @Description: TODO
 * @Create by: A
 * @Date: 2020/11/15 23:48
 */
@Configuration
@ConditionalOnProperty(name = "school.enabled", havingValue = "true")
@EnableConfigurationProperties
public class SpringBootConfiguration {

    @Autowired
    private SchoolProperties schoolProperties;

    @Bean
    @ConditionalOnMissingBean
    public SchoolService toStudent() {
        SchoolService schoolService = new SchoolService();
        School school = schoolProperties.toSchool();
        schoolService.setSchool(school);
        return schoolService;
    }
}
