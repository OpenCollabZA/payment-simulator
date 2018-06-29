import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CreditCardInfo} from "../shared/model/credit-card.info";

@Component({
  selector: 'app-creditcard',
  templateUrl: './creditcard.component.html',
  styleUrls: ['./creditcard.component.css']
})
export class CreditcardComponent implements OnInit {

  creditCard = new CreditCardInfo();

  constructor() { }

  ngOnInit() {
  }

}
