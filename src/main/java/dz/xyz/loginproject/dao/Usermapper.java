package dz.xyz.loginproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dz.xyz.loginproject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface Usermapper extends BaseMapper<User> {
    @Select("select *from sys_user where user_name = #{userName}")
    User findbyName(String userName);
}
