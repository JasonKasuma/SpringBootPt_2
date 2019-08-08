package com.projectcrud.crudloginde.model;

import javax.persistence.*;

@Entity
@Table(name="Tbl_Perusahaan")
public class Perusahaan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kode_perusahaan;

    @Column(name = "nama_perusahaan")
    private String namaPerusahaan;

    public Long getKode_perusahaan() {
        return kode_perusahaan;
    }

    public void setKode_perusahaan(Long kode_perusahaan) {
        this.kode_perusahaan = kode_perusahaan;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    @Override
    public String toString() {
        return "Perusahaan [kode_perusahaan=" + kode_perusahaan + ", nama_perusahaan=" + namaPerusahaan + "]";
    }
}
