import {Directive, HostBinding, Self} from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector : '[formControlName],[ngModel],[formControl]',
})
export class BSControlStatusDirective {

  public constructor(@Self() private control: NgControl) {
  }

  @HostBinding('class.is-valid')
  get ngClassValid(): boolean {
    if (this.control.control == null) {
      return false;
    }
    return this.control.control.valid;
  }

  @HostBinding('class.is-invalid')
  get ngClassInvalid(): boolean {
    if (this.control.control == null) {
      return false;
    }
    return this.control.control.invalid;
  }
}
