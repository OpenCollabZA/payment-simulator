import {PayWebRedirect} from './pay-web-redirect';
export interface PayWebCompleteResponse {
  redirectUrl: string;
  payWebRedirect: PayWebRedirect;
}
