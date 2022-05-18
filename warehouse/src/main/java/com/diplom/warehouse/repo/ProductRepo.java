package com.diplom.warehouse.repo;

import com.diplom.warehouse.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
