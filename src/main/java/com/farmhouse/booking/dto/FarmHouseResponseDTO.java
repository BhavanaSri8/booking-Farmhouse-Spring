package com.farmhouse.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmHouseResponseDTO {

    private Long id;
    private String name;
    private String location;
    private Integer capacity;
    private Double pricePerNight;
    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;
    private Boolean isAvailable;
    private String contactEmail;
    private String contactPhone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}