package com.adolfs.ecommerce.service.internal;

import com.adolfs.ecommerce.model.Order;
import com.adolfs.ecommerce.repository.OrderRepository;
import com.adolfs.ecommerce.service.OrderService;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public @NotNull Iterable<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Order create(@NotNull(message = "The order cannot be null.") @Valid Order order) {
    order.setDateCreated(LocalDate.now());
    return this.orderRepository.save(order);
  }

  @Override
  public void update(@NotNull(message = "The order cannot be null.") @Valid Order order) {
    orderRepository.save(order);
  }
}
