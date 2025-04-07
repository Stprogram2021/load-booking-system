package com.example.loadbooking.controller;

import com.example.loadbooking.dto.LoadDTO;
import com.example.loadbooking.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping
    public ResponseEntity<LoadDTO> createLoad(@RequestBody LoadDTO loadDTO) {
        LoadDTO createdLoad = loadService.createLoad(loadDTO);
        return ResponseEntity.ok(createdLoad);
    }

    @GetMapping
    public ResponseEntity<List<LoadDTO>> getAllLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType) {
        List<LoadDTO> loads = loadService.getAllLoads(shipperId, truckType);
        return ResponseEntity.ok(loads);
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<LoadDTO> getLoadById(@PathVariable UUID loadId) {
        LoadDTO load = loadService.getLoadById(loadId);
        return ResponseEntity.ok(load);
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<LoadDTO> updateLoad(@PathVariable UUID loadId, @RequestBody LoadDTO loadDTO) {
        LoadDTO updatedLoad = loadService.updateLoad(loadId, loadDTO);
        return ResponseEntity.ok(updatedLoad);
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<String> deleteLoad(@PathVariable UUID loadId) {
        loadService.deleteLoad(loadId);
        return ResponseEntity.ok("Load deleted successfully");
    }
}
