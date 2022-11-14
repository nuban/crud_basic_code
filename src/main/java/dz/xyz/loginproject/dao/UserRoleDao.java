package dz.xyz.loginproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import dz.xyz.loginproject.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRoleDao extends BaseMapper<UserRole> {

/**
* 批量新增数据
* @return 影响行数
*/
int insertBatch(@Param("entities") List<UserRole> entities);

/**
* 批量新增或按主键更新数据,记得校验list 不应为空
* @return 影响行数
*/
int insertOrUpdateBatch(@Param("entities") List<UserRole> entities);

}

