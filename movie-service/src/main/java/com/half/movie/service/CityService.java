package com.half.movie.service;

import com.half.movie.common.utils.ResponseData;

public interface CityService {
    /**
     * 根据经纬度获取城市定位
     * @param lng
     * @param lat
     * @return
     */
    ResponseData getPositionCity(String lng, String lat) throws Exception;

    /**
     * 获取所有城市信息列表
     * @return
     */
    ResponseData getCityList();
}
