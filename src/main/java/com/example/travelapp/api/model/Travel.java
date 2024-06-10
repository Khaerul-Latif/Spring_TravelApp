package com.example.travelapp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "nama_travel tidak boleh kosong")
    @JsonProperty("nama_travel")
    private String namaTravel;

    @NotEmpty(message = "no_telp tidak boleh kosong")
    @JsonProperty("no_telp")
    private String noTelp;

    @NotEmpty(message = "alamat tidak boleh kosong")
    private String alamat;

    @NotEmpty(message = " no_polisi boleh kosong")
    @JsonProperty("no_polisi")
    private String noPolisi;

    @NotEmpty(message = "jenis_bus tidak boleh kosong")
    @JsonProperty("jenis_bus")
    private String jenisBus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaTravel() {
        return namaTravel;
    }

    public void setNamaTravel(String namaTravel) {
        this.namaTravel = namaTravel;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getJenisBus() {
        return jenisBus;
    }

    public void setJenisBus(String jenisBus) {
        this.jenisBus = jenisBus;
    }
}
