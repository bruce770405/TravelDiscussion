package javaserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;

    @Column(name = "last_password_reset_date")
    private Date lastPasswordResetDate;

    @Column(name = "roles")
    private String roles;

    @Column(name = "stop_tag")
    private Short stopTag;

    @Column(name = "gender")
    private String gender;

    @Column(name = "level_id")
    private Integer levelId;

    @Column(name = "icon")
    private String icon;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_login_date")
    private Date lastLoginDate;
}
