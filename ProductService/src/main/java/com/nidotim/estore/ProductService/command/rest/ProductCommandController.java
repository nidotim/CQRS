package com.nidotim.estore.ProductService.command.rest;

import com.nidotim.estore.ProductService.command.CreateProductCommand;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductCommandController {

  private final Environment env;
  private final CommandGateway commandGateway;

  @PostMapping
  public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
    CreateProductCommand createProductCommand = CreateProductCommand.builder()
        .price(createProductRestModel.getPrice())
        .quantity(createProductRestModel.getQuantity())
        .title(createProductRestModel.getTitle())
        .productId(UUID.randomUUID().toString())
        .build();
    String returnValue;
    try {
      returnValue = commandGateway.sendAndWait(createProductCommand);
    } catch (Exception ex) {
      returnValue = ex.getLocalizedMessage();
    }
    return "HTTP POST Handled " + returnValue;
  }
//
//  @GetMapping
//  public String getProduct() {
//    return "HTTP GET Handled " + env.getProperty("local.server.port");
//  }
//
//  @PutMapping
//  public String updateProduct() {
//    return "HTTP PUT Handled";
//  }
//
//  @DeleteMapping
//  public String deleteProduct() {
//    return "HTTP DELETE Handled";
//  }

}
