package javaserver.service;

import javaserver.controller.NewArticle;
import javaserver.entity.ArticleDetailEntity;
import javaserver.entity.ArticleEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * @author BruceHsu
 */
public interface ArticleService {

    static Logger logger = LogManager.getLogger(ArticleService.class);

    ArticleBo saveNewArticle(ArticleBo articleBo);

    ResponseEntity<?> deleteArticleStepDetail(Long id, Long stepId, String username);

    ResponseEntity<?> deleteArticle(Long id);

    ResponseEntity<?> findArticlesByLevel(int levelId);

    ResponseEntity<?> findAllArticle(Pageable page);

    ResponseEntity<?> findArticleDetailById(Long id);

    ResponseEntity<?> editArticleById(long id, ArticleEntity article);

    ResponseEntity<?> editArticleDetailById(long id, long stepId, ArticleDetailEntity article);
}
