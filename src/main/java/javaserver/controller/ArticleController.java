package javaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
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
import javaserver.model.Article;
import javaserver.model.combine.NewArticleModel;

/**
 * 
 * @author BruceHsu
 * 
 * 
 */
@Api("控制 USER 發文資訊 ")
@RestController
@RequestMapping("/article")
@PreAuthorize("hasRole('USER ')")
public class ArticleController {

	
	
	private UserService service;
	
	@Autowired
	public ArticleController(UserService service) {
		this.service = service;
	}
	
	@ApiOperation("新增一篇文章")
//	@ApiImplicitParams({
//		@ApiImplicitParam(paramType = "post", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = ""), 
//		@ApiImplicitParam(paramType="post", name="userName", dataType="String", required = true, value="發文者身份", defaultValue=""),
//		@ApiImplicitParam(paramType = "post", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = "") 
		
//    })
    @ApiResponses({ 
    	    @ApiResponse(code = 401, message = "驗證不通過"), 
    	    @ApiResponse(code = 400, message = "請求參數不正確"),
		@ApiResponse(code = 404, message = "頁面不正確") 
    	    })
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<?> saveNewArticle(@RequestBody NewArticleModel model) {
		return service.saveNewArticle(model);
	}

	@ApiOperation("更新一篇文章")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "save", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = ""), 
		
    })
	 @ApiResponses({ 
		 @ApiResponse(code = 401, message = "驗證不通過"), 
		 @ApiResponse(code = 400, message = "請求參數不正確"),
		 @ApiResponse(code = 404, message = "頁面不正確") 
		 })		
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> saveUpdatedContact(@PathVariable("id") long id,@RequestBody Article webdata) {
		return service.editArticleById(id,webdata);
	}

	
	
	// 刪除
	@ApiOperation("刪除指定文章內的回文")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "remove", name = "id", dataType = "long", required = true, value = "指定文章編號", defaultValue = ""),
			@ApiImplicitParam(paramType = "remove", name = "stepId", dataType = "long", required = true, value = "文章內文編號", defaultValue = ""),
			@ApiImplicitParam(paramType="remove", name="username", dataType="String", required = true, value="請求者帳號名稱", defaultValue="")
	})
	@ApiResponses({ 
		@ApiResponse(code = 401, message = "驗證不通過"), 
		@ApiResponse(code = 400, message = "請求參數不正確"),
		@ApiResponse(code = 404, message = "頁面不正確") 
		})
	@RequestMapping(value = "/remove/{id}/{stepId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeContact(@PathVariable("id")long id,@PathVariable long stepId,String username) {

		return service.deleteArticleStepDetail(id,stepId,username);
	}


	@ApiOperation("回傳該文章詳細內容")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "id", dataType = "long", required = true, value = "文章編號") })
	@ApiResponses({ 
		@ApiResponse(code = 401, message = "驗證不通過"),
		@ApiResponse(code = 400, message = "請求參數不正確"),
			@ApiResponse(code = 404, message = "頁面不正確") 
		})
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findArticleById(@PathVariable("id")long id) {
		return service.findArticleDetailById(id);
	}

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
//	
//	@RequestMapping(value = "/query", method = RequestMethod.GET)
//	public ResultStatusModel findArticleByName(int levelId) {
//		return contactsRepository.findByUsername(username);
//		return new ResultStatusModel("ok","回傳文章，筆數:"+list.size(),list);
//	}

	
	@ApiOperation("回傳所有文章列表")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "queryAll") })
	@ApiResponses({ 
		@ApiResponse(code = 401, message = "驗證不通過"),
		@ApiResponse(code = 400, message = "請求參數不正確"),
		@ApiResponse(code = 404, message = "頁面不正確")
		})
	@RequestMapping(value = "/queryAll", method = RequestMethod.GET)
	public ResponseEntity<?> findContactAll() {
		
		return service.findAllArticle();
	}

//	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
//	public ResultStatusModel uploadImage(FileModel model) {
//		String username = model.getUsername();
//		model.getFileList().forEach ( file -> {
//				Util.base64ToImageFile(username, file);
//		});
//		return new ResultStatusModel(true,"上傳成功",model);
//	}

	
}
