package com.half.movie.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.half.movie.common.utils.ResponseData;
import com.half.movie.entity.WxUser;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhb
 * @since 2020-07-22
 */
public interface WxUserService extends IService<WxUser> {


    /**
     * 登录接口
     * @param wxCode
     * @return
     */
    ResponseData login(String wxCode)throws Exception;
}
