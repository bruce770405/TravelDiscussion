package javaserver.security;

import javaserver.entity.LoginEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class UserRegisterRequest {
    private Long Id;
    private String nickname;
    private String username;
    private String password;
    private String mail;
    private Date lastPasswordResetDate;
    private String roles;
    private Short stopTag;
    private String gender;
    private Integer levelId;
    private String icon;

    public LoginEntity convertToEntity() {
        LoginEntity loginEntity = new LoginEntity();
        BeanUtils.copyProperties(this, loginEntity);
        return loginEntity;
    }
}
