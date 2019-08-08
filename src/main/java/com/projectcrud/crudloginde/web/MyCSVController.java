package com.projectcrud.crudloginde.web;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.projectcrud.crudloginde.model.User;
import com.projectcrud.crudloginde.repository.ProductRepository;
import com.projectcrud.crudloginde.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MyCSVController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    public MyCSVController(ProductService productService) {
        this.productService = productService;
    }

    // create a link to download csv file
    @GetMapping("/export-product")
    public void exportCSV(HttpServletResponse response) throws Exception {

        // set file name and content type
        String filename = "product.csv";

        // declare data type
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        // create a list which contains Data
        List<String[]> data = new ArrayList<String[]>();

        // create a csv writer -
        StatefulBeanToCsv<User> writer = new StatefulBeanToCsvBuilder<User>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();
    }
}
