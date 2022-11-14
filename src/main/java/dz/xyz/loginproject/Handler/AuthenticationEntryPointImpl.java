package dz.xyz.loginproject.Handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import dz.xyz.loginproject.utils.WebUtils;
import dz.xyz.loginproject.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败就会抛出这个异常
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String uri = httpServletRequest.getRequestURI();
        if(uri.equals("/user/login")){
            ResultVo result = new ResultVo(401, "用户认证失败");
            String jsonResult = JSON.toJSONString(result);
            WebUtils.renderString(httpServletResponse,jsonResult);
            return;
        }
        log.info("出错路径：[{}]",uri);
        ResultVo result1 = new ResultVo(404,"请求出错");
        String result2 = JSON.toJSONString(result1);
        WebUtils.renderString(httpServletResponse,result2);

    }
}
