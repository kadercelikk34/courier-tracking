package com.migros.couriertracking.dto;

import java.io.Serializable;

//Kuryeye yakın magazaların isim ve kuryenin magazaya  uzaklığı ve mesafenin kac dk bilgileri için oluşturuldu
public class CourierDistance implements Serializable {

    private String storeName;
    private String courierDistance;
    private Double distanceMinute;

    public CourierDistance(String storeName, String courierDistance, Double distanceMinute) {
        this.storeName = storeName;
        this.courierDistance = courierDistance;
        this.distanceMinute = distanceMinute;
    }

    public CourierDistance() {
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCourierDistance() {
        return courierDistance;
    }

    public void setCourierDistance(String courierDistance) {
        this.courierDistance = courierDistance;
    }

    public Double getDistanceMinute() {
        return distanceMinute;
    }

    public void setDistanceMinute(Double distanceMinute) {
        this.distanceMinute = distanceMinute;
    }
}
