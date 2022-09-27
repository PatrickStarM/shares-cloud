package com.mth.content.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.mth.content.common.ResponseResult;
import com.mth.content.common.ResultCode;
import com.mth.content.domain.dto.ShareDto;
import com.mth.content.domain.entity.Share;
import com.mth.content.domain.entity.User;
import com.mth.content.openfeign.UserService;
import com.mth.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@RestController
@Slf4j
@RequestMapping(value = "/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {

    private final ShareService shareService;
    private final UserService userService;

    @GetMapping("{id}")
    @SentinelResource(value = "getShareById")
    public ResponseResult getShareById(@PathVariable Integer id) {
        String result = shareService.getNumber(2025);
        log.info(result);
        if ("BLOCKED".equals(result)) {
            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
        }
        Share share = shareService.findById(id);
        Integer userId = share.getUserId();
        ResponseResult res = userService.getUser(userId);
        String jsonString = JSONObject.toJSONString(res.getData());
        JSONObject obj = JSONObject.parseObject(jsonString);
        User user = JSONObject.toJavaObject(obj, User.class);
//        System.out.println(user);
        ShareDto shareDto = ShareDto.builder().share(share).nickName(user.getNickname()).avatar(user.getAvatar()).build();
        return ResponseResult.success(shareDto);
    }

    @GetMapping(value = "/all")
    @SentinelResource(value = "getAllShares")
//    @SentinelResource(value = "getAllShares", blockHandler = "getShareBlock")
    public ResponseResult getAllShares() {
        String result = shareService.getNumber(2111);
        log.info(result);
        if ("BLOCKED".equals(result)) {
            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
        }
        List<Share> all = shareService.findAll();
//        System.out.println(all);
        return ResponseResult.success(all);
    }

    public ResponseResult getShareBlock(BlockException exception) {
        log.info("接口被限流");
        log.info(exception.toString());
        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
    }
}
