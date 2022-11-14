package dz.xyz.loginproject.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 这是一个登录的Userdetails返回对象
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private  User user;

    @JSONField(serialize = false) //表示这是一个不放入json的序列化的对象
    private List<GrantedAuthority> authorities;

    //封装权限信息
    private List<String> permissions;
    public LoginUser(User user, List<String> permission) {
        this.user = user;
        this.permissions = permission;
    }

    //实际上的权限信息是从这个方法中拿到的
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        for (String permission : permissions) {
//            authorities.add(new SimpleGrantedAuthority(permission));
//        }
        /**
         * stream流的方式封装
         */
        authorities = permissions.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    //token是不是过期了
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是不是被锁了
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账号是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
