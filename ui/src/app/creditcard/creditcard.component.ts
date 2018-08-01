import {Component, OnInit} from '@angular/core';
import {CreditCardInfo} from "../shared/model/credit-card.info";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-creditcard',
  templateUrl: './creditcard.component.html',
  styleUrls: ['./creditcard.component.css']
})
export class CreditcardComponent implements OnInit {

  expiryYears: number[];
  creditCardForm: FormGroup;

  constructor(private formBuilder:FormBuilder) {
    this.expiryYears = [18,19,20,21,22,23,24,25];
    this.creditCardForm = this.formBuilder.group({
      type: [null, Validators.required],
      cardholder: [null, Validators.required],
      cardnumber: [null, Validators.required],
      expiremonth: [null, Validators.required],
      expireyear: [null, Validators.required],
      cvv: [null, Validators.required],
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
      expireyear : 20
    })
  }

  pay($event){
    console.log("need to pay");
    $event.target.submit();
    return false;
  }
}
