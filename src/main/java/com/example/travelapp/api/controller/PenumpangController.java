package com.example.travelapp.api.controller;

import com.example.travelapp.api.exception.ResourceNotFoundException;
import com.example.travelapp.api.model.Penumpang;
import com.example.travelapp.api.repository.PenumpangRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penumpang")
public class PenumpangController {

    @Autowired
    private PenumpangRepository penumpangRepository;

    @GetMapping
    public ResponseEntity<List<Penumpang>> getAllPenumpangs() {
        List<Penumpang> penumpangs = penumpangRepository.findAll();
        return new ResponseEntity<>(penumpangs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPenumpang(@Valid @RequestBody Penumpang penumpang, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Penumpang createdPenumpang = penumpangRepository.save(penumpang);
        return new ResponseEntity<>(createdPenumpang, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Penumpang> getPenumpangById(@PathVariable(value = "id") Long id) {
        Penumpang penumpang = penumpangRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Penumpang dengan ID " + id + " tidak ditemukan"));
        return ResponseEntity.ok().body(penumpang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePenumpang(@PathVariable(value = "id") Long id, @Valid @RequestBody Penumpang penumpangDetails) {
        Penumpang penumpang = penumpangRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Penumpang dengan ID " + id + " tidak ditemukan"));

        penumpang.setNama(penumpangDetails.getNama());
        penumpang.setNoTelp(penumpangDetails.getNoTelp());

        Penumpang updatedPenumpang = penumpangRepository.save(penumpang);
        return ResponseEntity.ok(updatedPenumpang);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePenumpang(@PathVariable(value = "id") Long id) {
        Penumpang penumpang = penumpangRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Penumpang dengan ID " + id + " tidak ditemukan"));

        penumpangRepository.delete(penumpang);
        return ResponseEntity.noContent().header("message", "Penumpang berhasil dihapus").build();
    }
}
