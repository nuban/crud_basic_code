package dz.xyz.loginproject.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


//不为空封装为json返回
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResultVo {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private Object data;

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultVo(REnum emum) {
        this.code = emum.getCode();
        this.msg = emum.getMessage();
    }

    public static ResultVo Success(Object data) {
        return new ResultVo(200,"操作成功", data);
    }

    public static ResultVo Success(Object data, String msg) {
        return new ResultVo(200,msg, data);
    }

    public static ResultVo Fail() {
        return new ResultVo(500,"操作失败");
    }

    public static ResultVo Fail(String msg) {
        return new ResultVo(500,msg);
    }


    public static ResultVo Result(REnum emum){
        return new ResultVo(emum);
    }


    public ResultVo(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}