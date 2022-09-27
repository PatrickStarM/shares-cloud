package com.mth.content.repository;

import com.mth.content.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
public interface ShareRepository extends JpaRepository<Share, Integer> {

}
