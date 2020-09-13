package javaserver.entity;


import javaserver.service.ArticleBo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author BruceHsu
 * article entity.
 */
@Table(name = "Article")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 發文者.
     */
    private String username;
    private String title;

    /**
     * 是否被刪文.
     */
    private Short stopTag;

    /**
     * 文章閱讀權限等級.
     */
    private Integer levelId;

    private Date modifyTime;
    private Date createTime;

    public ArticleBo convertToBo(ArticleDetailEntity detailEntity) {
        ArticleBo bo = new ArticleBo();
        BeanUtils.copyProperties(this, bo);
        bo.setDetailContext(detailEntity.getDetailContext());
        return bo;
    }
}