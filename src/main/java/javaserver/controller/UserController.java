package javaserver.controller;

import java.util.List;

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
import javaserver.model.Webdata;
import javaserver.repository.DataJpaRepository;

/*
 * 
 * 查詢網頁資訊
 */
@Api("控制 USER 資訊 ")
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('USER ')")
public class UserController {
	
	@Autowired
	DataJpaRepository contactsRepository;

	// 新增
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public Webdata login(@RequestBody Webdata webdata) {
			return this.contactsRepository.save(webdata);
		}

	// 新增
	@RequestMapping(value = "/save/new", method = RequestMethod.POST)
	public Webdata saveNewContact(@RequestBody Webdata webdata) {
		return this.contactsRepository.save(webdata);
	}

	// 更新
	@RequestMapping(value = "/save/updated", method = RequestMethod.PUT)
	public Webdata saveUpdatedContact(@RequestBody Webdata webdata) {
		Webdata contactUpdate = contactsRepository.findByUsername(webdata.getUsername());
		contactUpdate.setTitle(webdata.getTitle());
		contactUpdate.setBody(webdata.getBody());
		return contactsRepository.save(contactUpdate);
	}

	// 刪除
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public void removeContact(long id) {
		this.contactsRepository.delete(id);
	}
	
	@ApiOperation("傳param=發文者姓名 @return 發文資訊")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "username", dataType = "String",required = true, value = "發文者姓名", defaultValue = "") 
			})
	@ApiResponses({
		@ApiResponse(code = 401, message = "驗證不通過"),
		@ApiResponse(code = 400, message = "請求參數不正確"),
		@ApiResponse(code = 404, message = "頁面不正確") 
		})
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public Webdata findContactByName(String username) {
		Webdata contact = contactsRepository.findByUsername(username);
		return contact;
	}
	
	//查詢全部資料
	@PreAuthorize("hasRole('USER ')")
	@RequestMapping(value = "/queryall",method = RequestMethod.GET)
	public List<Webdata> findContactAll(){
		List<Webdata> list = contactsRepository.findAll();
		return list;
	}

}
