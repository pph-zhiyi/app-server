package com.pph.demo.bingFaBianChengShiZhan.demo.d2.m1;

import java.util.*;

/**
 * @author: pph
 * @date: 2020/1/7 09:21
 * @description:
 */
public class MonitorVehicleTracker {

    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        return Optional.ofNullable(locations.get(id))
                .map(MutablePoint::new)
                .orElse(null);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = Objects.requireNonNull(locations.get(id), "No such id: ".concat(id));
        loc.x = x;
        loc.y = y;
    }

    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        return Optional.ofNullable(m)
                .map(map -> Collections.unmodifiableMap(new HashMap<String, MutablePoint>(map.size()) {{
                    for (String s : map.keySet()) {
                        put(s, map.get(s));
                    }
                }}))
                .orElse(Collections.emptyMap());
    }
}
