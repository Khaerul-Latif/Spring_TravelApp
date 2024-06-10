package com.example.travelapp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
@Table(name = "tiket")
public class Tiket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "id_penumpang tidak boleh kosong")
    @JsonProperty("id_penumpang")
    private Long idPenumpang;

    @NotEmpty(message = "id_travel tidak boleh kosong")
    @JsonProperty("id_travel")
    private Long idTravel;

    private LocalDateTime jadwal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPenumpang() {
        return idPenumpang;
    }

    public void setIdPenumpang(Long idPenumpang) {
        this.idPenumpang = idPenumpang;
    }

    public Long getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(Long idTravel) {
        this.idTravel = idTravel;
    }

    public LocalDateTime getJadwal() {
        return jadwal;
    }

    public void setJadwal(LocalDateTime jadwal) {
        this.jadwal = jadwal;
    }
}
