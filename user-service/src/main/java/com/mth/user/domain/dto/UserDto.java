package com.mth.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String mobile;
    private String password;

}
