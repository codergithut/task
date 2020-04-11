package com.factory.task.controller;

import com.factory.task.data.GpsData;
import com.factory.task.data.GpsDataService;
import com.factory.task.model.RestModelTemplate;
import com.factory.task.model.task.TaskInsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.factory.task.util.RequestUtil.getUserCodeBySession;

/**
 * Created by tianjian on 2020/4/11.
 */
@RestController
@RequestMapping("/gps")
@CrossOrigin
public class GpsContrller {

    @Autowired
    private GpsDataService gpsDataService;

    @GetMapping("/saveLocation")
    public RestModelTemplate<Boolean> saveLocationInfo(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        // 根据taskStatus获取所有服务实例 比如获取所有服务状态未start 提供给页面，如果是start可以点击完成，否则就是没啥操作
        GpsData gpsData = new GpsData();
        gpsData.setId(UUID.randomUUID().toString());
        gpsData.setLatitude(latitude);
        gpsData.setLongitude(longitude);
        gpsData.setCreateTime(new Date(System.currentTimeMillis()));
        gpsDataService.save(gpsData);
        return new RestModelTemplate<>().Success(true);
    }


}
