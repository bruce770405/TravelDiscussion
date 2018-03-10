package javaserver.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name="Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username,title,stopTag;
    private int levelId;
    private Date modifyTime,createTime;
    
    public Article() {
        super();
    }
    public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	
	
	public Article(String username, String title,int levelId,Date modifyTime,Date createTime,String stopTag) {
    	super();
        this.username = username;
        this.title = title;
        this.levelId = levelId;
        this.modifyTime = modifyTime;
        this.createTime = createTime;
        this.stopTag = stopTag;
    }

    
	public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
   
//    @Override
//    public String toString() {
//        return "data{" +
//                ", username='" + username + '\'' +
//                ", title='" + title + '\''+
//                ", body=" + body +
//                '}';
//    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getStopTag() {
		return stopTag;
	}
	public void setStopTag(String stopTag) {
		this.stopTag = stopTag;
	}
}