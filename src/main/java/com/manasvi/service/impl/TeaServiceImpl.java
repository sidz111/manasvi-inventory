package com.manasvi.service.impl;

import com.manasvi.entity.Tea;
import com.manasvi.repository.TeaRepository;
import com.manasvi.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeaServiceImpl implements TeaService {

    @Autowired
    private TeaRepository teaRepository;

    @Override
    public Tea addTea(Tea tea) {
        return teaRepository.save(tea);
    }

    @Override
    public Tea updateTea(Long id, Tea updatedTea) {
        Optional<Tea> existingTea = teaRepository.findById(id);
        if (existingTea.isPresent()) {
            Tea tea = existingTea.get();
            tea.setDate(updatedTea.getDate());
            tea.setType(updatedTea.getType());
            tea.setTotalTeaCups(updatedTea.getTotalTeaCups());
            return teaRepository.save(tea);
        } else {
            throw new RuntimeException("Tea record not found with ID: " + id);
        }
    }

    @Override
    public void deleteTea(Long id) {
        teaRepository.deleteById(id);
    }

    @Override
    public Optional<Tea> getTeaById(Long id) {
        return teaRepository.findById(id);
    }

    @Override
    public List<Tea> getAllTeaRecords() {
        return teaRepository.findAll();
    }

    @Override
    public List<Tea> findTeaByDate(String date) {
        return teaRepository.findByDate(date);
    }

    @Override
    public List<Tea> findTeaByType(String type) {
        return teaRepository.findByType(type);
    }
}
