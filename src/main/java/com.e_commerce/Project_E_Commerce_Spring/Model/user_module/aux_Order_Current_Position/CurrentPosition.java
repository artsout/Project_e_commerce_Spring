package com.e_commerce.Project_E_Commerce_Spring.Model.user_module.aux_Order_Current_Position;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CurrentPosition {

    private double latitude;
    private double longitude;

    public CurrentPosition(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CurrentPosition that = (CurrentPosition) o;
        return Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "CurrentPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
