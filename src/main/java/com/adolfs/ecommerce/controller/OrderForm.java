package com.adolfs.ecommerce.controller;

import com.adolfs.ecommerce.dto.OrderProductDto;
import java.util.List;

public class OrderForm {

  private List<OrderProductDto> productOrders;

  public List<OrderProductDto> getProductOrders() {
    return productOrders;
  }

  public void setProductOrders(List<OrderProductDto> productOrders) {
    this.productOrders = productOrders;
  }
}
