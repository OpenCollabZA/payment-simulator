import { Component, OnInit } from '@angular/core';
import {Creditcard} from "../shared/model/creditcard";
import {Router} from "@angular/router";

@Component({
  selector: 'app-creditcard',
  templateUrl: './creditcard.component.html',
  styleUrls: ['./creditcard.component.css']
})
export class CreditcardComponent implements OnInit {

  creditCard = new Creditcard();

  constructor() { }

  ngOnInit() {
  }

}
