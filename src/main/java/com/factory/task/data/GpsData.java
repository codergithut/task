package com.factory.task.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by tianjian on 2020/4/11.
 */

@Entity
public class GpsData {

    @Id
    private String id;
    // 纬度
    private Double latitude;

    //经度
    private Double longitude;


    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
