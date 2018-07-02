import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { CreditcardComponent } from './creditcard/creditcard.component';
import { EftComponent } from './eft/eft.component';
import {FormsModule} from "@angular/forms";
import {BSControlStatusDirective} from "./shared/directive/bscontrol-status.directive";

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    CreditcardComponent,
    EftComponent,
    BSControlStatusDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }