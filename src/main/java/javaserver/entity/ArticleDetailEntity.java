package javaserver.entity;

import javaserver.entity.key.ArticleMultiKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Article_Detail")
@IdClass(ArticleMultiKey.class)
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailEntity implements Serializable {
    @Id
    private Long id;

    /**
     * 主題的第幾篇內文.
     */
    @Id
    private Long stepId;

    /**
     * 作者.
     */
    private String username;

    /**
     * 內文.
     */
    private String detailContext;

    private Date createTime;

    private Date modifyTime;

    /**
     * 是否被停權.
     */
    private Short stopTag;
}
