package dz.xyz.loginproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dz.xyz.loginproject.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Menumapper extends BaseMapper<Menu> {

    //实现查询权限
    List<String> findByUserid(Long userid);
}
