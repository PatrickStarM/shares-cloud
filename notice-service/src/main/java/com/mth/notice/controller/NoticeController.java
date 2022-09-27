package com.mth.notice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mth.notice.common.ResponseResult;
import com.mth.notice.common.ResultCode;
import com.mth.notice.domain.entity.Notice;
import com.mth.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@RestController
@RequestMapping(value = "/notices")
@Slf4j
// 设置动态读取远程配置
@RefreshScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {
    private final NoticeService noticeService;

    @Value("${disableNoticeRequest:false}")
    private Boolean disableNotice;

    @GetMapping("/latest")
    @SentinelResource(value = "getNotice",blockHandler = "getNoticeBlock")
    public ResponseResult getNotice() {
        if (disableNotice) {
            log.info("暂停公告服务");
            return ResponseResult.failure(ResultCode.INTERFACE_FORBID_VISIT);
        }
        Notice latestNotice = noticeService.getLatestNotice();
        if (latestNotice != null) {
            return ResponseResult.success(latestNotice);
        } else {
            return ResponseResult.failure(ResultCode.NOTICE_NO_LATEST);
        }
    }

    public ResponseResult getNoticeBlock(BlockException exception) {
        log.info("接口被限流");
        log.info(exception.toString());
        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
    }
}
