package com.farmhouse.booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "farm_houses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class farmHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Location is required")
    @Size(min = 5, max = 200, message = "Location must be between 5 and 200 characters")
    @Column(nullable = false)
    private String location;

    @Positive(message = "Capacity must be positive")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Column(nullable = false)
    private Integer capacity;

    @Positive(message = "Price per night must be positive")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(nullable = false)
    private Double pricePerNight;

    @NotNull(message = "Available from date is required")
    @FutureOrPresent(message = "Available from must be present or future date")
    @Column(nullable = false)
    private LocalDateTime availableFrom;

    @NotNull(message = "Available to date is required")
    @Future(message = "Available to must be future date")
    @Column(nullable = false)
    private LocalDateTime availableTo;

    @Column(nullable = false)
    private Boolean isAvailable = true;

    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String contactEmail;

    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$",
            message = "Phone number must be valid")
    @Column(nullable = false)
    private String contactPhone;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt;


    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }


}
