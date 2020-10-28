package javaserver.repository;

import javaserver.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author BruceHsu
 * article 的 dao
 */
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    List<ArticleEntity> findByUsername(String username);

    List<ArticleEntity> findByLevelId(int levelId);
}
