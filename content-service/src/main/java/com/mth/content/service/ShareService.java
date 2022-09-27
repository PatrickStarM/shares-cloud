package com.mth.content.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.mth.content.domain.entity.Share;

import java.util.List;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
public interface ShareService {
    /**
     * 根据id查找分享
     *
     * @param id id
     * @return 分享内容
     */
    Share findById(Integer id);

    /**
     * 获取所有share
     * @return 所有share
     */
    List<Share> findAll();

    String getNumber(int number);

    String blockHandlerNumber(int number, BlockException e);
}
