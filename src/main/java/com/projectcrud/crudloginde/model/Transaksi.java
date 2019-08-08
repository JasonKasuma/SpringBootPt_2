package com.projectcrud.crudloginde.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Tbl_Transaksi")
public class Transaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kode_transaksi;

    private Date tanggalTransaksi;
    private String hargaTransaksi;
    private String orderQuantity;
    private String sisaStokBarang;
    private String grand_total;

    @ManyToOne
    @JoinColumn(name = "kode_perusahaan")
    private Perusahaan perusahaan;

    public Perusahaan getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(Perusahaan perusahaan) {
        this.perusahaan = perusahaan;
    }

    @ManyToOne
    @JoinColumn(name = "kode_barang")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Long getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(Long kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getHargaTransaksi() {
        return hargaTransaksi;
    }

    public void setHargaTransaksi(String hargaTransaksi) {
        this.hargaTransaksi = hargaTransaksi;
    }

    public String getSisaStokBarang() {
        return sisaStokBarang;
    }

    public void setSisaStokBarang(String sisaStokBarang) {
        this.sisaStokBarang = sisaStokBarang;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
