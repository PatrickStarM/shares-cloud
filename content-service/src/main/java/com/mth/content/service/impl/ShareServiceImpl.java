package com.mth.content.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mth.content.domain.entity.Share;
import com.mth.content.repository.ShareRepository;
import com.mth.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }

    @Override
    public List<Share> findAll() {
        Sort sort = Sort.by("createTime").descending();
        return shareRepository.findAll(sort);
    }

    @SentinelResource(value = "getNumber",blockHandler = "blockHandlerNumber")
    @Override
    public String getNumber(int number) {
        return "number = {" + number + "}";
    }

    @Override
    public String blockHandlerNumber(int number, BlockException e) {
        return "BLOCKED";
    }
}
