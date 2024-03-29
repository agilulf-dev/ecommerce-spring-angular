import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {ProductOrder} from "../models/product.order.model";
import {ProductOrders} from "../models/product.orders.model";
import {Product} from "../models/product.model";

@Injectable()
export class EcommerceService {

  private productsUrl = "/api/products";
  private ordersUrl = "/api/orders";

  private productOrder: ProductOrder;
  private orders: ProductOrders = new ProductOrders();

  private productOrderSubject = new Subject();
  private ordersSubject = new Subject();
  private totalSubject = new Subject();

  private total: number;

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  constructor(private http: HttpClient) {
  }

  getAllProducts() {
    return this.http.get(this.productsUrl);
  }

  saveOrder(order: ProductOrders) {
    return this.http.post(this.ordersUrl, order);
  }

  addProduct(product: Product) {
    return this.http.post<Product>(this.productsUrl, product);
  }

  set SelectedProductOrder(value: ProductOrder) {
    this.productOrder = value;
    this.productOrderSubject.next(value);
  }

  get SelectedProductOrder() {
    return this.productOrder;
  }

  set ProductOrders(value: ProductOrders) {
    this.orders = value;
    this.ordersSubject.next(value);
  }

  get ProductOrders() {
    return this.orders;
  }

  get Total() {
    return this.total;
  }

  set Total(value: number) {
    this.total = value;
    this.totalSubject.next(value);
  }
}
