package com.half.movie.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.half.movie.common.utils.PositionUtils;
import com.half.movie.common.utils.ResponseCode;
import com.half.movie.common.utils.ResponseData;
import com.half.movie.entity.CityEntity;
import com.half.movie.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service("iCityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private PositionUtils positionUtils;

    @Override
    public ResponseData getPositionCity(String lng, String lat) throws Exception {
        Map<String,Object> resultMap = positionUtils.getLocation(lng, lat);
        if(resultMap.containsKey("status")){
            String status = resultMap.get("status").toString();
            String message = resultMap.get("message").toString();
            return ResponseData.fail(status,message);
        }
        return ResponseData.success(ResponseCode.SUCCESS.getCode(),resultMap);
    }

    @Override
    public ResponseData getCityList() {

        CityEntity cityEntity = new CityEntity(1, "上海", "S", 1);
        CityEntity cityEntity1 = new CityEntity(2, "北京", "B", 1);
        CityEntity cityEntity2 = new CityEntity(3, "郑州", "Z", 1);
        CityEntity cityEntity3 = new CityEntity(4, "乌鲁木齐", "W", 0);
        ArrayList<CityEntity> cityEntityList = new ArrayList<>();
        cityEntityList.add(cityEntity);
        cityEntityList.add(cityEntity1);
        cityEntityList.add(cityEntity2);
        cityEntityList.add(cityEntity3);
        return ResponseData.success("0",cityEntityList,null);
    }
}
