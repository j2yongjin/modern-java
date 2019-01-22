package modernjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import static org.springframework.boot.SpringApplication.*;

/**
 * Created by yjlee on 2018-12-13.
 */
@SpringBootApplication
public class SpringTestApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext configurableApplicationContext = run(SpringTestApplication.class,args);
        TestService  testService = (TestService) configurableApplicationContext.getBean("testService");
        Test2Service test2Service = (Test2Service) configurableApplicationContext.getBean("test2Service");

        System.out.println(testService);

        testService.add();
        testService.remove();
        testService.addAll();

        test2Service.add();
        test2Service.remove();
        test2Service.addAll();
    }

}

