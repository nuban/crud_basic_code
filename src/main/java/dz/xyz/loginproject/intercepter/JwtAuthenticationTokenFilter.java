package dz.xyz.loginproject.intercepter;

import dz.xyz.loginproject.entity.LoginUser;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import dz.xyz.loginproject.utils.JwtUtil;
import dz.xyz.loginproject.utils.RedisCache;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这是一个存在于最开始的拦截器，对用户的jwt进行认证的
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redis;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        //获取token ,解析token
        String token = httpServletRequest.getHeader("token");
        if(StringUtil.isNullOrEmpty(token)){
            //放行，因为是没有context的信息，所以还是很安全
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //验证token
        String userid = null;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            //token不合法抛出异常
            throw new RuntimeException("token非法");
        }
        LoginUser user = null;
        try{
            String key = "login:"+userid;
            user = redis.getCacheObject(key);
            if(user == null){
                filterChain.doFilter(httpServletRequest,httpServletResponse);
                return;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("用户登录出错");
        }

        //token合法从redis中得到数据 存入SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        UsernamePasswordAuthenticationToken userinfo = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userinfo);

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
