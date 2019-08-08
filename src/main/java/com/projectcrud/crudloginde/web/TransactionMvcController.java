package com.projectcrud.crudloginde.web;

import com.projectcrud.crudloginde.exception.RecordNotFoundException;
import com.projectcrud.crudloginde.model.Perusahaan;
import com.projectcrud.crudloginde.model.Product;
import com.projectcrud.crudloginde.model.Transaksi;
import com.projectcrud.crudloginde.repository.PerusahaanRepository;
import com.projectcrud.crudloginde.repository.ProductRepository;
import com.projectcrud.crudloginde.repository.TransaksiRepository;
import com.projectcrud.crudloginde.service.ProductService;
import com.projectcrud.crudloginde.service.TransaksiService;
import com.projectcrud.crudloginde.web.dto.ProductDto;
import javafx.scene.control.DatePicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.expression.Dates;
import sun.util.calendar.BaseCalendar;
import sun.util.resources.cldr.aa.CalendarData_aa_ER;

import javax.print.attribute.DateTimeSyntax;
import javax.print.attribute.standard.DateTimeAtCompleted;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.SystemOut;
import static javax.print.attribute.standard.MediaPrintableArea.MM;

@Controller
public class TransactionMvcController {
    @Autowired
    TransaksiService transaksiService;

    @Autowired
    TransaksiRepository transaksiRepository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/Transaction")
    public String getAllTransaction(Model model){
        List<Transaksi> list = transaksiService.getAllTransaction();

        model.addAttribute("transactions", list);
        return "list-transaction";
    }

    @RequestMapping(path = {"/Transaction/anoherAdd", "/Transaction/anotherAdd/{kode_transaksi}"})
    public String anotherAddTransactionByKodeTransaksi(Model model, @PathVariable("kode_transaksi") Optional<Long> kode_transaksi)
            throws RecordNotFoundException
    {

        List<Perusahaan> listCompany = (List<Perusahaan>) transaksiService.getCompanyList();
        List<Product> listProduct = (List<Product>) transaksiService.getProductList();
        model.addAttribute("listCompany", listCompany);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("transaction", new Transaksi());

        return "add-transaction";
    }

    @RequestMapping(path = "/Transaction/delete/{kode_transaksi}")
    public String deleteTransactionByKodeTransaksi(Model model, @PathVariable("kode_transaksi") Long kode_transaksi)
            throws RecordNotFoundException
    {
        transaksiService.deleteTransactionByKodeTransaction(kode_transaksi);
        return "redirect:/Transaction";
    }

    @RequestMapping(path = "/Transaction/createTransaction", method = RequestMethod.POST)
    public String createTransaction(Transaksi entity)
    {
        Optional<Product> product = productRepository.findById(entity.getProduct().getKode_barang());
        System.out.println(product.get().getHargaBarang());

        Transaksi saveTransaksi = new Transaksi();
        Perusahaan perusahaan = new Perusahaan();
        Product product1 = new Product();

        perusahaan.setKode_perusahaan(entity.getPerusahaan().getKode_perusahaan());
        product1.setKode_barang(entity.getProduct().getKode_barang());

        saveTransaksi.setPerusahaan(perusahaan);
        saveTransaksi.setProduct(product1);
        saveTransaksi.setOrderQuantity(entity.getOrderQuantity());
        saveTransaksi.setTanggalTransaksi(new Date());
        saveTransaksi.setHargaTransaksi(product.get().getHargaBarang());
        saveTransaksi.setSisaStokBarang(product.get().getStokBarang());

        transaksiRepository.save(saveTransaksi);
        return "redirect:/Transaction";
    }


}




/*
        Product product = new Product();
        Transaksi saveTransaksi = new Transaksi();
        if (entity.isPresent()){


            saveTransaksi.setHargaTransaksi(entity.getProduct().getHargaBarang());
            saveTransaksi.setSisaStokBarang(entity.getProduct().getStokBarang());
        }
        transaksiRepository.save(saveTransaksi);
        return "redirect:/Transaction";
        */

        /*
        Perusahaan perusahaan = new Perusahaan();

        productRepository.findById(entity.getProduct().getKode_barang());

        product.setHargaBarang(entity.getProduct().getHargaBarang());
        product.setNamaBarang(entity.getProduct().getNamaBarang());


        perusahaan.setKode_perusahaan(entity.getPerusahaan().getKode_perusahaan());

saveTransaksi.setTanggalTransaksi(entity.getTanggalTransaksi());

        Transaksi saveTransaksi = new Transaksi();

        saveTransaksi.setOrderQuantity(entity.getOrderQuantity());
        saveTransaksi.setProduct(product);

        saveTransaksi.setPerusahaan(perusahaan);
         */


