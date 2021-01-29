package com.rangjin.restapi.web.exception;

import com.rangjin.restapi.advice.exception.CustomAuthenticationEntrypointException;
import com.rangjin.restapi.web.dto.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public CommonResult entrypointException() {
        throw new CustomAuthenticationEntrypointException();
    }

    @GetMapping("/accessdenied")
    public CommonResult accessdeniedException() {
        throw new AccessDeniedException("");
    }

}
