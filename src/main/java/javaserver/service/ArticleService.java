package javaserver.service;

import javaserver.entity.ArticleDetailEntity;
import javaserver.entity.ArticleEntity;
import javaserver.service.bo.ArticleBo;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * @author BruceHsu
 */
public interface ArticleService {

    ArticleBo saveNewArticle(ArticleBo articleBo);

    ResponseEntity<?> deleteArticleStepDetail(Long id, Long stepId, String username);

    ResponseEntity<?> deleteArticle(Long id);

    ResponseEntity<?> findArticlesByLevel(int levelId);

    ResponseEntity<?> findAllArticle(Pageable page);

    ResponseEntity<?> findArticleDetailById(Long id);

    ResponseEntity<?> editArticleById(long id, ArticleEntity article);

    ResponseEntity<?> editArticleDetailById(long id, long stepId, ArticleDetailEntity article);
}
