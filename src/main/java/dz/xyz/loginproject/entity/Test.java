package dz.xyz.loginproject.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

/**
 * (Test)表实体类
 *
 * @author dz
 * @since 2022-11-14 20:33:35
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@ApiModel(value = "Test对象", description = "")
@TableName("dz_test")
public class Test extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("名字")
    private String name;



}

