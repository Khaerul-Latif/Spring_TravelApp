package com.example.travelapp.api.controller;

import com.example.travelapp.api.exception.ResourceNotFoundException;
import com.example.travelapp.api.model.Travel;
import com.example.travelapp.api.repository.TravelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    @Autowired
    private TravelRepository travelRepository;

    @GetMapping
    public ResponseEntity<List<Travel>> getAllTravels() {
        List<Travel> travels = travelRepository.findAll();
        return new ResponseEntity<>(travels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTravel(@Valid @RequestBody Travel travel, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Travel createdTravel = travelRepository.save(travel);
        return new ResponseEntity<>(createdTravel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Travel> getTravelById(@PathVariable(value = "id") Long id) {
        Travel travel = travelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Travel dengan ID " + id + " tidak ditemukan"));
        return ResponseEntity.ok().body(travel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTravel(@PathVariable(value = "id") Long id, @Valid @RequestBody Travel travelDetails, BindingResult result) {
        Travel travel = travelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Travel dengan ID " + id + " tidak ditemukan"));

        travel.setNamaTravel(travelDetails.getNamaTravel());
        travel.setNoTelp(travelDetails.getNoTelp());
        travel.setAlamat(travelDetails.getAlamat());
        travel.setNoPolisi(travelDetails.getNoPolisi());
        travel.setJenisBus(travelDetails.getJenisBus());

        Travel updatedTravel = travelRepository.save(travel);
        return ResponseEntity.ok(updatedTravel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravel(@PathVariable(value = "id") Long id) {
        Travel travel = travelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Travel dengan ID " + id + " tidak ditemukan"));

        travelRepository.delete(travel);
        return ResponseEntity.noContent().header("message", "Travel berhasil dihapus").build();
    }

    @GetMapping("/jakarta")
    public ResponseEntity<List<Travel>> getTravelsByKota() {
        List<Travel> travels = travelRepository.findAllByKota("Jakarta");
        return new ResponseEntity<>(travels, HttpStatus.OK);
    }
}
