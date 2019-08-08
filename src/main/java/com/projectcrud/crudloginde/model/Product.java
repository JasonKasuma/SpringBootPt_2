package com.projectcrud.crudloginde.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name="Tbl_Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kode_barang;

    @Column(name="nama_barang")
    private String namaBarang;

    @Column(name="harga_barang")
    private String hargaBarang;

    @Column(name="stok_barang")
    private String stokBarang;

    @Lob
    @Column(name="fileName")
    private String fileName;

    public Long getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(Long kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(String hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public String getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(String stokBarang) {
        this.stokBarang = stokBarang;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Product [kode_barang=" + kode_barang + ", namaBarang=" + namaBarang +
                ", hargaBarang=" + hargaBarang + ", stokBarang=" + stokBarang   + "]";
    }
}
