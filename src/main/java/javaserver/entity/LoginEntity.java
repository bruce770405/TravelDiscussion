package javaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
