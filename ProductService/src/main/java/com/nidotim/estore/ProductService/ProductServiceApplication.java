package com.nidotim.estore.ProductService;

import com.nidotim.estore.ProductService.command.interceptor.CreateProductCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductServiceApplication.class, args);
  }

  @Autowired
  public void registerCreateProductCommandInterceptor(ApplicationContext context,
      CommandBus commandBus) {
    commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));

  }

}
