package com.farmhouse.booking.controller;

import com.farmhouse.booking.dto.farmHouseDTO;
import com.farmhouse.booking.dto.FarmHouseResponseDTO;
import com.farmhouse.booking.dto.farmHouseDTO;
import com.farmhouse.booking.service.FarmHouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmhouses")
@CrossOrigin(origins = "*")
public class FarmHouseController {

    private final FarmHouseService farmHouseService;

    @Autowired
    public FarmHouseController(FarmHouseService farmHouseService) {
        this.farmHouseService = farmHouseService;
    }

    @PostMapping
    public ResponseEntity<FarmHouseResponseDTO> createFarmHouse(
            @Valid @RequestBody farmHouseDTO farmHouseDTO) {

        FarmHouseResponseDTO createdFarmHouse = farmHouseService.createFarmHouse(farmHouseDTO);
        return new ResponseEntity<>(createdFarmHouse, HttpStatus.CREATED);
    }
}