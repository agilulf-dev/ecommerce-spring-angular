import {Component, OnInit} from '@angular/core';
import {Product} from "../models/product.model";
import {EcommerceService} from "../services/ecommerce.service";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})

export class ProductFormComponent implements OnInit {

  product: Product;
  productAdded = false;

  constructor(private ecommerceService: EcommerceService) {
    this.product = new Product();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.product.pictureUrl = 'http://placehold.it/200x100';
    this.ecommerceService.addProduct(this.product).subscribe(() => this.productAdded = true);

  }

  reset() {
    this.product = new Product();
    this.productAdded = false;
  }
}
