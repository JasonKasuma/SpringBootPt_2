package com.projectcrud.crudloginde.web;

import com.projectcrud.crudloginde.exception.RecordNotFoundException;
import com.projectcrud.crudloginde.model.Perusahaan;
import com.projectcrud.crudloginde.service.PerusahaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller

public class PerusahaanMvcController {

    @Autowired
    PerusahaanService service;

    @RequestMapping("/Company")
    public String getAllPerusahaan(Model model)
    {
        List<Perusahaan> list = service.getAllPerusahaan();

        model.addAttribute("companies", list);
        return "list-company";
    }

    @RequestMapping(path = {"/Company/edit", "/Company/edit/{kode_perusahaan}"})
    public String editCompanyByKodePerusahaan(Model model, @PathVariable("kode_perusahaan") Optional<Long> kode_perusahaan)
            throws RecordNotFoundException
    {
        if (kode_perusahaan.isPresent()){
            Perusahaan perusahaan = service.getCompanyByKodePerusahaan(kode_perusahaan.get());
            model.addAttribute("company", perusahaan);
        } else {
            model.addAttribute("company", new Perusahaan());
        }
        return "add-edit-company";
    }

    @RequestMapping(path = "/Company/delete/{kode_perusahaan}")
    public String deleteCompanyByKodePerusahaan(Model model, @PathVariable("kode_perusahaan") Long kode_perusahaan)
            throws RecordNotFoundException
    {
        service.deleteCompanyByKodePerusahaan(kode_perusahaan);
        return "redirect:/Company";
    }

    @RequestMapping(path = "/Company/createCompany", method = RequestMethod.POST)
    public String createOrUpdateProduct(Perusahaan entity)
    {


        service.createOrUpdatePerusahaan(entity);
        return "redirect:/Company";
    }
}
