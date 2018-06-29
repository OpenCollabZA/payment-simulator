import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { CreditcardComponent } from './creditcard/creditcard.component';
import { EftComponent } from './eft/eft.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    CreditcardComponent,
    EftComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
