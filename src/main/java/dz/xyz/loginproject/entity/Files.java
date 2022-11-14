package dz.xyz.loginproject.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * (File)表实体类
 *
 * @author dz
 * @since 2022-11-13 20:13:41
 */
@SuppressWarnings("serial")
@Data
@ApiModel(value = "File对象", description = "")
@TableName("sys_file")
public class Files extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("文件大小(kb)")
    private Long size;

    @ApiModelProperty("下载链接")
    private String url;

    @ApiModelProperty("文件md5")
    private String md5;

    @ApiModelProperty("是否删除")
    private Integer isDelete;

    @ApiModelProperty("是否禁用链接")
    private Integer enable;

    @ApiModelProperty("是否删除")
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;
}

