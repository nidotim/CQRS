package com.nidotim.estore.ProductService.core.repository;

import com.nidotim.estore.ProductService.core.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

  ProductEntity findByProductId(String productId);

  ProductEntity findByProductIdIsOrTitleIs(String productId, String title);

}
