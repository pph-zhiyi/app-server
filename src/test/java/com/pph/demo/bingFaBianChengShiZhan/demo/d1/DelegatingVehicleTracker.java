package com.pph.demo.bingFaBianChengShiZhan.demo.d1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author: PPH
 * @Date: 2019-07-31 09:11
 * @Description:
 */
public class DelegatingVehicleTracker {

    private final ConcurrentMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(points);
    }

    public Map<String, Point> getLocations() {
//        return unmodifiableMap;
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("invalid vehicle name: ".concat(id));
    }
}
