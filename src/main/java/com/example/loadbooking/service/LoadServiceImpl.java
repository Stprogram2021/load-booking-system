package com.example.loadbooking.service;

import com.example.loadbooking.dto.FacilityDTO;
import com.example.loadbooking.dto.LoadDTO;
import com.example.loadbooking.exception.ResourceNotFoundException;
import com.example.loadbooking.model.Facility;
import com.example.loadbooking.model.Load;
import com.example.loadbooking.model.LoadStatus;
import com.example.loadbooking.repo.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private LoadRepository loadRepository;

    @Override
    public LoadDTO createLoad(LoadDTO loadDTO) {
        Load load = new Load();
        load.setShipperId(loadDTO.getShipperId());
        load.setFacility(convertToFacility(loadDTO.getFacility()));
        load.setProductType(loadDTO.getProductType());
        load.setTruckType(loadDTO.getTruckType());
        load.setNoOfTrucks(loadDTO.getNoOfTrucks());
        load.setWeight(loadDTO.getWeight());
        load.setComment(loadDTO.getComment());
        load.setDatePosted(loadDTO.getDatePosted());
        load.setStatus(LoadStatus.POSTED);

        Load savedLoad = loadRepository.save(load);
        return convertToDTO(savedLoad);
    }

    @Override
    public List<LoadDTO> getAllLoads(String shipperId, String truckType) {
        List<Load> loads = loadRepository.findAll();
        return loads.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public LoadDTO getLoadById(UUID loadId) {
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));
        return convertToDTO(load);
    }

    @Override
    public LoadDTO updateLoad(UUID loadId, LoadDTO loadDTO) {
        Load load = loadRepository.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));

        load.setShipperId(loadDTO.getShipperId());
        load.setFacility(convertToFacility(loadDTO.getFacility()));
        load.setProductType(loadDTO.getProductType());
        load.setTruckType(loadDTO.getTruckType());
        load.setNoOfTrucks(loadDTO.getNoOfTrucks());
        load.setWeight(loadDTO.getWeight());
        load.setComment(loadDTO.getComment());

        Load updatedLoad = loadRepository.save(load);
        return convertToDTO(updatedLoad);
    }

    @Override
    public void deleteLoad(UUID loadId) {
        if (!loadRepository.existsById(loadId)) {
            throw new ResourceNotFoundException("Load not found");
        }
        loadRepository.deleteById(loadId);
    }

    private LoadDTO convertToDTO(Load load) {
        LoadDTO dto = new LoadDTO();
        dto.setId(load.getId());
        dto.setShipperId(load.getShipperId());
        dto.setFacility(convertToFacilityDTO(load.getFacility()));
        dto.setProductType(load.getProductType());
        dto.setTruckType(load.getTruckType());
        dto.setNoOfTrucks(load.getNoOfTrucks());
        dto.setWeight(load.getWeight());
        dto.setComment(load.getComment());
        dto.setDatePosted(load.getDatePosted());
        dto.setStatus(load.getStatus());
        return dto;
    }

    private Facility convertToFacility(FacilityDTO facilityDTO) {
        if (facilityDTO == null) {
            return null;
        }
        Facility facility = new Facility();
        facility.setLoadingPoint(facilityDTO.getLoadingPoint());
        facility.setUnloadingPoint(facilityDTO.getUnloadingPoint());
        facility.setLoadingDate(facilityDTO.getLoadingDate());
        facility.setUnloadingDate(facilityDTO.getUnloadingDate());
        return facility;
    }

    private FacilityDTO convertToFacilityDTO(Facility facility) {
        if (facility == null) {
            return null;
        }
        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setLoadingPoint(facility.getLoadingPoint());
        facilityDTO.setUnloadingPoint(facility.getUnloadingPoint());
        facilityDTO.setLoadingDate(facility.getLoadingDate());
        facilityDTO.setUnloadingDate(facility.getUnloadingDate());
        return facilityDTO;
    }
}
