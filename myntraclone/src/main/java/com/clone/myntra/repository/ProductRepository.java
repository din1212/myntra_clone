package com.clone.myntra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.clone.myntra.repository.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> ,
JpaSpecificationExecutor<Product>{

}
