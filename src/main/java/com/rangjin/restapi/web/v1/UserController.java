package com.rangjin.restapi.web.v1;

import com.rangjin.restapi.advice.exception.CustomUserNotFoundException;
import com.rangjin.restapi.domain.User;
import com.rangjin.restapi.domain.UserRepository;
import com.rangjin.restapi.service.ResponseService;
import com.rangjin.restapi.web.dto.response.CommonResult;
import com.rangjin.restapi.web.dto.response.ListResult;
import com.rangjin.restapi.web.dto.response.SingleResult;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {

    private final UserRepository userRepository;

    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 전체 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/user")
    public ListResult<User> findAllUser(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        return responseService.getListResult(userRepository.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단일 조회", notes = "ID로 회원을 조회한다")
    @GetMapping(value = "/user/{id}")
    public SingleResult<User> findUserById(
            @ApiParam(value = "회원번호", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) throws Exception {
        return responseService.getSingleResult(userRepository.findById(id).orElseThrow(CustomUserNotFoundException::new));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다")
    @PostMapping(value = "/user")
    public SingleResult<User> save(
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name,
            @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();

        return responseService.getSingleResult(userRepository.save(user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam Long id,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name,
            @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        User user = User.builder()
                .id(id)
                .uid(uid)
                .name(name)
                .build();

        return responseService.getSingleResult(userRepository.save(user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원 정보를 삭제한다")
    @DeleteMapping(value = "/user/{id}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        userRepository.deleteById(id);

        return responseService.getSuccessResult();
    }

}
