package com.half.movie.entity;

import lombok.Data;

@Data
public class Code2SessionResponse {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode = "0";
    private String errmsg;
    private int expires_in;
    /**
     * 省略getter/setter
     */
}
