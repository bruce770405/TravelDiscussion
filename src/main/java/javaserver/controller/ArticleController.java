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
 * article controller.
 *
 * @author BruceHsu
 */
@Api("Article Handler")
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

    @ApiOperation("Create article")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "post", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = ""),
            @ApiImplicitParam(paramType = "post", name = "userName", dataType = "String", required = true, value = "發文者身份", defaultValue = ""),
            @ApiImplicitParam(paramType = "post", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = "")})
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ArticleDto saveNewArticle(@RequestBody ArticleDto param) {
        ArticleBo articleBo = service.saveNewArticle(param.convertToBo());
        return articleBo.convertToDto();
    }

    @ApiOperation("Update article subject")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = ""),
    })
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatedContact(@PathVariable("id") long id, @RequestBody ArticleEntity webData) {
        return service.editArticleById(id, webData);
    }

    @ApiOperation("Update article detail")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "stepId", dataType = "long", required = true, value = "文章編號", defaultValue = "")})
    @RequestMapping(value = "/edit/{id}/{stepId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatedContact(@PathVariable("id") long id, @PathVariable("id") long stepId,
                                            @RequestBody ArticleDetailEntity article) {
        return service.editArticleDetailById(id, stepId, article);
    }

    @ApiOperation("Delete article detail")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "long", required = true, value = "指定文章編號", defaultValue = ""),
            @ApiImplicitParam(paramType = "path", name = "stepId", dataType = "long", required = true, value = "文章內文編號", defaultValue = ""),
            @ApiImplicitParam(paramType = "remove", name = "username", dataType = "String", required = true, value = "請求者帳號名稱", defaultValue = "")})
    @RequestMapping(value = "/remove/{id}/{stepId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeContact(@PathVariable("id") long id, @PathVariable("stepId") long stepId,
                                           String username) {

        return service.deleteArticleStepDetail(id, stepId, username);
    }

    @ApiOperation("Find article detail")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "long", required = true, value = "文章編號")})
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findArticleById(@PathVariable("id") long id) {
        return service.findArticleDetailById(id);
    }

    @ApiOperation("Find all article pageable")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", dataType = "Integer", required = true, value = "資料頁數", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", name = "size", dataType = "Integer", required = true, value = "一頁資料量", defaultValue = "15"),
            @ApiImplicitParam(paramType = "queryAll")})
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public ResponseEntity<?> findContactAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "15") Integer size) {
        return service.findAllArticle(new PageRequest(page, size, new Sort(Sort.Direction.DESC, "createTime")));
    }

}
