package com.example.travelapp.api.repository;

import com.example.travelapp.api.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    @Query("SELECT t FROM Travel t WHERE t.alamat LIKE %:kota%")
    List<Travel> findAllByKota(String kota);
}

