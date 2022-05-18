package com.diplom.warehouse.controller;

import com.diplom.warehouse.repo.MessageRepo;
import com.diplom.warehouse.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
//@RequestMapping("/")
public class MainController {
    private final MessageRepo messageRepo;
    private final ProductRepo productRepo;

    @Value("${spring.profiles.active}")
    private String profile;


    @Autowired
    public MainController(MessageRepo messageRepo, ProductRepo productRepo){
        this.messageRepo = messageRepo;
        this.productRepo = productRepo;
    }

    @GetMapping("/")
    public String main(Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", null);
        data.put("messages", messageRepo.findAll());


        model.addAttribute("frontendData",data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }

    @GetMapping("/product")
    public String product(Model model){
        model.addAttribute("product", productRepo.findAll());
        return "product";
    }

}
