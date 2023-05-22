package com.app.controllers;

import com.app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/catalog")
    public String getCatalog(Model model) {
        model.addAttribute("items", adminService.getItems());
        return "catalog";
    }
}
