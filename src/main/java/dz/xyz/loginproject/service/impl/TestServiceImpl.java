package dz.xyz.loginproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dz.xyz.loginproject.dao.TestDao;
import dz.xyz.loginproject.entity.Test;
import dz.xyz.loginproject.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestDao, Test> implements TestService {

}

