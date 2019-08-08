package com.projectcrud.crudloginde.web.dto;

public class ProductDto {
    private Long kode_barang;
    private String namaBarang;
    private String hargaBarang;
    private String stokBarang;

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
    @Override
    public String toString() {
        return "Product [kode_barang=" + kode_barang + ", namaBarang=" + namaBarang +
                ", hargaBarang=" + hargaBarang + ", stokBarang=" + stokBarang   + "]";
    }
}
