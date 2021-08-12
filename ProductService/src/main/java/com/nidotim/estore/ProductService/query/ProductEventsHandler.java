package com.nidotim.estore.ProductService.query;

import com.nidotim.estore.ProductService.core.data.ProductEntity;
import com.nidotim.estore.ProductService.core.events.ProductCreatedEvent;
import com.nidotim.estore.ProductService.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventsHandler {

  private final ProductRepository productRepository;

  @EventHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    ProductEntity productEntity = new ProductEntity();
    BeanUtils.copyProperties(productCreatedEvent, productEntity);
    productRepository.save(productEntity);
  }
}
