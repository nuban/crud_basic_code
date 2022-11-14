package dz.xyz.loginproject.controller;

import com.alibaba.fastjson.JSON;
import dz.xyz.loginproject.service.Loginservice;
import dz.xyz.loginproject.dto.UserDto;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import dz.xyz.loginproject.dto.SignUser;
import dz.xyz.loginproject.vo.ResultVo;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags="用户登录的接口")
public class Logincontroller {

    @Autowired
    private Loginservice login;

    @ApiOperation("跨域的配置")
    @PostMapping("/testCors")
    @ResponseBody
    @PreAuthorize("@dz.hasAuthority('system:dept:list1')") //这里使用了自定义的异常处理
    public String testCors(){
        ResultVo cors = new ResultVo(200, "测试成功");
        String result = JSON.toJSONString(cors);
        return result;
    }

    @GetMapping
    @ResponseBody
    //有handsome才能访问这个注解
    @PreAuthorize("hasAuthority('admin')")
    //swagger的接口测试
    @ApiOperation("helloworld接口测试")
    public String gethello(){
        return "hello world";
    }

    /**
     * 登录接口
     * @param user 表示从请求体中去拿
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录方法")
    public ResultVo login(@Valid @RequestBody UserDto user){

        return login.login(user);
    }

    @GetMapping("/logout")
    @ApiOperation("退出登录")
    public ResultVo logout(){
        try {
            return login.logout();
        } catch (Exception e) {
            return ResultVo.Fail("用户登录出错");
        }
    }
    @GetMapping("/captch")
    public ResultVo getCaptch(){
        try {
            return login.getCaptch();
        } catch (Exception e) {
            return ResultVo.Fail("获取验证码失败");
        }
    }


    @PostMapping("signin")
    public ResultVo signin(@Valid @RequestBody SignUser signUser){
        try {
            return login.signIn(signUser);
        } catch (Exception e) {
            System.out.println(e);
            return ResultVo.Fail("用户注册失败");
        }
    }





}
