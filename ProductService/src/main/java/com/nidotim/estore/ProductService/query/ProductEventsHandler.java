package com.nidotim.estore.ProductService.query;

import com.nidotim.estore.ProductService.core.data.ProductEntity;
import com.nidotim.estore.ProductService.core.events.ProductCreatedEvent;
import com.nidotim.estore.ProductService.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductEventsHandler {

  private final ProductRepository productRepository;

  @EventHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    log.debug("event handler entered ...");
    ProductEntity productEntity = new ProductEntity();
    BeanUtils.copyProperties(productCreatedEvent, productEntity);
    productRepository.save(productEntity);
  }
}
