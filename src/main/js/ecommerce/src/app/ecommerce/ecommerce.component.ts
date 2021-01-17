import {Component, OnInit, ViewChild} from '@angular/core';
import {ProductsComponent} from "./products/products.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {OrdersComponent} from "./orders/orders.component";
import {ProductFormComponent} from "./product-form/product-form.component";

@Component({
  selector: 'app-ecommerce',
  templateUrl: './ecommerce.component.html',
  styleUrls: ['./ecommerce.component.scss']
})

export class EcommerceComponent implements OnInit {
  orderFinished = false;
  isAddingProduct = false;

  @ViewChild('productsComponent')
  productsComponent: ProductsComponent;

  @ViewChild('shoppingCartComponent')
  shoppingCartComponent: ShoppingCartComponent;

  @ViewChild('ordersComponent')
  ordersComponent: OrdersComponent;

  @ViewChild('productFormComponent')
  productFormComponent: ProductFormComponent;

  constructor() {
  }

  ngOnInit(): void {
  }

  reset() {
    this.orderFinished = false;
    this.ordersComponent.paid = false;
    this.isAddingProduct = false;
    this.productsComponent.reset();
    this.shoppingCartComponent.reset();
  }

  finishOrder(orderFinished: boolean) {
    this.orderFinished = orderFinished;
  }

  addProduct() {
    this.isAddingProduct = true;
    this.productFormComponent.reset();
  }
}
