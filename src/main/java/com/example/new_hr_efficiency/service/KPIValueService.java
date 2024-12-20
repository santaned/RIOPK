package com.example.new_hr_efficiency.service;

import com.example.new_hr_efficiency.model.KPIValue;
import com.example.new_hr_efficiency.repository.KPIValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class KPIValueService {

    @Autowired
    private KPIValueRepository kpiValueRepository;

    public List<KPIValue> getKPIValues() {
        return kpiValueRepository.findAll();
    }

    public KPIValue saveKPIValue(KPIValue kpiValue) {
        return kpiValueRepository.save(kpiValue);
    }

    public KPIValue getKPIValueById(Long id) {
        return kpiValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI Value not found"));
    }

    public KPIValue updateKPIValue(Long id, KPIValue kpiValue) {
        KPIValue existingKPIValue = kpiValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI Value not found"));
        existingKPIValue.setActualValue(kpiValue.getActualValue());
        existingKPIValue.setDate(kpiValue.getDate());
        return kpiValueRepository.save(existingKPIValue);
    }

    public KPIValue deleteKPIValue(Long id) {
        KPIValue existingKPIValue = kpiValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI value not found"));
        kpiValueRepository.deleteById(id);
        return existingKPIValue;
    }

}
