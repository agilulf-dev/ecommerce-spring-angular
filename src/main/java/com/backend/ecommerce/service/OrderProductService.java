package com.backend.ecommerce.service;

import com.backend.ecommerce.model.OrderProduct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderProductService {

  OrderProduct create(
      @NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);

}
