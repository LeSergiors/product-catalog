import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {faTrash} from '@fortawesome/free-solid-svg-icons'
import {FormControl, FormGroup,  Validators} from '@angular/forms';
import {ProductosService} from "../../services/productos.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-data-producto',
  templateUrl: './data-producto.component.html',
  styleUrls: ['./data-producto.component.css']
})
export class DataProductoComponent implements OnInit {
  titulo: any = 'Agregar Producto';
  imagenes: any = [];
  urlImagen: any;
  faTrash = faTrash;

  productoForm = new FormGroup({
    id: new FormControl(''),
    sku: new FormControl('', [Validators.required, Validators.min(1000000), Validators.max(99999999)], ),
    name: new FormControl('',[ Validators.required, Validators.minLength(3), Validators.maxLength(50)]),
    brand: new FormControl('', [Validators.required]),
    size: new FormControl(''),
    price: new FormControl('', [Validators.required, Validators.min(1), Validators.max(99999999)])
  });

  constructor(
    public activeModal: NgbActiveModal,
    private productoService: ProductosService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
  }

  agregarImagen() {

    if (!this.urlImagen) {
      this.toastr.error('No se ingresado url');
      return;
    }

    this.imagenes.push({principal: false, url: this.urlImagen});
    this.urlImagen = null;
  }

  eliminarImagen(i: any) {
    this.imagenes.splice(i, 1);
  }

  enviarFormulario() {
    const model = Object.assign({}, this.productoForm.value);
    model.imagenes  = [];
    model.imagenes.push(...this.imagenes);
    console.log(model);

    this.productoService.guardarProducto(model)
      .subscribe((respuesta: any) => {
          this.toastr.success('Producto guardado correctamente.');
          this.productoService.cargarProductos();
          this.activeModal.close();
      });

  }

  marcaPrincipal(imagen: any) {
    if (imagen.principal) {
      imagen.principal = false;
    } else {
      imagen.principal = true;
      this.imagenes.forEach((i: any) => {
        if(i.id !== imagen.id) {
          i.principal = false;
        }
      });
    }
  }

  cargarDatosModal(d: any) {
    this.productoForm.patchValue({
      id: d.id,
      sku: d.sku,
      name: d.name,
      brand: d.brand,
      size: d.size,
      price: d.price
    })
    this.imagenes = [];
    this.imagenes.push(...d.imagenes);
  }

}
