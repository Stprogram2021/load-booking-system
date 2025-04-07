package com.example.loadbooking.service;

import com.example.loadbooking.dto.LoadDTO;
import java.util.List;
import java.util.UUID;

public interface LoadService {
    LoadDTO createLoad(LoadDTO loadDTO);
    List<LoadDTO> getAllLoads(String shipperId, String truckType);
    LoadDTO getLoadById(UUID loadId);
    LoadDTO updateLoad(UUID loadId, LoadDTO loadDTO);
    void deleteLoad(UUID loadId);
}
