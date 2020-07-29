package com.half.movie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

import com.half.movie.commonvo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lmm
 * @since 2020-07-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wx_user")
@ApiModel(value="WxUser对象", description="")
public class WxUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小程序用户的openid")
    @TableField("openid")
    private String openid;

    @ApiModelProperty(value = "用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    @TableField("avatarurl")
    private String avatarurl;

    @ApiModelProperty(value = "性别   0 男  1  女")
    @TableField("gender")
    private Boolean gender;

    @ApiModelProperty(value = "所在国家")
    @TableField("country")
    private String country;

    @TableField("province")
    private String province;

    @ApiModelProperty(value = "城市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "语种")
    @TableField("language")
    private String language;

    @ApiModelProperty(value = "手机号码")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "最后一次登录时间")
    @TableField(value="lastlogintime",fill = FieldFill.INSERT_UPDATE)//新增和更新操作的时候自动插入时间
    private Date lastlogintime;

    @ApiModelProperty(value = "用户SessionKey")
    @TableField("session_key")
    private String sessionKey;


    public static final String OPENID = "openid";

    public static final String NICKNAME = "nickname";

    public static final String AVATARURL = "avatarurl";

    public static final String GENDER = "gender";

    public static final String COUNTRY = "country";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String LANGUAGE = "language";

    public static final String MOBILE = "mobile";

    public static final String LASTLOGINTIME = "lastlogintime";

    public static final String SESSION_KEY = "session_key";

}
