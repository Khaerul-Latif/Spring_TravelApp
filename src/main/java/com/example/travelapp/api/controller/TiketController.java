package com.example.travelapp.api.controller;

import com.example.travelapp.api.exception.ResourceNotFoundException;
import com.example.travelapp.api.model.Penumpang;
import com.example.travelapp.api.model.Tiket;
import com.example.travelapp.api.model.Travel;
import com.example.travelapp.api.repository.PenumpangRepository;
import com.example.travelapp.api.repository.TiketRepository;
import com.example.travelapp.api.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tiket")
public class TiketController {

    @Autowired
    private TiketRepository tiketRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private PenumpangRepository penumpangRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Tiket> getTiketById(@PathVariable(value = "id") Long id) {
        Tiket tiket = tiketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tiket dengan ID " + id + " tidak ditemukan"));
        return ResponseEntity.ok(tiket);
    }

    @PostMapping
    public ResponseEntity<Tiket> createTiket(@RequestBody Tiket tiket) {
        Tiket createdTiket = tiketRepository.save(tiket);
        return new ResponseEntity<>(createdTiket, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Tiket>> getAllTikets() {
        List<Tiket> tikets = tiketRepository.findAll();
        List<Tiket> responseTikets = new ArrayList<>();

        for (Tiket tiket : tikets) {
            Long idPenumpang = tiket.getIdPenumpang().getId();
            Long idTravel = tiket.getIdTravel().getId();

            Penumpang penumpang = penumpangRepository.findById(idPenumpang).orElseThrow(() ->
                    new ResourceNotFoundException("Penumpang dengan ID " + idPenumpang + " tidak ditemukan"));

            Travel travel = travelRepository.findById(idTravel).orElseThrow(() ->
                    new ResourceNotFoundException("Travel dengan ID " + idTravel + " tidak ditemukan"));

            Tiket responseTiket = new Tiket();
            responseTiket.setId(tiket.getId());
            responseTiket.setJadwal(tiket.getJadwal());
            responseTiket.setIdPenumpang(penumpang);
            responseTiket.setIdTravel(travel);

            responseTikets.add(responseTiket);
        }

        return new ResponseEntity<>(responseTikets, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Tiket> updateTiket(@PathVariable(value = "id") Long id, @RequestBody Tiket tiketDetails) {
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
        return ResponseEntity.noContent().build();
    }
}
