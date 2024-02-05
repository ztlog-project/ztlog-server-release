package com.devlog.admin.controller.main;

import com.devlog.admin.service.main.MainService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "메인(대쉬보드) 컨트롤러", description = "메인(대쉬보드) 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class MainController {

    private final MainService mainService;


}
