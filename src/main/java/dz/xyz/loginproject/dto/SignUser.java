package dz.xyz.loginproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 注册dto
 */
@Data
public class SignUser {
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String surecode;
    @NotBlank(message = "varifikey不能为空")
    private String varifikey;
}