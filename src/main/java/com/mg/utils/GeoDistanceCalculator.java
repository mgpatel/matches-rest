package com.mg.utils;

/**
 * Created by mayank.patel on 21/12/2016.
 */
public class GeoDistanceCalculator {

    public static int getDistance(double lat1, double lon1, double lat2, double lon2) {
        final int r = 6371;
        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) *
                Math.sin(latDistance / 2) +   Math.cos(Math.toRadians(lat1))*
                Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) *
                Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 -
                a));
        double distance = r * c;

        return (int)Math.round(distance);
    }
}
