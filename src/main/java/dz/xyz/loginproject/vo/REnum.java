package dz.xyz.loginproject.vo;

import lombok.Getter;

@Getter
@SuppressWarnings("All")
public enum REnum {

    SUCCESS(200,"成功"),
    UNKNOWN(400, "未知错误"),
    BAD_SQL( 400, "sql语法错误"),
    JSON_PARSE_ERROR( 400, "json解析异常"),
    PARAM_ERROR( 400, "参数不正确"),
    FILE_UPLOAD_ERROR(500, "文件上传错误"),
    EXCEL_DATA_IMPORT_ERROR( 400, "Excel数据导入错误");

    private Integer code;

    private String message;

    private REnum( Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

