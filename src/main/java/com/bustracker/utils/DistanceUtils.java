package com.bustracker.utils;

import com.bustracker.entity.Bus;
import com.bustracker.entity.Station;

public class DistanceUtils {

    /*
    * @return km
    * */
    public static double distanceBus2Bus(Bus start, Bus end) {
        return getDistance(start, end.getLongitude(), end.getLatitude());
    }

    /*
     * @return km
     * */
    public static double distanceBus2Station(Bus bus, Station station) {
        return getDistance(bus, station.getLon(), station.getLat());
    }

    private static double getDistance(Bus bus, double lon, double lat) {
        double theta = bus.getLongitude() - lon;
        double dist = Math.sin(deg2rad(bus.getLatitude())) * Math.sin(deg2rad(lat)) +
                Math.cos(deg2rad(bus.getLatitude())) * Math.cos(deg2rad(lat)) *
                        Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180);
    }

    private static double rad2deg(double red) {
        return (red / Math.PI * 180);
    }
}
