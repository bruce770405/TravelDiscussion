package javaserver.controller;

import io.swagger.annotations.*;
import javaserver.controller.dto.ArticleDto;
import javaserver.entity.ArticleDetailEntity;
import javaserver.entity.ArticleEntity;
import javaserver.service.ArticleBo;
import javaserver.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章用 controller.
 * <p>
 *
 * @author BruceHsu
 */
@Api("控制 USER 發文資訊 ")
@ApiResponses({@ApiResponse(code = 401, message = "驗證不通過"), @ApiResponse(code = 400, message = "請求參數不正確"),
        @ApiResponse(code = 404, message = "頁面不正確")})
@RestController
@RequestMapping("/article")
@PreAuthorize("hasRole('USER ')")
public class ArticleController {

    /**
     * 文章服務
     */
    private final ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @ApiOperation("新增一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "post", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = ""),
            @ApiImplicitParam(paramType = "post", name = "userName", dataType = "String", required = true, value = "發文者身份", defaultValue = ""),
            @ApiImplicitParam(paramType = "post", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = "")})
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ArticleDto saveNewArticle(@RequestBody ArticleDto param) {
        ArticleBo articleBo = service.saveNewArticle(param.convertToBo());
        return articleBo.convertToDto();
    }

    @ApiOperation("更新一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = ""),
    })
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatedContact(@PathVariable("id") long id, @RequestBody ArticleEntity webdata) {
        return service.editArticleById(id, webdata);
    }

    @ApiOperation("更新文章內的內容或回文")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "stepId", dataType = "long", required = true, value = "文章編號", defaultValue = "")})
    @RequestMapping(value = "/edit/{id}/{stepId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatedContact(@PathVariable("id") long id, @PathVariable("id") long stepId,
                                            @RequestBody ArticleDetailEntity article) {
        return service.editArticleDetailById(id, stepId, article);
    }

    @ApiOperation("刪除指定文章內的回文")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "指定文章編號", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "stepId", dataType = "long", required = true, value = "文章內文編號", defaultValue = ""),
            @ApiImplicitParam(paramType = "remove", name = "username", dataType = "String", required = true, value = "請求者帳號名稱", defaultValue = "")})
    @RequestMapping(value = "/remove/{id}/{stepId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeContact(@PathVariable("id") long id, @PathVariable("stepId") long stepId,
                                           String username) {

        return service.deleteArticleStepDetail(id, stepId, username);
    }

    @ApiOperation("回傳該文章詳細內容")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "long", required = true, value = "文章編號")})
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findArticleById(@PathVariable("id") long id) {
        return service.findArticleDetailById(id);
    }

    // @ApiOperation("參數:文章id | article @回傳回復結果")
    // @ApiImplicitParams({
    // @ApiImplicitParam(paramType = "query", name = "username", dataType =
    // "String", required = true, value = "發文者姓名", defaultValue = "") })
    // @ApiResponses({ @ApiResponse(code = 401, message = "驗證不通過"),
    // @ApiResponse(code = 400, message = "請求參數不正確"),
    // @ApiResponse(code = 404, message = "頁面不正確") })
    // //@PreAuthorize("hasRole('USER ')")
    // @RequestMapping(value = "/reply/{id}", method = RequestMethod.POST)
    // public ResultStatusModel replyArticle(@PathVariable("id")long id,Article
    // inputReply) {
    // Article article = contactsRepository.findById(id);
    // return new ResultStatusModel("ok","回覆成功",list);
    // }

    // @ApiOperation("參數:使用者名稱 @回傳該名使用者文章列表")
    // @ApiImplicitParams({
    // @ApiImplicitParam(paramType = "query", name = "username", dataType =
    // "String", required = true, value = "發文者姓名", defaultValue = "") })
    // @ApiResponses({ @ApiResponse(code = 401, message = "驗證不通過"),
    // @ApiResponse(code = 400, message = "請求參數不正確"),
    // @ApiResponse(code = 404, message = "頁面不正確") })
    //
    // @RequestMapping(value = "/query", method = RequestMethod.GET)
    // public ResultStatusModel findArticleByName(int levelId) {
    // return contactsRepository.findByUsername(username);
    // return new ResultStatusModel("ok","回傳文章，筆數:"+list.size(),list);
    // }

    @ApiOperation("回傳所有文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = true, value = "資料頁數", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", name = "size", dataType = "Integer", required = true, value = "一頁資料量", defaultValue = "15"),
            @ApiImplicitParam(paramType = "queryAll")})
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public ResponseEntity<?> findContactAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "15") Integer size) {

        return service.findAllArticle(new PageRequest(page, size, new Sort(Sort.Direction.DESC, "createTime")));
    }

    // @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    // public ResultStatusModel uploadImage(FileModel model) {
    // String username = model.getUsername();
    // model.getFileList().forEach ( file -> {
    // Util.base64ToImageFile(username, file);
    // });
    // return new ResultStatusModel(true,"上傳成功",model);
    // }

}
