package dz.xyz.loginproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dz.xyz.loginproject.dao.UserRoleDao;
import dz.xyz.loginproject.entity.UserRole;
import dz.xyz.loginproject.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}

