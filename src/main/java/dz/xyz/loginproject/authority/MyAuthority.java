package dz.xyz.loginproject.authority;

import dz.xyz.loginproject.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义的权限管理，更加灵活
 * component是加的名字是一个Spel表达式
 */
@Component("dz")
public class MyAuthority {

    public boolean hasAuthority(String permission){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        return permissions.contains(permission);
    }


}
