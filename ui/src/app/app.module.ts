import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { CreditcardComponent } from './creditcard/creditcard.component';
import { EftComponent } from './eft/eft.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BSControlStatusDirective} from "./shared/directive/bscontrol-status.directive";
import { PaygateComponent } from './paygate/paygate.component';
import {HttpClientModule} from "@angular/common/http";

import '@clr/icons';
import '@clr/icons/shapes/all-shapes';
import { ClarityModule } from '@clr/angular';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    CreditcardComponent,
    EftComponent,
    BSControlStatusDirective,
    PaygateComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    ClarityModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
