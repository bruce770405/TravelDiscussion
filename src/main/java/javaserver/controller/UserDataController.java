package javaserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("User's Data Handler")
@RestController
@RequestMapping(value = "/user")
@PreAuthorize("hasRole('USER ')")
public class UserDataController {

    private final UserService userService;

    @Autowired
    public UserDataController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Get User's Icon")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "get", name = "username", dataType = "String", required = true, value = "username", defaultValue = "")
    })
    @GetMapping(value = "/getIcon/{username}")
    public ResponseEntity<?> getUserIcon(@PathVariable("username") String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        Resource resource = userService.getUsersIcon(username);
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
