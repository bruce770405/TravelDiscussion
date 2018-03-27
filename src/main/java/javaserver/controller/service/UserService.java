package javaserver.controller.service;

import org.springframework.http.ResponseEntity;

import javaserver.model.Article;
import javaserver.model.combine.NewArticleModel;

public interface UserService {

	ResponseEntity<?> saveNewArticle(NewArticleModel model);
	ResponseEntity<?> deleteArticleStepDetail(Long id,Long stepId,String username);
	ResponseEntity<?> deleteArticle(Long id);
	
	ResponseEntity<?> findArticlesByLevel(int levelId);
	ResponseEntity<?> findAllArticle();
	ResponseEntity<?> findArticleDetailById(Long id);
	ResponseEntity<?> editArticleById(long id,Article article);
}
