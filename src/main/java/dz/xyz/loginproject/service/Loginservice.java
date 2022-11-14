package dz.xyz.loginproject.service;


import dz.xyz.loginproject.dto.UserDto;
import dz.xyz.loginproject.dto.SignUser;
import dz.xyz.loginproject.vo.ResultVo;

public interface Loginservice {
    ResultVo login(UserDto user);

    ResultVo logout();

    ResultVo getCaptch();

    ResultVo signIn(SignUser signUser);

}
