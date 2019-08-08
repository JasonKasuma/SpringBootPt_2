package com.projectcrud.crudloginde.web;

import com.projectcrud.crudloginde.model.Perusahaan;
import com.projectcrud.crudloginde.model.Product;
import com.projectcrud.crudloginde.repository.PerusahaanRepository;
import com.projectcrud.crudloginde.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/MainMenu")
public class MainMenu {

    @RequestMapping
    public String getMainMenu (Model model) {
        return "index";
    }
}
