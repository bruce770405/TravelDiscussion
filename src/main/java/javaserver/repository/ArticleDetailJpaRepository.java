package javaserver.repository;

import javaserver.entity.ArticleDetailEntity;
import javaserver.entity.key.ArticleMultiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDetailJpaRepository extends JpaRepository<ArticleDetailEntity, ArticleMultiKey> {

    List<ArticleDetailEntity> findAllById(Long id);

    ArticleDetailEntity findByIdAndStepId(Long id, Long stepId);

    int deleteByIdAndStepId(Long id, Long stepId);
}
