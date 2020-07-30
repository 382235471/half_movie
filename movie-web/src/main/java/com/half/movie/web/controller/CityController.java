package com.half.movie.web.controller;

import com.half.movie.common.utils.ResponseCode;
import com.half.movie.common.utils.ResponseData;
import com.half.movie.service.CityService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
@Api(tags = {"城市定位,城市列表接口"})
public class CityController {

    @Autowired
    CityService iCityService;

    @ApiOperation(value="根据经纬度获取城市信息",notes="不知道说点啥")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/getPositionCity")
    public ResponseData getPositionCity(@ApiParam(name="lng",value = "精度",required = true) @RequestParam(value = "lng" ,required = true)String lng,
                                        @ApiParam(name="lat",value = "纬度",required = true)@RequestParam(value = "lat",required = true)String lat){
        try {
            ResponseData responseData = iCityService.getPositionCity(lng, lat);
            return responseData;
        } catch (Exception e) {
            return ResponseData.fail(ResponseCode.ERROR.getCode(),e.getMessage());
        }

    }

    @ApiOperation(value="获取城市列表接口",notes="说你个锤子")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @PostMapping("/getCityList")
    public ResponseData getCityList(){
        ResponseData responseData = iCityService.getCityList();

        return responseData;
    }



}
