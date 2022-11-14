package dz.xyz.loginproject.config;

import dz.xyz.loginproject.Handler.AuthenticationEntryPointImpl;
import dz.xyz.loginproject.intercepter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import dz.xyz.loginproject.Handler.AccessDenyHandlerImpl;

/**
 * 密码加密存入数据库，使用的是一个BCtyptPasswordEncoder 对象
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SequrityConfig extends WebSecurityConfigurerAdapter {
    //这是jwt的验证的过滤器
    @Autowired
    private JwtAuthenticationTokenFilter jwtFilter;

    //自定义异常处理器 ------ 授权
    @Autowired
    private AccessDenyHandlerImpl denyHandler;
    //自定义异常处理器 ------ 认证
    @Autowired
    private AuthenticationEntryPointImpl authenticationHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                //放行swagger
                .antMatchers("/swagger-ui/**").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/profile/**").anonymous()
                .antMatchers("/profile/**").anonymous()
                .antMatchers("/v3/**").anonymous()
                .antMatchers("/user/captch").anonymous()
                .antMatchers("/user/signin").anonymous()
                .antMatchers("/file/**").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        //把jwtfilter放在了UsernameandPassword...filer之前 （就是替换了默认的那个filter）
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //自定义的异常处理类
        http.exceptionHandling().authenticationEntryPoint(authenticationHandler).
                accessDeniedHandler(denyHandler);
//        springSecurity允许跨域
        http.cors();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean("password")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
