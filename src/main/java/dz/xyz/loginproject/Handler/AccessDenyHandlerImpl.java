package dz.xyz.loginproject.Handler;

import com.alibaba.fastjson.JSON;
import dz.xyz.loginproject.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import dz.xyz.loginproject.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义授权失败的处理器
 */
@Component
public class AccessDenyHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultVo result = new ResultVo(403, "检查用户是否有响应的权限");
        String jsonResult = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse,jsonResult);
    }
}
