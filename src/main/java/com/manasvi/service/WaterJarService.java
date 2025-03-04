package com.manasvi.service;

import com.manasvi.entity.WaterJar;
import java.util.List;
import java.util.Optional;

public interface WaterJarService {

    WaterJar addWaterJar(WaterJar waterJar);
    
    WaterJar updateWaterJar(Long id, WaterJar waterJar);
    
    void deleteWaterJar(Long id);
    
    Optional<WaterJar> getWaterJarById(Long id);
    
    List<WaterJar> getAllWaterJars();
    
    List<WaterJar> findByJarOwner(String jarOwner);
    
    List<WaterJar> findByDate(String date);
}
