package com.example.new_hr_efficiency.controller;

import com.example.new_hr_efficiency.model.KPI;
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
        KPI kpi = kpiService.getKPI(id);
        return new ResponseEntity<>(kpi, HttpStatus.OK);
    }

    @GetMapping("/kpis")
    public List<KPI> getKPIs() {
        return kpiService.getKPIs();
    }

    @PatchMapping("/kpi/{id}")
    public ResponseEntity<KPI> updateKPI(@PathVariable Long id, @RequestBody KPI kpi) {
        KPI updatedKPI = kpiService.updateKPI(id, kpi);
        return new ResponseEntity<>(updatedKPI, HttpStatus.OK);
    }

    @DeleteMapping("/kpi/{id}")
    public ResponseEntity<KPI> deleteKPI(@PathVariable Long id) {
        KPI deletedKPI = kpiService.deleteKPI(id);
        return new ResponseEntity<>(deletedKPI, HttpStatus.OK);
    }

}
