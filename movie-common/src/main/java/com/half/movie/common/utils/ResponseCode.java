package com.half.movie.common.utils;

public enum ResponseCode {
    SUCCESS("200","执行成功！"),
    NO_DATA("201","未查询到数据！"),
    UNEN_UPDATE("300","无新版本！"),
    NEED_UPDATE("301","发现新版本，是否需要更新？"),
    MUST_UPDATE("302","当前版本不可用，是否强制更新？"),
    ERROR("500","执行失败！"),
    NEED_LOGIN("501","需要重新登陆!"),
    ILLEGAL_ARGUMENT("502","非法参数！"),
    NO_USERINFO("503", "未查询到用户信息！"),
    ALREADY_UPDATE("202","数据已被修改,请刷新后重试"),
    EXIST_SON("203","存在子节点不能删除"),

    //业务类
    CODE_NAME_IS_NULL("50001", "编码或名称不能为空！"),
    ORG_IS_NULL("50002", "组织信息不能为空！"),
    GROUP_IS_NULL("50003", "集团信息不能为空！"),
    PRIMARY_KEY_IS_NULL("50004", "主键不能为空！"),
    CMATERIALOID_IS_NULL("50005", "物料不能为空！"),
    SOURCEBILL_IS_NULL("50006", "来源单据信息为空！"),
    MARBASCLASS_IS_NULL("50007", "物料分类为空！"),
    QUALITYMAN_IS_NULL("50008", "生产日期或失效日期为空！"),
    RACK_IS_NULL("50009", "货位为空！"),
    EXIST_CODE_NAME("50100", "已存在该编码或名称！"),
    ;
    private final String code;
    private final String desc;


    ResponseCode(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
