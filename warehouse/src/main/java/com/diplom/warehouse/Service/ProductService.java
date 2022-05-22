package com.diplom.warehouse.Service;

import com.diplom.warehouse.domain.Product;
import com.diplom.warehouse.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product create(Product product){
        productRepo.save(product);

        return product;
    }
}
