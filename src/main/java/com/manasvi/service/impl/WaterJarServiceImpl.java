package com.manasvi.service.impl;

import com.manasvi.entity.WaterJar;
import com.manasvi.repository.WaterJarRepository;
import com.manasvi.service.WaterJarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterJarServiceImpl implements WaterJarService {

    @Autowired
    private WaterJarRepository waterJarRepository;

    @Override
    public WaterJar addWaterJar(WaterJar waterJar) {
        return this.waterJarRepository.save(waterJar);
    }

    @Override
    public WaterJar updateWaterJar(Long id, WaterJar updatedWaterJar) {
        Optional<WaterJar> existingJar = waterJarRepository.findById(id);
        if (existingJar.isPresent()) {
            WaterJar jar = existingJar.get();
            jar.setJarOwner(updatedWaterJar.getJarOwner());
            jar.setTotalJars(updatedWaterJar.getTotalJars());
            jar.setDate(updatedWaterJar.getDate());
            return waterJarRepository.save(jar);
        } else {
            throw new RuntimeException("WaterJar record not found with ID: " + id);
        }
    }

    @Override
    public void deleteWaterJar(Long id) {
        this.waterJarRepository.deleteById(id);
    }

    @Override
    public Optional<WaterJar> getWaterJarById(Long id) {
        return this.waterJarRepository.findById(id);
    }

    @Override
    public List<WaterJar> getAllWaterJars() {
        return this.waterJarRepository.findAll();
    }

    @Override
    public List<WaterJar> findByJarOwner(String jarOwner) {
        return this.waterJarRepository.findByJarOwner(jarOwner);
    }

    @Override
    public List<WaterJar> findByDate(String date) {
        return this.waterJarRepository.findByDate(date);
    }
}
