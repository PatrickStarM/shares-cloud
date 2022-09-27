package com.mth.content.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @description: User表实体类
 * @author: mth
 * @date: 2022/9/6
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色
     */
    private String roles;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 积分
     */
    private Integer bonus;

}
