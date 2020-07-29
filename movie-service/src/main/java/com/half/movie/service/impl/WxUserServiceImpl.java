package com.half.movie.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.half.movie.common.utils.RedisUtils;
import com.half.movie.common.utils.ResponseCode;
import com.half.movie.common.utils.ResponseData;
import com.half.movie.common.utils.UrlUtil;
import com.half.movie.entity.Code2SessionResponse;
import com.half.movie.entity.WxUser;
import com.half.movie.dao.WxUserMapper;
import com.half.movie.service.WxUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.half.movie.shiro.jwt.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhb
 * @since 2020-07-22
 */
@Service("iWxUserService")
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;
    @Value("${wx.applet.appid}")
    private String appid;
    @Value("${wx.applet.appsecret}")
    private String appSecret;


    @Override
    public ResponseData login(String wxCode){
        //根据微信小程序临时凭证获取微信用户信息
        String json = code2Session(wxCode);
        Code2SessionResponse response  = JSON.parseObject(json, Code2SessionResponse.class);
        if (!response.getErrcode().equals("0")){
            return ResponseData.fail(response.getErrcode(),response.getErrmsg());

        }else{
            WxUser wxUser = getUserByOpenId(response.getOpenid());
            if(wxUser == null) {

                wxUser = new WxUser();
                // 不存在，就是第一次登录：新建用户信息
                wxUser.setOpenid(response.getOpenid());
                wxUser.setSessionKey(response.getSession_key());
                wxUserMapper.insert(wxUser);
            }else{
                //如果存在，就不是第一次登录，更新最后登录时间(自动更新)
                wxUser.setSessionKey(response.getSession_key());
                wxUserMapper.updateById(wxUser);
            }

            //5 . JWT 返回自定义登陆态 Token
            String token = JWTConfig.createTokenByWxAccount(wxUser);
            Map<String, Object> result = new HashMap(16);
            result.put("wxUser",wxUser);
            result.put("token",token);
            return ResponseData.success(ResponseCode.SUCCESS.getCode(), result, "登陆成功！");
        }
    }

    public String code2Session(String wxCode) {
        // code  -> openid
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";

        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", appid);	//开发者设置中的appId
        requestUrlParam.put("secret", appSecret);	//开发者设置中的appSecret
        requestUrlParam.put("js_code", wxCode);	//小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");	//默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        String json = UrlUtil.sendPost(requestUrl, requestUrlParam);
        return json;
    }


    public WxUser getUserByOpenId(String openid) {
        QueryWrapper<WxUser> queryWrapper = new QueryWrapper<WxUser>();
        queryWrapper.eq("openid",openid);
        WxUser wxUser = wxUserMapper.selectOne(queryWrapper);
        return wxUser;
    }
}
