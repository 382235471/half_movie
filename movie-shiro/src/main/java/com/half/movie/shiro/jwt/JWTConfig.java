package com.half.movie.shiro.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.half.movie.common.utils.Const;
import com.half.movie.common.utils.RedisUtils;
import com.half.movie.common.utils.UUIDUtils;
import com.half.movie.entity.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author lmm
 * @创建时间 2019-10-11
 * @描述
 **/
@Component
public class JWTConfig {

    /**
     * JWT 自定义密钥 我这里写死的
     */
    private static final String SECRET_KEY = "5371f568a45e5ab1f442c38e0932aef24447139b";

    private static String jwtTokenExpireTime;

    private static RedisUtils redis;

    @Value("${config.jwtToken-expireTime}")
    public void setJwtTokenExpireTime(String jwtTokenExpireTime) {
        JWTConfig.jwtTokenExpireTime = jwtTokenExpireTime;
    }

    @Autowired
    public void setRedis(RedisUtils redis){
        JWTConfig.redis = redis;
    }

    /**
     * 根据微信用户登陆信息创建 token
     * 注 : 这里的token会被缓存到redis中,用作为二次验证
     * redis里面缓存的时间应该和jwt token的过期时间设置相同
     *
     * @param wxUser 微信用户信息
     * @return 返回 jwt token
     */
    public static String createTokenByWxAccount(WxUser wxUser) {


        try {
            String jwtId = UUIDUtils.getUniqueIdByUUId(); //JWT 随机ID,做为验证的key
            Date date = new Date(System.currentTimeMillis() + Integer.parseInt(jwtTokenExpireTime) * 1000);//JWT 配置过期时间的正确姿势
            Algorithm algorithm =Algorithm.HMAC256(SECRET_KEY);
            String token = JWT.create()
                    .withClaim(Const.OPEN_ID, wxUser.getOpenid())
                    .withClaim(Const.SESSION_KEY, wxUser.getSessionKey())
                    .withClaim(Const.JWT_ID, jwtId)
                    .withExpiresAt(date)
                    .sign(algorithm);
            //2 . Redis缓存JWT, 注 : 请和JWT过期时间一致
            redis.set("JWT-SESSION-"+jwtId,token,Integer.parseInt(jwtTokenExpireTime));
            return token;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 校验token是否正确
     * 1 . 根据token解密，解密出jwt-id , 先从redis中查找出redisToken，匹配是否相同
     * 2 . 然后再对redisToken进行解密，解密成功则 继续流程 和 进行token续期
     *
     * @param token 密钥
     * @return 返回是否校验通过
     */
    public boolean verifyToken(String token) {
        try {

            //1 . 根据token解密，解密出jwt-id , 先从redis中查找出redisToken，匹配是否相同
            String redisToken = redis.get("JWT-SESSION-" + getJwtIdByToken(token)).toString();
            if (!redisToken.equals(token)){
                return false;
            }
            //2 . 得到算法相同的JWTVerifier
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(Const.OPEN_ID, getWxOpenIdByToken(redisToken))
                    .withClaim(Const.SESSION_KEY, getSessionKeyByToken(redisToken))
                    .withClaim(Const.JWT_ID, getJwtIdByToken(redisToken))
                    .acceptExpiresAt(System.currentTimeMillis() + Integer.parseInt(jwtTokenExpireTime) * 1000)  //JWT 正确的配置续期姿势
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            //4 . Redis缓存JWT续期
            redis.set("JWT-SESSION-" + getJwtIdByToken(token), redisToken, Integer.parseInt(jwtTokenExpireTime));
            return true;

        } catch (Exception e) { //捕捉到任何异常都视为校验失败
            return false;
        }
    }



    /**
     * 根据Token获取wxOpenId(注意坑点 : 就算token不正确，也有可能解密出wxOpenId,同下)
     */
    public String getWxOpenIdByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("OpenId:").asString();
    }

    /**
     * 根据Token获取sessionKey
     */
    public String getSessionKeyByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("SessionKey:").asString();
    }
    /**
     * 根据Token 获取jwt-id
     */
    private String getJwtIdByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("jwtId:").asString();
    }
}