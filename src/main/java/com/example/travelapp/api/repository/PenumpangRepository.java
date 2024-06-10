package com.example.travelapp.api.repository;

import com.example.travelapp.api.model.Penumpang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenumpangRepository extends JpaRepository<Penumpang, Long> {
}

