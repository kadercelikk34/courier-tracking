package com.migros.couriertracking.dto;

import java.io.Serializable;

//Kuryeye yakın magazaların isim ve mesafe için oluşturuldu
public class CourierDistance implements Serializable {

    private String storeName;
    private String distance;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
