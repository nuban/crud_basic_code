package dz.xyz.loginproject;

import dz.xyz.loginproject.entity.Files;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@MapperScan("dz.xyz.loginproject.dao")
@EnableOpenApi //开启swagger
@SpringBootApplication
public class LoginprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginprojectApplication.class, args);
        System.out.println("swagger地址： http://localhost/swagger-ui/index.html");
    }

}
