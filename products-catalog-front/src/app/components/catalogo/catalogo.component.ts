import { Component, OnInit } from '@angular/core';
import {ProductosService} from "../../services/productos.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-catalogo',
  templateUrl: './catalogo.component.html',
  styleUrls: ['./catalogo.component.css']
})
export class CatalogoComponent implements OnInit {

  sku: any = null;

  constructor(
    private productosService: ProductosService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.productosService.cargarProductos();
  }

  buscarPorSku() {
    console.log(this.sku);
    if (!this.sku) {
      this.toastr.error('No se ha ingresado SKU');
      return;
    }
    this.productosService.buscarPorSKU(this.sku)
      .subscribe((respuesta: any) => {
        const productos = [];
        productos.push(respuesta.data);
        this.productosService.nextProductos(productos);
      });
  }

}
