package com.migros.couriertracking.utils;


import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * DistanceCalculator formulüne göre iki nokta arasındaki mesafeyi metre olarak hesaplar
 */
public class DistanceCalculator {
    private DistanceCalculator() {
        throw new IllegalStateException("DistanceCalculator class");
    }

    private static final DecimalFormat df = new DecimalFormat("#.#");
    private static final int THOUSAND_VALUE = 1000;
    private static final int SIXTY_VALUE = 60;

    //iki nokta arasındaki mesafeyi metre olarak verir.
    public static double distanceAsMetric(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * SIXTY_VALUE * 1.1515 * 1.609344 * THOUSAND_VALUE;
            return (dist);
        }
    }

    public static String distanceFormat(double distance) {
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(distance) + " m";

    }

    // verilen mesafe ve hıza göre dakikayı hesaplar (time = distance / speed)
    public static double distanceAsTime(double distance, double speed) {
        double speedMeters = (speed * THOUSAND_VALUE) / SIXTY_VALUE;
        return  Math.round(distance / speedMeters);

    }


}
