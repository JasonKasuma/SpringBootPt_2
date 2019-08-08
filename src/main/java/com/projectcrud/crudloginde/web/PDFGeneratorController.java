package com.projectcrud.crudloginde.web;

import com.projectcrud.crudloginde.util.PDFGeneratorUtil;
import com.projectcrud.crudloginde.model.Transaksi;
import com.projectcrud.crudloginde.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class PDFGeneratorController {

    @Autowired
    PDFGeneratorUtil pdfGeneratorUtil;

    @Autowired
    TransaksiService transaksiService;

    @RequestMapping("/generatePDF")
    public String getPDFView(Model model) throws Exception {
        List<Transaksi> transaksis = transaksiService.getAllTransaction();
        Map<Object, Object> data = new HashMap<>();
        data.put("transaksi", transaksis);
        pdfGeneratorUtil.createPdf("greeting", data);
        model.addAttribute("message", "PDF Downloaded successfully..");
        return "test";
    }

    private List<Transaksi> getTransaksi() {
        List<Transaksi> transaksis = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            transaksis.add(new Transaksi());
        }
        return transaksis;
    }
}
