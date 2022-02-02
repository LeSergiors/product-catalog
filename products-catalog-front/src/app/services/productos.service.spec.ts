import {getTestBed, TestBed} from '@angular/core/testing';

import { ProductosService } from './productos.service';
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {ToastrModule} from "ngx-toastr";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

describe('ProductosService', () => {
  let service: ProductosService;
  let httpMock:  HttpTestingController

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,
        ToastrModule.forRoot(),
        NgbModule],
      providers: [ProductosService]
    });
    service = TestBed.inject(ProductosService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an Observable<any>', () => {

  });


});
