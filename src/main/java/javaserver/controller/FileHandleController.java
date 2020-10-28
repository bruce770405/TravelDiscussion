package javaserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javaserver.controller.dto.FileUploadDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * file controller.
 *
 * @author BruceHsu
 */
@Api("File Handler")
@RestController
@RequestMapping("/file")
@PreAuthorize("hasRole('USER ')")
public class FileHandleController {

    @ApiOperation("Create article")
    @PostMapping(value = "/upload")
    public FileUploadDto saveNewArticle(@RequestBody FileUploadDto param) {
        // TODO
        return null;
    }
}
