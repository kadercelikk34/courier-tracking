package com.migros.couriertracking.utils;


import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * DistanceCalculator formulüne göre iki nokta arasındaki mesafeyi metre olarak hesaplar
 */
public class DistanceCalculator {
    private static final DecimalFormat df = new DecimalFormat("#.#");

    public static double distanceAsMetric(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515 * 1.609344 * 1000;
            return (dist);
        }
    }

    public static String distanceFormat(double distance) {
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(distance) + " m";

    }


}
