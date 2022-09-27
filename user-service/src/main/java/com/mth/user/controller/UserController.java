package com.mth.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mth.user.common.ResponseResult;
import com.mth.user.common.ResultCode;
import com.mth.user.domain.dto.UserDto;
import com.mth.user.domain.entity.User;
import com.mth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.mth.user.common.ResultCode.USER_SIGN_IN_FAIL;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    @SentinelResource(value = "getUserById",blockHandler = "getUserBlock")
    @GetMapping("{id}")
    public ResponseResult getUserById(@PathVariable Integer id) {
        return ResponseResult.success(userService.findById(id));
    }

    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody UserDto userDto) {
        User userInfo = userService.login(userDto);
        if (userInfo != null) {
            return ResponseResult.success(userInfo);
        } else {
            return ResponseResult.failure(USER_SIGN_IN_FAIL);
        }
    }

    public ResponseResult getUserBlock(BlockException exception) {
        log.info("接口被限流");
        log.info(exception.toString());
        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
    }

}
