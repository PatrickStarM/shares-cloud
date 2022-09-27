package com.mth.user.service.impl;

import com.mth.user.domain.dto.UserDto;
import com.mth.user.domain.entity.User;
import com.mth.user.repository.UserRepository;
import com.mth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findById(Integer id) {
//        Integer i =  3/0;
//        try {
//            Thread.sleep(1000*3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User login(UserDto userDto) {
        return userRepository.findByMobileAndPassword(userDto.getMobile(), userDto.getPassword());
    }
}
