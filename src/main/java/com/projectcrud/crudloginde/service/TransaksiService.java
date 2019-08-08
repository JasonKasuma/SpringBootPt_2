package com.projectcrud.crudloginde.service;

import com.projectcrud.crudloginde.exception.RecordNotFoundException;
import com.projectcrud.crudloginde.model.Perusahaan;
import com.projectcrud.crudloginde.model.Product;
import com.projectcrud.crudloginde.model.Transaksi;
import com.projectcrud.crudloginde.repository.PerusahaanRepository;
import com.projectcrud.crudloginde.repository.ProductRepository;
import com.projectcrud.crudloginde.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TransaksiService {

    @Autowired
    TransaksiRepository transaksiRepository;

    @Autowired
    PerusahaanRepository perusahaanRepository;

    @Autowired
    ProductRepository productRepository;


    public List<Transaksi> getAllTransaction() {
        List<Transaksi> result = (List<Transaksi>) transaksiRepository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Transaksi>();
        }
    }

    public List<Perusahaan> getCompanyList() {
        List<Perusahaan> listCompany = (List<Perusahaan>) perusahaanRepository.findAll();
        return listCompany;
    }

    public List<Product> getProductList() {
        List<Product> listProduct = (List<Product>) productRepository.findAll();
        return listProduct;
    }

    public Transaksi getTransaksiByKodeTransaksi(Long kode_transaksi) throws RecordNotFoundException {
        Optional<Transaksi> transaction = transaksiRepository.findById(kode_transaksi);

        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new RecordNotFoundException("No Transaction record exist for given ID / Transaction's Code");
        }
    }

    public Product getHargaBarangById (Long kode_barang) throws RecordNotFoundException {
        Optional<Product> product = productRepository.findById(kode_barang);

        if (product.isPresent()){
            return product.get();
        } else {
            throw new RecordNotFoundException("No Product");
        }
    }

    public Transaksi createTransaction(Transaksi transaksi) {

        if (transaksi.getKode_transaksi() == null) {
            transaksi = transaksiRepository.save(transaksi);
        }
        return transaksi;


    }

    public void deleteTransactionByKodeTransaction(Long kode_transaksi) throws RecordNotFoundException
    {
        Optional<Transaksi> entity = transaksiRepository.findById(kode_transaksi);

        if (entity.isPresent())
        {
            transaksiRepository.deleteById(kode_transaksi);
        } else {
            throw new RecordNotFoundException("No Transaction Record Exist For Given ID");
        }
    }
}
