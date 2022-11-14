package dz.xyz.loginproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dz.xyz.loginproject.entity.Files;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileMapper extends BaseMapper<Files> {
}