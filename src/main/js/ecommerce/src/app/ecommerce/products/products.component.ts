import {Component, OnInit} from '@angular/core';
import {ProductOrder} from "../models/product.order.model";
import {Product} from "../models/product.model";
import {Subscription} from 'rxjs';
import {ProductOrders} from "../models/product.orders.model";
import {EcommerceService} from "../services/ecommerce.service";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  productOrders: ProductOrder[] = [];
  products: Product[] = [];
  selectedProductOrder: ProductOrder;
  private shoppingCartOrders: ProductOrders;
  sub: Subscription;
  productSelected = false;

  constructor(private ecommerceService: EcommerceService) {
  }

  ngOnInit(): void {
    this.productOrders = [];
    this.loadProducts();
    this.loadOrders();
  }

  isProductSelected(product: Product): boolean {
    return this.getProductIndex(product) > -1;
  }

  addToCart(order: ProductOrder) {
    this.ecommerceService.SelectedProductOrder = order;
    this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
    this.productSelected = true;
  }

  removeFromCart(productOrder: ProductOrder) {
    let index = this.getProductIndex(productOrder.product);
    if (index > -1) {
      this.shoppingCartOrders.productOrders.splice(
        this.getProductIndex(productOrder.product), 1);
    }
    this.ecommerceService.ProductOrders = this.shoppingCartOrders;
    this.shoppingCartOrders = this.ecommerceService.ProductOrders;
    this.productSelected = false;
  }

  private loadProducts() {
    this.ecommerceService.getAllProducts()
    .subscribe(
      (products: any[]) => {
        this.products = products;
        this.products.forEach(product => {
          this.productOrders.push(new ProductOrder(product, 0));
        })
      },
      (error) => console.log(error)
    );
  }

  private loadOrders() {
    this.sub = this.ecommerceService.OrdersChanged
    .subscribe(() => {
      this.shoppingCartOrders = this.ecommerceService.ProductOrders;
    });
  }

  private getProductIndex(product: Product): number {
    return this.ecommerceService.ProductOrders.productOrders.findIndex(
      value => value.product === product);
  }

  reset() {
    this.productOrders = [];
    this.loadProducts();
    this.ecommerceService.ProductOrders.productOrders = [];
    this.loadOrders();
    this.productSelected = false;
  }
}
