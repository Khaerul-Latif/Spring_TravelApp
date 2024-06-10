package com.example.travelapp.api.repository;

import com.example.travelapp.api.model.Tiket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiketRepository extends JpaRepository<Tiket, Long> {
}

