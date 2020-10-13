import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute, Params} from "@angular/router";
import {PaygateService} from "../shared/service/paygate.service";
import {PayWebRequestInfo} from "../shared/model/pay-web-request.info";

@Component({
  selector: 'app-paygate',
  templateUrl: './paygate.component.html',
  styleUrls: ['./paygate.component.css']
})
export class PaygateComponent  implements OnInit, OnDestroy {

  transactionDetails: PayWebRequestInfo;

  paramsSubscription: Subscription;

  paygateId: string;

  constructor(private route: ActivatedRoute,
              private paygateService: PaygateService) {
    this.paramsSubscription = this.route.params.subscribe((params: Params)=>{
      this.paygateId = params.paygateId;
      this.loadTransactionDetails(params.paygateId);
    });
  }

  ngOnInit() {

  }

  private loadTransactionDetails(paygateId: string){
    console.log("loading " + paygateId);
    this.paygateService.getTransactionDetail(this.paygateId).subscribe((transactionDetail)=> {
      this.transactionDetails = transactionDetail;
    });
  }

  ngOnDestroy(): void {
    if(this.paramsSubscription !== null){
      this.paramsSubscription.unsubscribe();
    }
  }

  userCancel(){
    this.paygateService.cancelTransaction(this.paygateId).subscribe((payWebCompleteResponse)=> {
      this.paygateService.performRedirect(payWebCompleteResponse);
    })
  }

}
