package javaserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javaserver.model.ArticleDetail;


/**
 * 
 * @author BruceHsu
 *
 * 控制文章詳細內容
 */
public interface ArticleDetailJpaRepository extends JpaRepository<ArticleDetail,Long>{

	List<ArticleDetail> findById(long id);
	ArticleDetail findByIdAndStepId(long id,long stepId);
	int deleteByIdAndStepId(long id,long stepId);
}
