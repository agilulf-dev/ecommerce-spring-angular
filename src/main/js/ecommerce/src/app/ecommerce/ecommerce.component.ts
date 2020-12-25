import {Component, OnInit, ViewChild} from '@angular/core';
import {ProductsComponent} from "./products/products.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {OrdersComponent} from "./orders/orders.component";

@Component({
  selector: 'app-ecommerce',
  templateUrl: './ecommerce.component.html',
  styleUrls: ['./ecommerce.component.scss']
})

export class EcommerceComponent implements OnInit {
  collapsed = true;
  orderFinished = false;

  @ViewChild('productsC')
  productsC: ProductsComponent;

  @ViewChild('shoppingCartC')
  shoppingCartC: ShoppingCartComponent;

  @ViewChild('ordersC')
  ordersC: OrdersComponent;

  constructor() {
  }

  ngOnInit(): void {
  }

  toggleCollapsed() {
    this.collapsed = !this.collapsed;
  }

  reset() {
    this.orderFinished = false;
    this.ordersC.paid = false;
    this.productsC.reset();
    this.shoppingCartC.reset();
  }

  finishOrder(orderFinished: boolean) {
    this.orderFinished = orderFinished;
  }
}
