import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from '@angular/common/http';
import {PayWebRequestInfo} from "../model/pay-web-request.info";
import {Observable} from "rxjs";
import {PayWebRedirect} from '../model/pay-web-redirect';
import {isNil} from 'lodash';
import {PayWebCompleteResponse} from '../model/pay-web-complete-response.info';
import {PaygateConstants} from "../model/paygate.constants";

@Injectable({
  providedIn: 'root'
})
export class PaygateService{

  constructor(private http: HttpClient){

  }

  getTransactionDetail(paygateId: string): Observable<PayWebRequestInfo>{
    return this.http.get<PayWebRequestInfo>('rest/ui/transactionDetail',{
      params : {
        paygateId: paygateId
      }
    });
  }



  private sendCompleteTransaction(payRequestId: string, resultCode: string) : Observable<PayWebCompleteResponse> {
    return this.http.post<PayWebCompleteResponse>('rest/ui/complete',null, {
      params : {
        payRequestId: payRequestId,
        resultCode
      }
    });
  }

  completeTransaction(payRequestId: string){
    return this.sendCompleteTransaction(payRequestId, PaygateConstants.PAYGATE_TRANSACTION_APPROVED);
  }

  cancelTransaction(payRequestId: string) : Observable<PayWebCompleteResponse>{
    return this.sendCompleteTransaction(payRequestId, PaygateConstants.PAYGATE_TRANSACTION_CANCELLED);
  }

  declineTransaction(payRequestId: string) : Observable<PayWebCompleteResponse>{
    return this.sendCompleteTransaction(payRequestId, PaygateConstants.PAYGATE_TRANSACTION_DECLINED);
  }

  /**
   * Submit and redirect to the paygate portal for payment
   */
  performRedirect(redirect: PayWebCompleteResponse) {

    // The paygate form will be appended to the paygate div
    const paygateDiv = document.querySelector('#paygateDiv');

    // Select the existing form
    let paygateForm: HTMLFormElement = paygateDiv.querySelector('#paygate-redirect-form');
    if (isNil(paygateForm)) {
      // If it did not exist, create it
      paygateForm = document.createElement('form');
      paygateForm.setAttribute('id', 'paygate-redirect-form');
      paygateForm.setAttribute('action', redirect.redirectUrl);
      paygateForm.setAttribute('method', 'POST');

      const requestElement: HTMLInputElement = document.createElement('input');
      requestElement.setAttribute('id', 'request-id');
      requestElement.setAttribute('type', 'hidden');
      requestElement.setAttribute('name', 'PAY_REQUEST_ID');
      requestElement.setAttribute('value', redirect.payWebRedirect.payRequestId);
      paygateForm.appendChild(requestElement);

      const checksumElement: HTMLInputElement = document.createElement('input');
      checksumElement.setAttribute('id', 'checksum');
      checksumElement.setAttribute('type', 'hidden');
      checksumElement.setAttribute('name', 'CHECKSUM');
      checksumElement.setAttribute('value', redirect.payWebRedirect.checksum);
      paygateForm.appendChild(checksumElement);

      const transactionStatus: HTMLInputElement = document.createElement('input');
      transactionStatus.setAttribute('id', 'transaction-status');
      transactionStatus.setAttribute('type', 'hidden');
      transactionStatus.setAttribute('name', 'TRANSACTION_STATUS');
      transactionStatus.setAttribute('value', redirect.payWebRedirect.transactionStatus as any as string);
      paygateForm.appendChild(transactionStatus);
      document.querySelector('#paygateDiv').appendChild(paygateForm);
    } else {
      // Update the existing form
      paygateForm.querySelector('#request-id').setAttribute('value', redirect.payWebRedirect.payRequestId);
      paygateForm.querySelector('#checksum').setAttribute('value', redirect.payWebRedirect.checksum);
      paygateForm.querySelector('#transaction-status').setAttribute('value', redirect.payWebRedirect.transactionStatus as any  as string);
    }

    // Submit the paygate form
    paygateForm.submit();
  }
}
