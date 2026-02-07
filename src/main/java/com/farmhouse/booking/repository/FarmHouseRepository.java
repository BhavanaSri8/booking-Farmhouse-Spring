package com.farmhouse.booking.repository;

import com.farmhouse.booking.entity.farmHouse;
import com.farmhouse.booking.entity.farmHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FarmHouseRepository extends JpaRepository<farmHouse, Long> {



    boolean existsByName(String name);
}