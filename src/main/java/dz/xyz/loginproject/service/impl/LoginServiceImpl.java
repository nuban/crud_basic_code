package dz.xyz.loginproject.service.impl;

import com.google.code.kaptcha.Producer;
import dz.xyz.loginproject.dto.UserDto;

import dz.xyz.loginproject.entity.LoginUser;
import dz.xyz.loginproject.entity.User;
import dz.xyz.loginproject.dao.Menumapper;
import dz.xyz.loginproject.entity.UserRole;
import dz.xyz.loginproject.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import dz.xyz.loginproject.service.Loginservice;
import dz.xyz.loginproject.entity.CaptchImg;
import dz.xyz.loginproject.dto.SignUser;
import dz.xyz.loginproject.dao.Usermapper;
import dz.xyz.loginproject.utils.Base64;
import dz.xyz.loginproject.utils.JwtUtil;
import dz.xyz.loginproject.utils.RedisCache;
import dz.xyz.loginproject.vo.ResultVo;
import dz.xyz.loginproject.vo.UserTokenVo;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements Loginservice {
    @Autowired
    private Menumapper menumapper;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private Usermapper mapper;
    //这是认证使用的对象
    @Autowired
    AuthenticationManager manager;

    //导入redis
    @Autowired
    private RedisCache redis;


    /**
     *     获取验证码
     */

    /**
     *     获取验证码
     */
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;
    /**
     * 认证的接口
     * @param user
     * @return
     */
    @Override
    public ResultVo login(UserDto user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //需要一个对象
        Authentication authenticate = manager.authenticate(token);

        //如果为空
        if(Objects.isNull(authenticate)){
        throw new RuntimeException("用户名或者密码错误");
        }
        //如果不为空
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user1 = loginUser.getUser();
        if("1".equals(user1.getStatus())){
            return new ResultVo(400,"该用户未审核");
        }
        String uid = user1.getId().toString();
        user1.setPassword(null);
        String jwt = JwtUtil.createJWT(uid);
        UserTokenVo usertoken = new UserTokenVo(jwt, user1);

        //存入redis
        redis.setCacheObject("login:"+uid,loginUser);
        return new ResultVo(200,"登录成功",usertoken);
    }

    @Override
    public ResultVo logout() {
        //从authenticatiion中拿到值
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        Long id = principal.getUser().getId();
        //删除redis中的键值对
        boolean result = redis.deleteObject("login:" + id);
        if(result == true){
            return new ResultVo(200,"注销成功");
        }
        return null;
    }

    @Override
    public ResultVo getCaptch() {
        //生成算式
        String capText = captchaProducerMath.createText();
        String  capStr = capText.substring(0, capText.lastIndexOf("@"));
        String code = capText.substring(capText.lastIndexOf("@") + 1);
        BufferedImage image = captchaProducerMath.createImage(capStr);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (Exception e) {
            log.info("验证码写入失败");
        }

        //生成随机的键
        UUID uuid =UUID.randomUUID();
        String sUuid = uuid.toString();
        String varify = "codeimg_"+sUuid;
        //图片
        String base64img = Base64.encode(os.toByteArray());
        CaptchImg captchImg = new CaptchImg(varify, base64img);
        //存入redis 3分钟过期
        redis.setCacheObject(varify,code,5, TimeUnit.MINUTES);
        ResultVo result = new ResultVo(200, "验证码响应成功", captchImg);
        return result;
    }

    @Override
    public ResultVo signIn(SignUser signUser) {

        String code = redis.getCacheObject(signUser.getVarifikey());
        if(null == code || !code.equals(signUser.getSurecode())){
            return new ResultVo(400,"验证码错误");
        }

        //遇到重名
        User user = mapper.findbyName(signUser.getUserName());
        if(user != null){
            return new ResultVo(500,"用户已注册");
        }
        //加密密码
        PasswordEncoder pass = new BCryptPasswordEncoder();
        String passwordEncoder = pass.encode(signUser.getPassword());

        User registerUser = new User(signUser.getUserName(), passwordEncoder);
        int insert = mapper.insert(registerUser);
        if(insert != 0){
            Long id = registerUser.getId();
            UserRole userRole = new UserRole();
            userRole.setRoleId(2L);
            userRole.setUserId(id);
            userRoleService.save(userRole);
            return new ResultVo(200,"注册成功");
        }
        return new ResultVo(500,"注册失败，联系管理员。");
    }

}
