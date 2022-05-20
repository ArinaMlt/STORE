package com.diplom.warehouse.controller;

import com.diplom.warehouse.repo.MessageRepo;
import com.diplom.warehouse.repo.ProductRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
    private final ObjectMapper objectMapper;
//    private final ObjectWriter objectWriter;

    @Value("${spring.profiles.active}")
    private String profile;


    @Autowired
    public MainController(MessageRepo messageRepo, ProductRepo productRepo, ObjectMapper objectMapper){
        this.messageRepo = messageRepo;
        this.productRepo = productRepo;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public String main(Model model) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", null);
        data.put("messages", messageRepo.findAll());


        String messages = objectMapper.writeValueAsString(messageRepo.findAll());
        model.addAttribute("messages", messages);
//        model.addAttribute("frontendData",data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }

//    @GetMapping("/product")
//    public String product(Model model){
//        model.addAttribute("product", productRepo.findAll());
//        return "product";
//    }

}
