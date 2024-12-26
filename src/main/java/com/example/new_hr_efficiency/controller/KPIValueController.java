package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.KPIValue;
import com.example.new_hr_efficiency.service.KPIValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class KPIValueController {

    @Autowired
    private KPIValueService kpiValueService;

    @PostMapping("/kpiValue")
    public ResponseEntity<KPIValue> saveKPIValue(@RequestBody KPIValue kpiValue) {
        KPIValue savedKPIValue = kpiValueService.saveKPIValue(kpiValue);
        return new ResponseEntity<>(savedKPIValue, HttpStatus.CREATED);
    }

    @DeleteMapping("/kpiValue/{id}")
    public ResponseEntity<KPIValue> deleteKPIValue(@PathVariable Long id) {
        KPIValue deletedKPIValue;
        try {
            deletedKPIValue = kpiValueService.deleteKPIValue(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedKPIValue, HttpStatus.OK);
    }

}
