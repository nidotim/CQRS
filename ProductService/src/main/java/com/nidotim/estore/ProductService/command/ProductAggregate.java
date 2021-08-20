package com.nidotim.estore.ProductService.command;

import com.nidotim.estore.ProductService.core.events.ProductCreatedEvent;
import java.math.BigDecimal;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
@Slf4j
public class ProductAggregate {

  @AggregateIdentifier
  private String productId;
  private String title;
  private BigDecimal price;
  private Integer quantity;

  @CommandHandler
  public ProductAggregate(CreateProductCommand createProductCommand) {
    log.debug("productAggregate entered ... ");
    // Validate Create Product Command
    if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Price cannot be less than or equal to 0");
    }
    if (StringUtils.isEmpty(createProductCommand.getTitle())) {
      throw new IllegalArgumentException("Title cannot be empty");
    }

    ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
    BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

    AggregateLifecycle.apply(productCreatedEvent);
  }

  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent) {
    this.productId = productCreatedEvent.getProductId();
    this.price = productCreatedEvent.getPrice();
    this.title = productCreatedEvent.getTitle();
    this.quantity = productCreatedEvent.getQuantity();
  }

}
