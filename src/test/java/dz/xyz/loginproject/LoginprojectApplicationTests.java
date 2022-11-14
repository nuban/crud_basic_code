package dz.xyz.loginproject;

import dz.xyz.loginproject.entity.User;
import dz.xyz.loginproject.dao.Menumapper;
import dz.xyz.loginproject.dao.Usermapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class LoginprojectApplicationTests {

    @Autowired
    private Menumapper menumapper;

    @Autowired
    private Usermapper usermapper;

//    @Resource(name = "password")报错但不影响
    @Autowired
    private BCryptPasswordEncoder pass;

    @Test
    void contextLoads() {
        User user = usermapper.selectById(1);
        System.out.println(user);
    }
    @Test
    public void testpassword(){
        String password = "123456";
        String encode = pass.encode(password);
        System.out.println(encode);
        System.out.println(pass.matches(password,encode));
    }

    /**
     * 测试从数据库查询权限
     */
    @Test
    public void Testmenumapper(){
        System.out.println(menumapper.findByUserid(1L));
    }
}
