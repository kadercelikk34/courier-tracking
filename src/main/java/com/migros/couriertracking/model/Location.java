package com.migros.couriertracking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "LOCATIONS")

public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "LATITUDE")
    private Double latitude;

    @NotNull
    @Column(name = "LONGITUDE")
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private Courier courier;

    @OneToOne(fetch = FetchType.LAZY)
    private Store store;

    @Column(name = "COURIER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date courierDate;

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

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Date getCourierDate() {
        return courierDate;
    }

    public void setCourierDate(Date courierDate) {
        this.courierDate = courierDate;
    }

    @PrePersist
    public void persist() {
        this.courierDate = new Date();

    }
}
