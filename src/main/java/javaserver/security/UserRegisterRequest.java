package javaserver.security;

import javaserver.controller.dto.FileUploadDto;
import javaserver.entity.UserEntity;
import lombok.Data;

import java.util.Date;

@Data
public class UserRegisterRequest {
    private String nickname;
    private String username;
    private String password;
    private String mail;
    private String roles;
    private String gender;
    private Integer levelId;
    private FileUploadDto icon;

    public UserEntity convertToEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(password);
        userEntity.setGender(gender);
        userEntity.setIcon(icon.getBody());
        userEntity.setFileName(icon.getFileName());
        userEntity.setLastPasswordResetDate(new Date());
        userEntity.setMail(mail);
        userEntity.setNickname(nickname);
        userEntity.setUsername(username);
        userEntity.setStopTag((short) 0);
        return userEntity;
    }
}
