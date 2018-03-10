package javaserver.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String nickname;
	private String username;
	private String password;
	private String mail;
	private Date lastPasswordResetDate;
	private String roles;
	private String stopTag;
	private String gender;
	private int levelId;

	private String icon;
	
	public String getStopTag() {
		return stopTag;
	}

	public void setStopTag(String stopTag) {
		this.stopTag = stopTag;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public Login() {
		super();
	}

	public Login(String nickname,String username, String password, String gender, String mail, int levelId, Date lastPasswordResetDate,
			String roles, String stopTag) {
		super();
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.roles = roles;
		this.gender = gender;
		this.levelId = levelId;
		this.stopTag = stopTag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Long getId() {
		return Id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
