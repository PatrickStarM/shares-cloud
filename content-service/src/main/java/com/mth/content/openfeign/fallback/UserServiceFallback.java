package com.mth.content.openfeign.fallback;

import com.mth.content.common.ResponseResult;
import com.mth.content.domain.entity.User;
import com.mth.content.openfeign.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/8
 **/
@Slf4j
@Component
public class UserServiceFallback implements UserService {

    @Override
    public ResponseResult getUser(int id) {
        log.info("fallback getUser");
        User user = User.builder().id(100000001).mobile("17606186124").nickname("这是降级方案返回的用户派大星").avatar("http://www.patrickstarm.top:9092/imgs/2022/07/16/62d2bd5b9b94c.png").build();
        return ResponseResult.success(user);
    }
}
