package com.migros.couriertracking.dto;

public class LocationDto {
    private Long id;
    private Double latitude;
    private Double longitude;
    private CourierDto courier;
    private StoreDto store;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public CourierDto getCourier() {
        return courier;
    }

    public void setCourier(CourierDto courier) {
        this.courier = courier;
    }

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }
}
