package com.diplom.warehouse.controller;

import com.diplom.warehouse.Service.ProductService;
import com.diplom.warehouse.domain.Product;
import com.diplom.warehouse.repo.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
//    private final ProductService productService;
    private final ProductRepo productRepo;

    @Autowired
    public ProductController(ProductRepo productRepo) {
//        this.productService = productService;
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<Product> list(){
        return productRepo.findAll();
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Product product) {
        return product;
    }

    @PostMapping
    public Product create(@RequestBody Product product){
//        return productService.create(product);
        return productRepo.save(product);
    }
    @PutMapping("{id}")
    public Product update(
            @PathVariable("id") Product productFromDb,
            @RequestBody Product product){
        BeanUtils.copyProperties(product, productFromDb, "id");

        return productRepo.save(product);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Product product){
        productRepo.delete(product);
    }
}
