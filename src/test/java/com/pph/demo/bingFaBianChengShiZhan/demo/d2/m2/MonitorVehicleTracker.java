package com.pph.demo.bingFaBianChengShiZhan.demo.d2.m2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: pph
 * @date: 2020/1/8 09:15
 * @description:
 */
public class MonitorVehicleTracker {

    private final ConcurrentMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public MonitorVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
//        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (Objects.isNull(locations.replace(id, new Point(x, y)))) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }
}
