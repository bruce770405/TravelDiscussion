package javaserver.service.impl;

import javaserver.entity.ArticleDetailEntity;
import javaserver.entity.ArticleEntity;
import javaserver.entity.UserEntity;
import javaserver.error.RestfulException;
import javaserver.error.errorcode.ArticleErrorCode;
import javaserver.repository.ArticleDetailRepository;
import javaserver.repository.ArticleRepository;
import javaserver.repository.UserRepository;
import javaserver.service.ArticleService;
import javaserver.service.bo.ArticleBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service(value = "ArticleService")
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleDetailRepository articleDetailRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository article, ArticleDetailRepository articleDetail,
                              UserRepository user) {
        this.articleDetailRepository = articleDetail;
        this.articleRepository = article;
        this.userRepository = user;
    }

    @Override
    public ArticleBo saveNewArticle(ArticleBo articleBo) {
        Date systemDate = new Date();
        ArticleEntity entity = articleBo.convertToEntity();
        ArticleEntity returnArticle = articleRepository.save(entity);

        if (!Objects.isNull(returnArticle)) {
            ArticleDetailEntity detailEntity = ArticleDetailEntity.builder()
                    .detailContext(articleBo.getDetailContext())
                    .createTime(systemDate)
                    .modifyTime(systemDate)
                    .stepId(0L)
                    .stopTag((short) 0)
                    .username(returnArticle.getUsername())
                    .id(returnArticle.getId())
                    .build();
            ArticleDetailEntity returnDetail = articleDetailRepository.save(detailEntity);
            return returnArticle.convertToBo(returnDetail);
        } else {
            throw new RestfulException(ArticleErrorCode.ARTICLE_SAVE_FAIL);
        }

    }

    @Override
    public ResponseEntity<?> findArticlesByLevel(int levelId) {
        List<ArticleEntity> list = articleRepository.findByLevelId(levelId);
        // TODO Auto-generated method stub
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<?> findAllArticle(Pageable page) {
        Page<ArticleEntity> pageable = articleRepository.findAll(page);
        // TODO Auto-generated method stub
        return ResponseEntity.ok(pageable);
    }

    @Override
    public ResponseEntity<?> findArticleDetailById(Long id) {
        List<ArticleDetailEntity> list = articleDetailRepository.findAllById(id);
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<?> editArticleById(long id, ArticleEntity article) {
//        ArticleEntity contactUpdate = articleRepository.findById(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<?> deleteArticleStepDetail(Long id, Long stepId, String username) {
        // 先判斷是否有刪除權限
        Optional<UserEntity> optional = userRepository.findByUsername(username);
        UserEntity userEntity = optional.orElseThrow(() -> new RestfulException(ArticleErrorCode.ARTICLE_NOT_EXISTED));

        ArticleDetailEntity articleDetailEntity = articleDetailRepository.findByIdAndStepId(id, stepId);

        // 不是管理者 or 版主 --> 繼續檢查是否為發文者
        if (!userEntity.getRoles().equals("ADMIN") && !userEntity.getRoles().equals("BOARD_MANAGER")) {
            if (!articleDetailEntity.getUsername().equals(username)) {
                // 發文者才可以自刪
                throw new RestfulException(ArticleErrorCode.ARTICLE_DELETE_FAIL_BECAUSE_DONT_HAVE_AUTH);
            }
        }

        articleDetailRepository.deleteByIdAndStepId(id, stepId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<?> deleteArticle(Long id) {
        // 先判斷是否有刪除的權限
        // contactsRepository.f
        return null;
    }

    @Override
    public ResponseEntity<?> editArticleDetailById(long id, long stepId, ArticleDetailEntity article) {
        // TODO Auto-generated method stub

        return ResponseEntity.ok(null);

    }


}
