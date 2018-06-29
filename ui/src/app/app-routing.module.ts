import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LandingComponent} from "./landing/landing.component";
import {CreditcardComponent} from "./creditcard/creditcard.component";
import {EftComponent} from "./eft/eft.component";

const routes: Routes = [
  {
    path: 'creditcard',
    component: CreditcardComponent,
  },
  {
    path: 'eft',
    component: EftComponent,
  },
  {
    path: '',
    component: LandingComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
