/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fernando
 */
public class MapRegionManager {
    private static MapRegionManager instance;
    private final Map<String, MapRegion> regions = new HashMap<>();
    
    public MapRegionManager() {
        instance = this;
    }
    
    public static MapRegionManager getMapRegionManagerInstance() {
        return instance;
    }
    
    public void addRegion(MapRegion region) {
        if(region.getId().contentEquals("fogless")) {
            region.unlock();
        }
        regions.put(region.getId(), region);
    }
    
    public void unlockRegion(String id) {
        if (regions.containsKey(id)) {
            regions.get(id).unlock();
        }
    }
    
    public MapRegion getRegion(String id) {
        return regions.get(id);
    }

    public Collection<MapRegion> getAllRegions() {
        return regions.values();
    }
}
