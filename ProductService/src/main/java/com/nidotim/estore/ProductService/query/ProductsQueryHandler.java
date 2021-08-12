package com.nidotim.estore.ProductService.query;

import com.nidotim.estore.ProductService.core.data.ProductEntity;
import com.nidotim.estore.ProductService.core.repository.ProductRepository;
import com.nidotim.estore.ProductService.query.rest.ProductRestModel;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsQueryHandler {

  private final ProductRepository productRepository;


  @QueryHandler
  public List<ProductRestModel> findProducts(FindProductsQuery findProductsQuery) {
    List<ProductRestModel> productRestModels = new ArrayList<>();
    List<ProductEntity> productEntities = productRepository.findAll();

    for (ProductEntity productEntity : productEntities) {
      ProductRestModel productRestModel = new ProductRestModel();
      BeanUtils.copyProperties(productEntity, productRestModel);
      productRestModels.add(productRestModel);
    }
    return productRestModels;
  }

}
