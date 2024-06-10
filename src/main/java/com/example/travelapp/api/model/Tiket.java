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


    @ManyToOne
    @JoinColumn(name = "id_penumpang", referencedColumnName = "id")
    @JsonProperty("id_penumpang")
    private Penumpang idPenumpang;

    @ManyToOne
    @JoinColumn(name = "id_travel", referencedColumnName = "id")
    @JsonProperty("id_travel")
    private Travel idTravel;

    private LocalDateTime jadwal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Penumpang getIdPenumpang() {
        return idPenumpang;
    }

    public void setIdPenumpang(Penumpang idPenumpang) {
        this.idPenumpang = idPenumpang;
    }

    public Travel getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(Travel idTravel) {
        this.idTravel = idTravel;
    }

    public LocalDateTime getJadwal() {
        return jadwal;
    }

    public void setJadwal(LocalDateTime jadwal) {
        this.jadwal = jadwal;
    }
}
