package dz.xyz.loginproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.ArrayList;


@Configuration
public class SwaggerConfig {
    /**
     * 配置swagger的Docket bean
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30) // 指定swagger3.0版本
                .apiInfo(createApiInfo());
    }
    /**
     * 配置swagger的ApiInfo bean
     * @return
     */
    @Bean
    public ApiInfo createApiInfo(){
        return new ApiInfo("springsecurity写登录"
                ,"跟着三更做"
                ,"3.0"
                ,"http://www.xn--xhqt53a.xyz/"
                ,new Contact("东子", "http://www.xn--xhqt53a.xyz/",
                "2911890696@qq.com")
                ,"这啥"
                ,"http://www.apache.org/licenses/LICENSE-2.0"
                ,new ArrayList());
    }
}
