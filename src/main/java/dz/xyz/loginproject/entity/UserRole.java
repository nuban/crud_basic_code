package dz.xyz.loginproject.entity;


import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

/**
 * (UserRole)表实体类
 *
 * @author dz
 * @since 2022-11-14 19:06:32
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@ApiModel(value = "UserRole对象", description = "")
public class UserRole extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("角色id")
    private Long roleId;



}

