import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LandingComponent} from "./landing/landing.component";
import {CreditcardComponent} from "./creditcard/creditcard.component";
import {EftComponent} from "./eft/eft.component";
import {PaygateComponent} from "./paygate/paygate.component";

const routes: Routes = [
  {
    path: ':paygateId',
    component: PaygateComponent,
    children: [
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
        component: LandingComponent
      }
    ],
  },
    {
      path: '',
      pathMatch : 'full',
      redirectTo: '1234567890' // This should never happen
    },
  // { path: '**', redirectTo: '123'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
