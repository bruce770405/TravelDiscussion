package javaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javaserver.model.ArticleDetail;

public interface ArticleDetailJpaRepository extends JpaRepository<ArticleDetail,Long>{

	ArticleDetail findById(long id);
}
