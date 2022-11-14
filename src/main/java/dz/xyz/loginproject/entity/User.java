package dz.xyz.loginproject.entity;


import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

/**
 * 用户表(User)表实体类
 *
 * @author dz
 * @since 2022-11-14 19:28:42
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@Data
@ApiModel(value = "User对象", description = "")
public class User extends BaseEntity implements Serializable {
private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("账号状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phonenumber;

    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private String sex;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("用户类型（0管理员，1普通用户）")
    private String userType;

    @ApiModelProperty("创建人的用户id")
    private Long createBy;


    @ApiModelProperty("更新人")
    private Long updateBy;


    public User(String userName, String passwordEncoder) {
        this.userName = userName;
        this.password = passwordEncoder;
    }


}

