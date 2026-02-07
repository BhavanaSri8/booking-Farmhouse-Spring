package com.farmhouse.booking.service;

import com.farmhouse.booking.dto.farmHouseDTO;
import com.farmhouse.booking.dto.FarmHouseResponseDTO;
import com.farmhouse.booking.entity.farmHouse;
import com.farmhouse.booking.exceptions.DuplicateResourceException;
import com.farmhouse.booking.exceptions.ResourceNotFoundException;
import com.farmhouse.booking.exceptions.ValidationException;
import com.farmhouse.booking.repository.FarmHouseRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FarmHouseServiceImpl implements FarmHouseService {

    private final FarmHouseRepository farmHouseRepository;

    @Autowired
    public FarmHouseServiceImpl(FarmHouseRepository farmHouseRepository) {
        this.farmHouseRepository = farmHouseRepository;
    }
    @Override
    public FarmHouseResponseDTO createFarmHouse(farmHouseDTO farmHouseDTO) {
        // Check if farm house with same name already exists
        if (farmHouseRepository.existsByName(farmHouseDTO.getName())) {
            throw new DuplicateResourceException("FarmHouse", "name", farmHouseDTO.getName());
        }

        // Validate date range
        validateDateRange(farmHouseDTO.getAvailableFrom(), farmHouseDTO.getAvailableTo());

        // Convert DTO to Entity
        farmHouse farmHouse = convertToEntity(farmHouseDTO);

        // Save to database
        farmHouse savedFarmHouse = farmHouseRepository.save(farmHouse);

        // Convert Entity to Response DTO
        return convertToResponseDTO(savedFarmHouse);
    }
    private void validateDateRange(LocalDateTime availableFrom, LocalDateTime availableTo) {
        if (availableFrom.isAfter(availableTo)) {
            throw new ValidationException("Available from date cannot be after available to date");
        }

        if (availableFrom.isBefore(LocalDateTime.now())) {
            throw new ValidationException("Available from date cannot be in the past");
        }
    }

    private farmHouse convertToEntity(farmHouseDTO dto) {
        farmHouse farmHouse = new farmHouse();
        farmHouse.setName(dto.getName());
        farmHouse.setLocation(dto.getLocation());
        farmHouse.setCapacity(dto.getCapacity());
        farmHouse.setPricePerNight(dto.getPricePerNight());
        farmHouse.setAvailableFrom(dto.getAvailableFrom());
        farmHouse.setAvailableTo(dto.getAvailableTo());
        farmHouse.setIsAvailable(dto.getIsAvailable());
        farmHouse.setContactEmail(dto.getContactEmail());
        farmHouse.setContactPhone(dto.getContactPhone());
        return farmHouse;
    }

    private FarmHouseResponseDTO convertToResponseDTO(farmHouse farmHouse) {
        FarmHouseResponseDTO responseDTO = new FarmHouseResponseDTO();
        responseDTO.setId(farmHouse.getId());
        responseDTO.setName(farmHouse.getName());
        responseDTO.setLocation(farmHouse.getLocation());
        responseDTO.setCapacity(farmHouse.getCapacity());
        responseDTO.setPricePerNight(farmHouse.getPricePerNight());
        responseDTO.setAvailableFrom(farmHouse.getAvailableFrom());
        responseDTO.setAvailableTo(farmHouse.getAvailableTo());
        responseDTO.setIsAvailable(farmHouse.getIsAvailable());
        responseDTO.setContactEmail(farmHouse.getContactEmail());
        responseDTO.setContactPhone(farmHouse.getContactPhone());
        responseDTO.setCreatedAt(farmHouse.getCreatedAt());
        responseDTO.setUpdatedAt(farmHouse.getUpdatedAt());
        return responseDTO;
    }
}
