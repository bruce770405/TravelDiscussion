package javaserver.controller;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javaserver.util.Util;

@Api("控制 USER 的修改資訊 ")
@Controller
@RequestMapping(value = "/user")
@PreAuthorize("hasRole('USER ')")
public class UserDataController {

	private final ResourceLoader resourceLoader;

	@Autowired
	public UserDataController(ResourceLoader resourceLoader) {
		
		this.resourceLoader = resourceLoader;
	}

	@ApiOperation("獲取使用者頭貼")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "get", name = "username", dataType = "String", required = true, value = "使用者名稱", defaultValue = "")
//		@ApiImplicitParam(paramType="post", name="userName", dataType="String", required = true, value="發文者身份", defaultValue=""),
//		@ApiImplicitParam(paramType = "post", name = "id", dataType = "long", required = true, value = "文章編號", defaultValue = "") 
		
    })
    @ApiResponses({ 
    	    @ApiResponse(code = 401, message = "驗證不通過"), 
    	    @ApiResponse(code = 400, message = "請求參數不正確"),
		@ApiResponse(code = 404, message = "頁面不正確") 
    	    })
	@RequestMapping(value = "/getIcon/{username}", method = RequestMethod.GET)
	@ResponseBody  
	public ResponseEntity<?> getUserIcon(@PathVariable("username") String username) {
		String fileUrl = Util.path+username+"\\";
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG); 
		try {
			ResponseEntity<?> r = new ResponseEntity<>(resourceLoader.getResource("file:" + Paths.get(fileUrl, "user.jpg")), headers, HttpStatus.OK);
			return r;
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
	// public List<Object>
}
