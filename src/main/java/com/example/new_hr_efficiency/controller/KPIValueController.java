package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.KPIValue;
import com.example.new_hr_efficiency.service.KPIValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class KPIValueController {

    @Autowired
    private KPIValueService kpiValueService;

    @PostMapping("/kpiValue")
    public ResponseEntity<KPIValue> saveKPIValue(@RequestBody KPIValue kpiValue){
        KPIValue savedKPIValue = kpiValueService.saveKPIValue(kpiValue);
        return new ResponseEntity<>(savedKPIValue, HttpStatus.CREATED);
    }

    @GetMapping("/kpiValue/{id}")
    public ResponseEntity<KPIValue> getKPIValue(@PathVariable Long id){
        KPIValue kpiValue = kpiValueService.getKPIValueById(id);
        return new ResponseEntity<>(kpiValue, HttpStatus.OK);
    }

    @GetMapping("/kpiValues")
    public List<KPIValue> getKPIValues(){
        return kpiValueService.getKPIValues();
    }

    @PatchMapping("/kpiValue/{id}")
    public ResponseEntity<KPIValue> updateKPIValue(@PathVariable Long id, @RequestBody KPIValue kpiValue){
        KPIValue updatedKPIValue = kpiValueService.updateKPIValue(id, kpiValue);
        return new ResponseEntity<>(updatedKPIValue, HttpStatus.OK);
    }

    @DeleteMapping("/kpiValue/{id}")
    public ResponseEntity<KPIValue> deleteKPIValue(@PathVariable Long id){
        KPIValue deletedKPIValue = kpiValueService.deleteKPIValue(id);
        return new ResponseEntity<>(deletedKPIValue, HttpStatus.OK);
    }

}
