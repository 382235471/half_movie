package com.half.movie.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.half.movie.common.utils.ResponseCode;
import com.half.movie.common.utils.ResponseData;

import com.half.movie.service.WxUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhb
 * @since 2020-07-22
 */


@RestController
@RequestMapping("/wxUser")
@Api(tags = {"微信用户接口"})
public class WxUserController {

    @Autowired
    WxUserService iWxUserService;

    @ApiOperation(value="用户登录",notes="随边说点啥")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/weChatLogin")
    public ResponseData weChatLogin(@RequestParam(value = "wxCode",required = false)String wxCode)
    {
        ResponseData responseData = null;
        try {
            responseData = iWxUserService.login(wxCode);
        } catch (Exception e) {
           return ResponseData.fail(ResponseCode.ERROR.getCode(),e.getMessage());
        }
        return responseData;
    }
}
