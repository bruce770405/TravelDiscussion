package javaserver.controller.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaserver.model.Article;
import javaserver.model.ArticleDetail;
import javaserver.model.combine.NewArticleModel;
import javaserver.model.combine.ResultStatusModel;
import javaserver.repository.ArticleDetailJpaRepository;
import javaserver.repository.DataJpaRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	DataJpaRepository contactsRepository;
	@Autowired
	ArticleDetailJpaRepository articleDetailRepository;
	
	@Override
	public ResultStatusModel saveNewArticle(NewArticleModel model) {
		Date now = new Date();
//		String username, String title,int levelId,Date modifyTime,Date createTime,String stopTag
		Article articleInput = new Article(model.getUsername(),model.getTitle(),model.getLevelId(),null,now,"");
		Article returnArticle = contactsRepository.save(articleInput);
//		long id, String username, String detailContext, String stopTag, Date createTime,Date modifyTime
		if(returnArticle != null) {
			ArticleDetail detailInput = new ArticleDetail(returnArticle.getId(),returnArticle.getUsername(),model.getDetailContext(),"",returnArticle.getCreateTime(),null);
			ArticleDetail returnDetail = articleDetailRepository.save(detailInput);
			if(returnDetail != null) {
				model.setStopTag("");
				model.setCreateTime(returnArticle.getCreateTime());
				model.setModifyTime(returnArticle.getModifyTime());
				 return new ResultStatusModel(true,"文章發佈成功",model);
			}
		}
		return new ResultStatusModel(false,"發佈失敗",null);
	}

	@Override
	public ResultStatusModel findArticlesByLevel(int levelId) {
		List<Article> list = contactsRepository.findByLevelId(levelId);
		return new ResultStatusModel(true,"搜尋結束",list);
	}

	@Override
	public ResultStatusModel findAllArticle() {
		List<Article> list = contactsRepository.findAll();
		list.forEach(e -> {
			System.out.println(e.getCreateTime());
		});
		return new ResultStatusModel(true,"搜尋完成，總搜尋數："+list.size(),list);
	}

	@Override
	public ResultStatusModel findArticleDetailById(Long id) {
		List<ArticleDetail> list = articleDetailRepository.findById(id);
		if(list != null)
		    return new ResultStatusModel(true,"搜尋完成，回傳資料",list);
		else
			return new ResultStatusModel(false,"搜尋錯誤，請檢查文章編號",null);
	}

	@Override
	public ResultStatusModel editArticleById(long id,Article article) {
		Article contactUpdate = contactsRepository.findById(id);
		if(contactUpdate == null)
			return new ResultStatusModel(false,"無指定文章編號",null);
		contactUpdate.setTitle(article.getTitle());
		contactUpdate.setModifyTime(new Date());
		contactUpdate.setStopTag(article.getStopTag());
		contactUpdate.setLevelId(article.getLevelId());
//		contactUpdate.set
		if(contactsRepository.save(contactUpdate) != null)
	    return new ResultStatusModel(true,"修改完成",contactUpdate);
		else
			return new ResultStatusModel(false,"修改有問題，請確認",null);
	}

}
