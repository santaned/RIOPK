package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.KPI;
import com.example.new_hr_efficiency.model.KPIValue;
import com.example.new_hr_efficiency.service.KPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class KPIController {

    @Autowired
    private KPIService kpiService;

    @PostMapping("/kpi")
    public ResponseEntity<KPI> saveKPI(@RequestBody KPI kpi) {
        KPI savedKPI = kpiService.saveKPI(kpi);
        return new ResponseEntity<>(savedKPI, HttpStatus.CREATED);
    }

    @GetMapping("/kpi/{id}")
    public ResponseEntity<KPI> getKPI(@PathVariable Long id) {
        KPI kpi;
        try {
            kpi = kpiService.getKPI(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(kpi, HttpStatus.OK);
    }

    @GetMapping("/kpis")
    public List<KPI> getKPIs() {
        return kpiService.getKPIs();
    }

    @PatchMapping("/kpi/{id}")
    public ResponseEntity<KPI> updateKPI(@PathVariable Long id, @RequestBody KPI kpi) {
        KPI updatedKPI;
        try {
            updatedKPI = kpiService.updateKPI(id, kpi);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedKPI, HttpStatus.OK);
    }

    @DeleteMapping("/kpi/{id}")
    public ResponseEntity<KPI> deleteKPI(@PathVariable Long id) {
        KPI deletedKPI;
        try {
            deletedKPI = kpiService.deleteKPI(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedKPI, HttpStatus.OK);
    }

    @GetMapping("/kpi/{id}/kpi_values")
    public ResponseEntity<List<KPIValue>> getKPIValuesByKPI(@PathVariable Long id) {
        KPI kpi;
        try {
            kpi = kpiService.getKPI(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(kpi.getKpiValues(), HttpStatus.OK);
    }

}
