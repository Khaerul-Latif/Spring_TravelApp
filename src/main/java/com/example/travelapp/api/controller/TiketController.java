package com.example.travelapp.api.controller;

import com.example.travelapp.api.exception.ResourceNotFoundException;
import com.example.travelapp.api.model.Tiket;
import com.example.travelapp.api.repository.TiketRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiket")
public class TiketController {

    @Autowired
    private TiketRepository tiketRepository;

    // Endpoint untuk menambahkan handler jika ID penumpang atau ID travel tidak ditemukan
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping
    public ResponseEntity<List<Tiket>> getAllTikets() {
        List<Tiket> tikets = tiketRepository.findAll();
        return new ResponseEntity<>(tikets, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTiket(@Valid @RequestBody Tiket tiket, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Tiket createdTiket = tiketRepository.save(tiket);
        return new ResponseEntity<>(createdTiket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tiket> getTiketById(@PathVariable(value = "id") Long id) {
        Tiket tiket = tiketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tiket dengan ID " + id + " tidak ditemukan"));
        return ResponseEntity.ok().body(tiket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTiket(@PathVariable(value = "id") Long id, @Valid @RequestBody Tiket tiketDetails, BindingResult result) {
        Tiket tiket = tiketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tiket dengan ID " + id + " tidak ditemukan"));

        tiket.setIdPenumpang(tiketDetails.getIdPenumpang());
        tiket.setIdTravel(tiketDetails.getIdTravel());
        tiket.setJadwal(tiketDetails.getJadwal());

        Tiket updatedTiket = tiketRepository.save(tiket);
        return ResponseEntity.ok(updatedTiket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTiket(@PathVariable(value = "id") Long id) {
        Tiket tiket = tiketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tiket dengan ID " + id + " tidak ditemukan"));

        tiketRepository.delete(tiket);
        return ResponseEntity.noContent().header("message", "Tiket berhasil dihapus").build();
    }
}
