package dz.xyz.loginproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CaptchImg {
    private String uuid;
    private String img;
}
