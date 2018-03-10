package javaserver.model.combine;

import java.util.Date;

public class NewArticleModel {

	private String title,detailContext,stopTag,username;
	private Date modifyTime,createTime;
	private int levelId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	
}
