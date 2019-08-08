package com.projectcrud.crudloginde.web;

import ch.qos.logback.core.util.FileSize;
import com.projectcrud.crudloginde.exception.RecordNotFoundException;
import com.projectcrud.crudloginde.model.Product;
import com.projectcrud.crudloginde.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

public class ProductMvcController {


    @Autowired
    ProductService service;

    @RequestMapping("/Product")
    public String getAllProduct(Model model) {
        List<Product> list = service.getAllProduct();

        model.addAttribute("products", list);
        return "list-products";
    }

    @RequestMapping(path = {"/Product/edit", "/Product/edit/{kode_barang}"})
    public String editProductByKodeBarang(Model model, @PathVariable("kode_barang") Optional<Long> kode_barang)
            throws RecordNotFoundException {

        if (kode_barang.isPresent()) {
            Product product = service.getProductByKodeBarang(kode_barang.get());

            model.addAttribute("product", product);
            return "list-products";
        } else {
            model.addAttribute("product", new Product());
        }
        return "add-edit-product";
    }

    @RequestMapping(path = "/Product/delete/{kode_barang}")
    public String deleteProductByKodeBarang(Model model, @PathVariable("kode_barang") Long kode_barang)
            throws RecordNotFoundException {
        service.deleteProductByKodeBarang(kode_barang);
        return "redirect:/Product";
    }

    // GET: Show upload form page.


    @RequestMapping(value = "/Product/createProduct", method = RequestMethod.POST)
    public String createOrUpdateProductPost(@ModelAttribute("product") Product product, MultipartFile file) throws IOException {

        service.createOrUpdateProduct(product, file);
        return "redirect:/Product";
        //return this.doUpload(product,file);
    }
}
