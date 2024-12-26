package com.example.new_hr_efficiency.service;

import com.example.new_hr_efficiency.model.KPIValue;
import com.example.new_hr_efficiency.repository.EmployeeRepository;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<KPIValue> getKPIValuesByEmployeeId() {
        return kpiValueRepository.findAll();
    }

    public KPIValue saveKPIValue(KPIValue kpiValue) {
        return kpiValueRepository.save(kpiValue);
    }

    public KPIValue deleteKPIValue(Long id) {
        KPIValue existingKPIValue = kpiValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KPI value not found"));
        kpiValueRepository.deleteById(id);
        return existingKPIValue;
    }

}
