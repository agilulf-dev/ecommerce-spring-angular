package com.backend.ecommerce.service.internal;

import com.backend.ecommerce.model.OrderProduct;
import com.backend.ecommerce.repository.OrderProductRepository;
import com.backend.ecommerce.service.OrderProductService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

  private final OrderProductRepository orderProductRepository;

  public OrderProductServiceImpl(
      OrderProductRepository orderProductRepository) {
    this.orderProductRepository = orderProductRepository;
  }

  @Override
  public OrderProduct create(
      @NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct) {
    return this.orderProductRepository.save(orderProduct);
  }
}
