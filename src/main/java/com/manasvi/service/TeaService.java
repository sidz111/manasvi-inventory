package com.manasvi.service;

import com.manasvi.entity.Tea;
import java.util.List;
import java.util.Optional;

public interface TeaService {
    
    Tea addTea(Tea tea);
    
    Tea updateTea(Long id, Tea updatedTea);
    
    void deleteTea(Long id);
    
    Optional<Tea> getTeaById(Long id);
    
    List<Tea> getAllTeaRecords();
    
    List<Tea> findTeaByDate(String date);
    
    List<Tea> findTeaByType(String type);
}
