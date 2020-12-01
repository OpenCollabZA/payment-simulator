import {Component, OnInit} from '@angular/core';
import {CreditCardInfo} from "../shared/model/credit-card.info";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PaygateComponent} from "../paygate/paygate.component";
import {PaygateService} from '../shared/service/paygate.service';

@Component({
  selector: 'app-creditcard',
  templateUrl: './creditcard.component.html',
  styleUrls: ['./creditcard.component.css']
})
export class CreditcardComponent implements OnInit {

  expiryYears: number[];
  creditCardForm: FormGroup;
  constructor(private formBuilder:FormBuilder,
              private paygateService: PaygateService,
              private paygateComponent: PaygateComponent) {


    this.expiryYears = [18,19,20,21,22,23,24,25];
    this.creditCardForm = this.formBuilder.group({
      type: [null, Validators.required],
      cardholder: [null, Validators.required],
      cardnumber: [null, Validators.required],
      expiremonth: [null, Validators.required],
      expireyear: [null, Validators.required],
      cvv: [null, Validators.required],
      payRequestId: [null, Validators.required],
    })
  }

  ngOnInit() {
    // Start with some mock data to speed up the demo
    this.creditCardForm.patchValue({
      type : "VISA",
      cardholder : "Mr Payment Simulator",
      cardnumber : "40000000000001",
      cvv : 123,
      expiremonth : 5,
      expireyear : 20,
      payRequestId : this.paygateComponent.paygateId
    })
  }

  declineTransaction(){
    const formValue = this.creditCardForm.value;
    this.paygateService.declineTransaction(formValue.payRequestId).subscribe((completeResponse) => {
      this.paygateService.performRedirect(completeResponse);
    });
  }

  pay($event: Event){
    $event.preventDefault();
    $event.stopImmediatePropagation();
    const formValue = this.creditCardForm.value;
    this.paygateService.completeTransaction(formValue.payRequestId).subscribe((completeResponse) => {
      this.paygateService.performRedirect(completeResponse);

    });
    return false;
  }
}
