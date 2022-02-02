import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataProductoComponent } from './data-producto.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {ToastrModule} from "ngx-toastr";
import {NgbActiveModal, NgbModule} from "@ng-bootstrap/ng-bootstrap";

describe('DataProductoComponent', () => {
  let component: DataProductoComponent;
  let fixture: ComponentFixture<DataProductoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,
        ToastrModule.forRoot(),],
      providers: [NgbModule, NgbActiveModal],
      declarations: [ DataProductoComponent]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DataProductoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
