package javaserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name= "Article_Detail")
@IdClass(ArticleMultiKeys.class)
public class ArticleDetail  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4094158907079951050L;
	@Id
	private Long id;
	@Id
	private Long stepId;
	private String username;
	private Date createTime;
	private Date modifyTime;
	private String detailContext, stopTag;


	public ArticleDetail() {
		super();
	}
	
	public ArticleDetail(Long id,Long stepId, String username, String detailContext, String stopTag, Date createTime,
			Date modifyTime) {
		super();
		this.id = id;
		this.stepId = stepId;
		this.username = username;
		this.detailContext = detailContext;
		this.stopTag = stopTag;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
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



	public String getDetailContext() {
		return detailContext;
	}

	public void setDetailContext(String detailContext) {
		this.detailContext = detailContext;
	}

	public String getStopTag() {
		return stopTag;
	}

	public void setStopTag(String stopTag) {
		this.stopTag = stopTag;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((username == null) ? 0 : username.hashCode());
//		// result = prime * result + ((startCity == null) ? 0 : startCity.hashCode());
//		return result;
//	}

}
