import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CatalogoComponent } from './components/catalogo/catalogo.component';
import { CatalogoGrillaComponent } from './components/catalogo-grilla/catalogo-grilla.component';
import { HeaderComponent } from './components/header/header.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ToastrModule} from "ngx-toastr";
import { DataProductoComponent } from './components/data-producto/data-producto.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpConfigInterceptor} from "./httpconfig.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    CatalogoComponent,
    CatalogoGrillaComponent,
    HeaderComponent,
    DataProductoComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        HttpClientModule,
        FontAwesomeModule,
        BrowserAnimationsModule,
        ToastrModule.forRoot(),
        FormsModule,
        ReactiveFormsModule
    ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
