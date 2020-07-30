package com.half.movie.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component("positionUtils")
public class PositionUtils {


    private static String wxPositionKey ;

    @Value("${wx.positionKey}")
    public void setWxPositionKey(String wxPositionKey){
        PositionUtils.wxPositionKey = wxPositionKey;
    }

    public Map<String,Object> getLocation(String lng, String lat)throws Exception {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 参数解释：lng：经度，lat：维度。KEY：腾讯地图key，get_poi：返回状态。1返回，0不返回
        String urlString = "http://apis.map.qq.com/ws/geocoder/v1/?location=" + lat + "," + lng + "&key=" + wxPositionKey + "&get_poi=1";
        String result = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            // 腾讯地图使用GET
            conn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            // 获取地址解析结果
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            e.getMessage();
        }

        // 转JSON格式
        JSONObject jsonObject = JSONObject.parseObject(result);
        if(jsonObject.containsKey("result")){
            JSONObject cityObject= jsonObject.getJSONObject("result");
            // 获取地址（行政区划信息） 包含有国籍，省份，城市
            JSONObject adInfo = cityObject.getJSONObject("ad_info");
            resultMap.put("nation", adInfo.get("nation"));
            resultMap.put("nationCode", adInfo.get("nation_code"));
            resultMap.put("province", adInfo.get("province"));
            resultMap.put("provinceCode", adInfo.get("adcode"));
            resultMap.put("city", adInfo.get("city"));
            resultMap.put("cityCode", adInfo.get("city_code"));

        }else if(jsonObject.containsKey("status")){
            String status = jsonObject.get("status").toString();
            String message= jsonObject.get("message").toString();
            resultMap.put("status",status);
            resultMap.put("message",message);
        }
        return resultMap;
    }

    public static void main(String[] args) {


        PositionUtils positionUtils = new PositionUtils();

        // 测试
        String lng = "111.546112";//经度
        String lat = "24.378622";//维度
        Map<String, Object> map = null;
        try {
            map = positionUtils.getLocation(lng, lat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map);
        System.out.println("国   籍：" + map.get("nation"));
        System.out.println("国家代码：" + map.get("nationCode"));
        System.out.println("省   份：" + map.get("province"));
        System.out.println("省份代码：" + map.get("provinceCode"));
        System.out.println("城   市：" + map.get("city"));
        System.out.println("城市代码：" + map.get("cityCode"));
    }

}
