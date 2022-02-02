import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  private productosMessasge = new BehaviorSubject([]);
  productos = this.productosMessasge.asObservable();

  constructor(
    private http: HttpClient
  ) { }

  nextProductos(productos: any): void {
    this.productosMessasge.next(productos);
  }

  cargarProductos(){
    this.http.get(`${environment.URL_PRODUCTOS}/obtener-productos`)
      .subscribe((respuesta: any) => {
        this.nextProductos(respuesta.data)
      })
  }

  eliminarProducto(id: number) {
    const params = new HttpParams()
      .set('product_id', id)
    return this.http.get(`${environment.URL_PRODUCTOS}/eliminar-producto`, {params})
  }

  buscarPorSKU(sku: number){
    const params = new HttpParams()
      .set('sku', sku)
    return this.http.get(`${environment.URL_PRODUCTOS}/obtener-por-sku`, {params})
  }

  guardarProducto(producto: any) {
    return this.http.post(`${environment.URL_PRODUCTOS}/guardar-producto`, producto);
  }

}
