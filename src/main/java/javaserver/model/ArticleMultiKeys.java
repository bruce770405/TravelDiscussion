package javaserver.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author BruceHsu
 * 
 * @Param Id
 * @Param username
 * @Param createTime 
 * 
 * 多key連結
 */
public class ArticleMultiKeys implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7075314889231294987L;

	private Long id;
	private String username;
	private Date createTime;

	public ArticleMultiKeys() {
		super();
	}

	public ArticleMultiKeys(Long id, String username, Date createTime) {
		this.id = id;
		this.username = username;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result + ((username == null) ? 0 : username.hashCode());
		result = PRIME * result + ((createTime == null) ? 0 : createTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final ArticleMultiKeys other = (ArticleMultiKeys) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		if (createTime == null) {
			if (other.createTime != null) {
				return false;
			}
		} else if (!createTime.equals(other.createTime)) {
			return false;
		}

		return true;
	}
}
