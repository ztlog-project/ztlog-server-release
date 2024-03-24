package com.devlog.admin.controller.file;


import com.devlog.admin.service.file.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "파일 컨트롤러", description = "파일 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1")
public class FileController {

    private final FileService fileService;



}
