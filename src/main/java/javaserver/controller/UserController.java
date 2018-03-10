package javaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javaserver.controller.service.UserService;
import javaserver.model.combine.NewArticleModel;
import javaserver.model.combine.ResultStatusModel;

/**
 * 
 * @author BruceHsu
 * 
 */
@Api("控制 USER 發文資訊 ")
@RestController
@RequestMapping("/article")
@PreAuthorize("hasRole('USER ')")
public class UserController {

	
	@Autowired
	UserService service;
	
	// 新增
	@ApiOperation("參數:文章detail @新增文章")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "remove", name = "id", dataType = "long", required = true, value = "發文者指定文章編號", defaultValue = ""), 
		@ApiImplicitParam(paramType="remove", name="userName", dataType="String", required = true, value="發文者身份", defaultValue="")
    })
    @ApiResponses({ @ApiResponse(code = 401, message = "驗證不通過"), @ApiResponse(code = 400, message = "請求參數不正確"),
		@ApiResponse(code = 404, message = "頁面不正確") })

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResultStatusModel saveNewArticle(@RequestBody NewArticleModel model) {
		return service.saveNewArticle(model);
	}

	// 更新\
//	@ApiOperation("參數:文章detail @更新文章")
//	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
//	public Article saveUpdatedContact(@PathVariable("id") long id,@RequestBody Article webdata) {
//		Article contactUpdate = contactsRepository.findById(id);
//		contactUpdate.setTitle(webdata.getTitle());
//		contactUpdate.setDetail(webdata.getDetail());
//		return contactsRepository.save(contactUpdate);
//	}

	
	
	// 刪除
//	@ApiOperation("參數:文章編號 @刪除使用者指定文章")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "remove", name = "id", dataType = "long", required = true, value = "發文者指定文章編號", defaultValue = ""), 
//			@ApiImplicitParam(paramType="remove", name="userName", dataType="String", required = true, value="發文者身份", defaultValue="")
//	})
//	@ApiResponses({ @ApiResponse(code = 401, message = "驗證不通過"), @ApiResponse(code = 400, message = "請求參數不正確"),
//			@ApiResponse(code = 404, message = "頁面不正確") })
//	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
//	public void removeContact(@PathVariable("id")long id,String userName) {
//		Article article = contactsRepository.findById(id);
//		if(article.getUsername().equals(userName))
//		   this.contactsRepository.delete(id);
//	}


//	@ApiOperation("參數:文章編號 @回傳該文章詳細內容")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "發文者姓名", defaultValue = "") })
//	@ApiResponses({ @ApiResponse(code = 401, message = "驗證不通過"), @ApiResponse(code = 400, message = "請求參數不正確"),
//			@ApiResponse(code = 404, message = "頁面不正確") })
//	//@PreAuthorize("hasRole('USER ')")
//	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
//	public ResultStatusModel findArticleById(@PathVariable("id")long id) {
//		List<ArticleDetail> detail = articleDetailRepository.findById(id);
//		return new ResultStatusModel("ok","回傳文章全部內容數目:"+detail.size(),detail);
//	}

//	@ApiOperation("參數:文章id | article @回傳回復結果")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "發文者姓名", defaultValue = "") })
//	@ApiResponses({ @ApiResponse(code = 401, message = "驗證不通過"), @ApiResponse(code = 400, message = "請求參數不正確"),
//			@ApiResponse(code = 404, message = "頁面不正確") })
//	//@PreAuthorize("hasRole('USER ')")
//	@RequestMapping(value = "/reply/{id}", method = RequestMethod.POST)
//	public ResultStatusModel replyArticle(@PathVariable("id")long id,Article inputReply) {
//		Article article = contactsRepository.findById(id);
//		return new ResultStatusModel("ok","回覆成功",list);
//	}

	
//	@ApiOperation("參數:使用者名稱 @回傳該名使用者文章列表")
//	@ApiImplicitParams({
//			@ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "發文者姓名", defaultValue = "") })
//	@ApiResponses({ @ApiResponse(code = 401, message = "驗證不通過"), @ApiResponse(code = 400, message = "請求參數不正確"),
//			@ApiResponse(code = 404, message = "頁面不正確") })
//	@PreAuthorize("hasRole('USER ')")
//	@RequestMapping(value = "/query", method = RequestMethod.GET)
//	public ResultStatusModel findArticleByName(int levelId) {
//		return contactsRepository.findByUsername(username);
//		return new ResultStatusModel("ok","回傳文章，筆數:"+list.size(),list);
//	}

	
	@ApiOperation("@回傳所有文章列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "queryAll") })
	@ApiResponses({ 
		@ApiResponse(code = 401, message = "驗證不通過"),
		@ApiResponse(code = 400, message = "請求參數不正確"),
		@ApiResponse(code = 404, message = "頁面不正確")
		})
	//@PreAuthorize("hasRole('USER ')")
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	public ResultStatusModel findContactAll() {
		
		return service.findAllArticle();
	}

}
