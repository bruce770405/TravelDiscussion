package javaserver.controller.dto;

import javaserver.service.bo.ArticleBo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * <p>
 * 發表新文章所需model.
 * </p>
 *
 * @author BruceHsu
 * @version 1.0, 2018年5月27日
 */
@Data
public class ArticleDto {

    /**
     * 文章標題.
     */
    private String title;
    /**
     * 文章內容.
     */
    private String detailContext;
    /**
     * 是否停止閱讀.
     */
    private String stopTag;
    /**
     * 修改時間.
     */
    private Date modifyTime;
    /**
     * 創建時間.
     */
    private Date createTime;
    /**
     * 閱讀等級.
     */
    private int levelId;

    public ArticleBo convertToBo() {
        ArticleBo bo = new ArticleBo();
        BeanUtils.copyProperties(this, bo);
        return bo;
    }

}
