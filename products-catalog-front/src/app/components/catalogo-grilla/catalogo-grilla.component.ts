import { Component, OnInit } from '@angular/core';
import {ProductosService} from "../../services/productos.service";
import {faTrash, faEdit} from '@fortawesome/free-solid-svg-icons'
import {ToastrService} from "ngx-toastr";
import {Subject, takeUntil} from "rxjs";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DataProductoComponent} from "../data-producto/data-producto.component";

@Component({
  selector: 'app-catalogo-grilla',
  templateUrl: './catalogo-grilla.component.html',
  styleUrls: ['./catalogo-grilla.component.css']
})
export class CatalogoGrillaComponent implements OnInit {

  private unsubscribe$ = new Subject<void>();
  faTrash = faTrash;
  faEdit = faEdit;

  productos: any;

  constructor(
    private productosService: ProductosService,
    private toastr: ToastrService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.productosService.productos.pipe(takeUntil(this.unsubscribe$)).subscribe(productos => this.productos = productos);
  }

  eliminarProducto(id: number) {
    this.productosService.eliminarProducto(id)
      .subscribe((respuesta: any) => {
        this.toastr.success('Elemento eliminado correctamente.')
        this.productosService.cargarProductos();
      })
  }

  abrirAgregarProducto() {
    const modalRef = this.modalService.open(DataProductoComponent, {size: "lg"});
    modalRef.componentInstance.titulo = 'Agregar Producto.';
  }

  abrirEditarProducto(d: any){
    const modalRef = this.modalService.open(DataProductoComponent, {size: "lg"});
    modalRef.componentInstance.cargarDatosModal(d);
  }

}
