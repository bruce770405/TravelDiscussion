package javaserver.controller.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javaserver.model.Article;
import javaserver.model.ArticleDetail;
import javaserver.model.Login;
import javaserver.model.combine.NewArticleModel;
import javaserver.model.combine.ResultStatusModel;
import javaserver.repository.ArticleDetailJpaRepository;
import javaserver.repository.DataJpaRepository;
import javaserver.repository.UserJpaRepository;


@Service
public class UserServiceImpl implements UserService{

	DataJpaRepository articleRepository;	
	ArticleDetailJpaRepository articleDetailRepository;
	UserJpaRepository userRepository;
	
	@Autowired
	public UserServiceImpl(DataJpaRepository article,ArticleDetailJpaRepository articleDetail,UserJpaRepository user) {
		this.articleDetailRepository = articleDetail;
		this.articleRepository = article;
		this.userRepository = user;
	}
	
	
	@Override
	public ResponseEntity<?> saveNewArticle(NewArticleModel model) {
		Date now = new Date();
//		String username, String title,int levelId,Date modifyTime,Date createTime,String stopTag
		Article articleInput = new Article(model.getUsername(),model.getTitle(),model.getLevelId(),null,now,"");
		Article returnArticle = articleRepository.save(articleInput);
//		long id, String username, String detailContext, String stopTag, Date createTime,Date modifyTime
		if(returnArticle != null) {
			long stepId = 0;
			ArticleDetail detailInput = new ArticleDetail(returnArticle.getId(),stepId,returnArticle.getUsername(),model.getDetailContext(),"",returnArticle.getCreateTime(),null);
			ArticleDetail returnDetail = articleDetailRepository.save(detailInput);
			if(returnDetail != null) {
				model.setStopTag("");
				model.setCreateTime(returnArticle.getCreateTime());
				model.setModifyTime(returnArticle.getModifyTime());
				 return ResponseEntity.ok(new ResultStatusModel(true,"文章發佈成功",model));
			}
		}
		return new ResponseEntity<>(new ResultStatusModel(false,"發佈失敗",null),HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> findArticlesByLevel(int levelId) {
		List<Article> list = articleRepository.findByLevelId(levelId);
		return ResponseEntity.ok(new ResultStatusModel(true,"搜尋結束",list));
	}

	@Override
	public ResponseEntity<?> findAllArticle() {
		List<Article> list = articleRepository.findAll();
		return ResponseEntity.ok(new ResultStatusModel(true,"搜尋完成，總搜尋數："+list.size(),list));
	}

	@Override
	public ResponseEntity<?> findArticleDetailById(Long id) {
		List<ArticleDetail> list = articleDetailRepository.findById(id);
		if(list != null)
		    return ResponseEntity.ok(new ResultStatusModel(true,"搜尋完成，回傳資料",list));
		else
			return new ResponseEntity<>(new ResultStatusModel(false,"搜尋錯誤，請檢查文章編號",null),HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> editArticleById(long id,Article article) {
		Article contactUpdate = articleRepository.findById(id);
		if(contactUpdate == null)
			return new ResponseEntity<>(new ResultStatusModel(false,"無指定文章編號",null),HttpStatus.BAD_REQUEST);
		contactUpdate.setTitle(article.getTitle());
		contactUpdate.setModifyTime(new Date());
		contactUpdate.setStopTag(article.getStopTag());
		contactUpdate.setLevelId(article.getLevelId());
//		contactUpdate.set
		if(articleRepository.save(contactUpdate) != null)
	    return ResponseEntity.ok(new ResultStatusModel(true,"修改完成",contactUpdate));
		else
			return new ResponseEntity<>(new ResultStatusModel(false,"修改有問題，請確認",null),HttpStatus.BAD_REQUEST);
	}

	
	@Override
	public ResponseEntity<?> deleteArticleStepDetail(Long id,Long stepId,String username) {
		//先判斷是否有刪除權限
		Login user = userRepository.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<>(new ResultStatusModel(false,"查無此用戶資料",null),HttpStatus.BAD_REQUEST);
		}else {
			int levelId = user.getLevelId(); //之後可能需要由等級或帳號admin判定能否刪文
			ArticleDetail detail = articleDetailRepository.findByIdAndStepId(id, stepId);
			if(!detail.getUsername().equals(username)) {
				return new ResponseEntity<>(new ResultStatusModel(false,"此用戶沒有權限刪除此貼文",null),HttpStatus.BAD_REQUEST);
			};
			//檢查是否為發文者，是的話才能刪除
			int count = articleDetailRepository.deleteByIdAndStepId(id, stepId);
			return new ResponseEntity<>(new ResultStatusModel(true,"操作完成，刪除 "+count+" 筆資料",null),HttpStatus.OK);	
		}
	}

	@Override
	public ResponseEntity<?> deleteArticle(Long id) {
		//先判斷是否有刪除的權限
//		contactsRepository.f
         return null;
	}

}
