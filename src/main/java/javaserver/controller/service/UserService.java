package javaserver.controller.service;

import javaserver.model.Article;
import javaserver.model.combine.NewArticleModel;
import javaserver.model.combine.ResultStatusModel;

public interface UserService {

	ResultStatusModel saveNewArticle(NewArticleModel model);
	ResultStatusModel findArticlesByLevel(int levelId);
	ResultStatusModel findAllArticle();
	ResultStatusModel findArticleDetailById(Long id);
	ResultStatusModel editArticleById(long id,Article article);
}
