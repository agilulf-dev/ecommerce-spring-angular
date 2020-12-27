package com.backend.ecommerce;

import static com.backend.ecommerce.model.OrderStatus.PAID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

import com.backend.ecommerce.controller.OrderController;
import com.backend.ecommerce.controller.OrderForm;
import com.backend.ecommerce.controller.ProductController;
import com.backend.ecommerce.dto.OrderProductDto;
import com.backend.ecommerce.model.Order;
import com.backend.ecommerce.model.Product;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
    EcommerceApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EcommerceApplicationTest {

  private static final String LOCALHOST_PREFIX = "http://localhost:";
  private static final String PRODUCTS_API_ADDRESS = "/api/products";
  private static final String ORDERS_API_ADDRESS = "/api/orders";

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  @Autowired
  private ProductController productController;

  @Autowired
  private OrderController orderController;

  @Test
  public void contextLoads() {
    Assertions.assertThat(productController).isNotNull();
    Assertions.assertThat(orderController).isNotNull();
  }

  @Test
  public void givenGetProductsApiCall_whenProductListRetrieved_thenSizeMatchAndListContainsProductNames() {
    ResponseEntity<Iterable<Product>> responseEntity = restTemplate
        .exchange(LOCALHOST_PREFIX + port + PRODUCTS_API_ADDRESS, HttpMethod.GET, null,
            new ParameterizedTypeReference<Iterable<Product>>() {
            });
    Iterable<Product> products = responseEntity.getBody();
    Assertions
        .assertThat(products)
        .hasSize(7);

    assertThat(products, hasItem(hasProperty("name", is("TV Set"))));
    assertThat(products, hasItem(hasProperty("name", is("Game Console"))));
    assertThat(products, hasItem(hasProperty("name", is("Sofa"))));
    assertThat(products, hasItem(hasProperty("name", is("Icecream"))));
    assertThat(products, hasItem(hasProperty("name", is("Beer"))));
    assertThat(products, hasItem(hasProperty("name", is("Phone"))));
    assertThat(products, hasItem(hasProperty("name", is("Watch"))));
  }

  @Test
  public void givenGetOrdersApiCall_whenProductListRetrieved_thenSizeMatchAndListContainsProductNames() {
    ResponseEntity<Iterable<Order>> responseEntity = restTemplate
        .exchange(LOCALHOST_PREFIX + port + ORDERS_API_ADDRESS, HttpMethod.GET, null,
            new ParameterizedTypeReference<Iterable<Order>>() {
            });

    Iterable<Order> orders = responseEntity.getBody();
    Assertions.assertThat(orders).hasSize(0);
  }

  @Test
  public void givenPostOrder_whenBodyRequestMatcherJson_thenResponseContainsEqualObjectProperties() {
    OrderForm orderForm = new OrderForm();
    OrderProductDto productDto = new OrderProductDto();
    productDto.setProduct(new Product(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
    productDto.setQuantity(2);
    orderForm.setProductOrders(Collections.singletonList(productDto));

    final ResponseEntity<Order> postResponse = restTemplate
        .postForEntity(LOCALHOST_PREFIX + port + ORDERS_API_ADDRESS, orderForm, Order.class);
    Order order = postResponse.getBody();

    Assertions.assertThat(postResponse.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
    assertThat(order, hasProperty("status", is(PAID.name())));
    assertThat(order.getOrderProducts(), hasItem(hasProperty("quantity", is(2))));
  }
}
