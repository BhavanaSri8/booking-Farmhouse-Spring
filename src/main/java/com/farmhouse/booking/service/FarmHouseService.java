package com.farmhouse.booking.service;

import com.farmhouse.booking.dto.farmHouseDTO;
import com.farmhouse.booking.dto.FarmHouseResponseDTO;
import java.util.List;

public interface FarmHouseService {

    FarmHouseResponseDTO createFarmHouse(farmHouseDTO farmHouseDTO);

}