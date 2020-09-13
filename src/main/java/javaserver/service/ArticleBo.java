package javaserver.service;

import javaserver.controller.dto.ArticleDto;
import javaserver.entity.ArticleEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class ArticleBo {
    private String title;
    private String detailContext;
    private String stopTag;
    private String username;
    private Date modifyTime, createTime;
    private Integer levelId;

    public ArticleEntity convertToEntity() {
        ArticleEntity entity = new ArticleEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }

    public ArticleDto convertToDto() {
        ArticleDto dto = new ArticleDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
