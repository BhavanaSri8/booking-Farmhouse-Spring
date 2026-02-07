package com.farmhouse.booking.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class farmHouseDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(min = 5, max = 200, message = "Location must be between 5 and 200 characters")
    private String location;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be positive")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @NotNull(message = "Price per night is required")
    @Positive(message = "Price per night must be positive")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double pricePerNight;

    @NotNull(message = "Available from date is required")
    @FutureOrPresent(message = "Available from must be present or future date")
    private LocalDateTime availableFrom;

    @NotNull(message = "Available to date is required")
    @Future(message = "Available to must be future date")
    private LocalDateTime availableTo;

    private Boolean isAvailable = true;

    @NotBlank(message = "Contact email is required")
    @Email(message = "Email should be valid")
    private String contactEmail;

    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$",
            message = "Phone number must be valid")
    private String contactPhone;
}
