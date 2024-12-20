package com.example.new_hr_efficiency.service;

import com.example.new_hr_efficiency.model.KPI;
import com.example.new_hr_efficiency.repository.KPIRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class KPIService {

    @Autowired
    private KPIRepository kpiRepository;

    public List<KPI> getKPIs() {
        return kpiRepository.findAll();
    }

    public KPI saveKPI(KPI kpi) {
        return kpiRepository.save(kpi);
    }

    public KPI getKPI(Long id) {
        return kpiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI not found"));
    }

    public KPI updateKPI(Long id, KPI kpi) {
        KPI existingKPI = kpiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI not found"));
        existingKPI.setName(kpi.getName());
        existingKPI.setDescription(kpi.getDescription());
        existingKPI.setTargetValue(kpi.getTargetValue());
        existingKPI.setMeasurementUnit(kpi.getMeasurementUnit());
        return kpiRepository.save(existingKPI);
    }

    public KPI deleteKPI(Long id) {
        KPI existingKPI = kpiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI not found"));
        kpiRepository.deleteById(id);
        return existingKPI;
    }

}
