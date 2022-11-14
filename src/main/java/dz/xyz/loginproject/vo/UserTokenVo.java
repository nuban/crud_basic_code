package dz.xyz.loginproject.vo;

import dz.xyz.loginproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserTokenVo implements Serializable {
    private String token;
    private User userInfo;
}
