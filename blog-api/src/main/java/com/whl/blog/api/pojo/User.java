package com.whl.blog.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by whling on 2018/4/18.
 */
@Data
public class User implements Serializable{

    private Long userId;

    private String userName;

    private String passWord;

    private String nickName;

    private String phone;

    private String email;

    private Integer status;

    private Integer loginType;

    private String avatar;

    private Integer gender;

    private String meta;

    private Integer level;

    private String md5;

    private Date createTime;

    private Date modifyTime;
}
