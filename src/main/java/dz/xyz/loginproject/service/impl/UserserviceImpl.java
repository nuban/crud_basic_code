package dz.xyz.loginproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import dz.xyz.loginproject.entity.LoginUser;
import dz.xyz.loginproject.entity.User;
import dz.xyz.loginproject.dao.Menumapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import dz.xyz.loginproject.dao.Usermapper;

import java.util.List;

/**
 * 这里主要是替换了springSecurity 中的InmerneryUserDetailsManager 实现从数据库中查询用户信息
 */
@Service
public class UserserviceImpl implements UserDetailsService {
    @Autowired
    private Menumapper menumapper;

    @Autowired
    private Usermapper usermapper;

    /**
     * 根据用户名去比对相关的信息是否真确
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过LanmdaQueryWrapper对象作为条件查询密码
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUserName,username);
        User user = usermapper.selectOne(wrapper);

        if(user == null){
            throw new RuntimeException("没找到该用户");
        }

        /**
         * 数据库查询 权限信息(多表查询，自己写sql)
         */
        List<String> permissions = menumapper.findByUserid(user.getId());
        //找到了，封装Userdetails对象
        LoginUser loginUser = new LoginUser(user,permissions);
        return loginUser;
    }
}
