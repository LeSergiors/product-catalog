import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogoGrillaComponent } from './catalogo-grilla.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {ToastrModule} from "ngx-toastr";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

describe('CatalogoGrillaComponent', () => {
  let component: CatalogoGrillaComponent;
  let fixture: ComponentFixture<CatalogoGrillaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,
        ToastrModule.forRoot(),
        NgbModule],
      declarations: [ CatalogoGrillaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CatalogoGrillaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
