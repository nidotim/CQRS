package com.nidotim.estore.ProductService.command.interceptor;

import com.nidotim.estore.ProductService.command.CreateProductCommand;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements
    MessageDispatchInterceptor<CommandMessage<?>> {

  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
      List<? extends CommandMessage<?>> messages) {
    return (index, command) -> {
      log.debug("Intercepted command: " + command.getPayloadType());
      if (CreateProductCommand.class.equals(command.getPayloadType())) {
        CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

        log.debug("CreateProductCommand Intercepted command: " + command.getPayloadType());
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
          throw new IllegalArgumentException("Price cannot be less than or equal to 0");
        }
        if (StringUtils.isEmpty(createProductCommand.getTitle())) {
          throw new IllegalArgumentException("Title cannot be empty");
        }
      }
      return command;
    };
  }
}
