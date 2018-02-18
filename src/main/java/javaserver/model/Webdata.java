package javaserver.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name="webdata")
public class Webdata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username,title,body;
    public Webdata() {
//        super();
    }
    public Webdata(String username, String title,String body) {
//    	super();
        this.username = username;
        this.title = title;
        this.body = body;
    }

    public long getId() {
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}