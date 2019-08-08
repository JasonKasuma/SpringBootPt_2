package com.projectcrud.crudloginde.service;

import com.projectcrud.crudloginde.exception.RecordNotFoundException;
import com.projectcrud.crudloginde.model.Product;
import com.projectcrud.crudloginde.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private static String UPLOADED_FOLDER = "C:\\Users\\Ricky Suhanry\\Desktop\\Desktop Library File\\Work documentation\\PT Ifabula Digital Kreasi\\Testing project library\\Semua punya Hansen udah kelar\\Crud Total\\src\\main\\resources\\static\\UploadFile\\";

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProduct()
    {
        List<Product> result = (List<Product>) repository.findAll();

        if (result.size() > 0){
            return result;
        } else {
            return new ArrayList<Product>();
        }
    }

    public Product getProductByKodeBarang(Long kode_barang) throws RecordNotFoundException
    {
        Optional<Product> product = repository.findById(kode_barang);

        if (product.isPresent()){
            return product.get();
        } else {
            throw new RecordNotFoundException("No Product record exist for given ID / Product's Code");
        }
    }

    public Product createOrUpdateProduct(@ModelAttribute("product") Product product, MultipartFile file) throws IOException
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //product.setFileName(fileName);
        String testName = StringUtils.getFilename(fileName);
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + testName);
        Files.write(path, bytes);
        System.out.println("=========== " + testName);
        System.out.println("===============" + UPLOADED_FOLDER + testName);


        if (product.getKode_barang() == null)
        {
            product.setFileName(testName);
            product = repository.save(product);

            return product;
        } else {
            Optional<Product> entity = repository.findById(product.getKode_barang());

            if (entity.isPresent())
            {
                Product newProduct = entity.get();
                newProduct.setNamaBarang(product.getNamaBarang());
                newProduct.setHargaBarang((product.getHargaBarang()));
                newProduct.setStokBarang(product.getStokBarang());
                newProduct.setFileName(testName);

                newProduct =repository.save(newProduct);

                return newProduct;
            } else {
                product = repository.save(product);

                return product;
            }
        }
    }

    public void deleteProductByKodeBarang(Long kode_barang) throws RecordNotFoundException
    {
        Optional<Product> entity = repository.findById(kode_barang);

        if (entity.isPresent())
        {
            repository.deleteById(kode_barang);
        } else {
            throw new RecordNotFoundException("No Product Record Exist For Given ID");
        }
    }
}
