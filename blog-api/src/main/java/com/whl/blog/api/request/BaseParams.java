package com.whl.blog.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by whling on 2018/4/18.
 */
@Data
public class BaseParams implements Serializable{

    private String userId;

    private String userName;

    private String userEmail;

    private String clientIP;

    private String traceID;
}
