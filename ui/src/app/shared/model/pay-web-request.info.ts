

/**
 * This class mimics the DTO as documented by die Paygate API document.
 */
export interface PayWebRequestInfo {
  /**
   Your PayGateID – assigned by PayGate
   */
  paygateId: string;

  /**
   This is your reference number for use by your internal systems
   */
  reference: string;

  /**
   Transaction amount in cents.
   e.g. 32.99 is specified as 3299
   */
  amount: string;

  /**
   Currency code of the currency the customer is paying in.
   */
  currency: string;

  /**
   Once the transaction is completed, PayWeb will return the customer to
   a page on your web site. The page the customer must see is specified in
   this field
   */
  returnUrl: string;

  /**
   This is the date that the transaction was initiated on your website or
   system. The transaction date must be specified in 'Coordinated Universal
   Time' (UTC) and formatted as ‘YYYY-MM-DD HH:MM:SS’
   */
  transactionDate: string;

  /**
   The locale code identifies to PayGate the customer’s language, country
   and any special variant preferences (such as Date/Time format) which may
   be applied to the user interface. Not all the locales in the locale table are
   locale(s) you are using is supported. If the locale passed is not supported,
   then PayGate will default to the “en” locale
   */
  locale: string;

  /**
   Country code of the country the customer is paying from.
   */
  country: string;

  /**
   If the transaction is approved, PayWeb will email a payment confirmation to
   this email address – unless this is overridden at a gateway level by using
   the Payment Confirmation setting. This field remains compulsory but the
   sending of the confirmation email can be blocked
   */
  email: string;

  /**
   The payment method(s) to show to the client.
    If this field is not populated, then all payment methods activated
   will be shown on the menu page.
    If the merchant has more than one wallet method (EW) activated,
   and this field is populated with ‘EW’, then PayWeb will present the
   client with a menu of all the active wallet payment methods to
   choose from.
    If both the PAY_METHOD and PAY_METHOD_DETAIL fields are
   populated, then PayWeb will display the secure payment page for
   that specific payment method only.
   */
  payMethod: string;

  /**
   The PAY_METHOD_DETAIL field should be left blank unless the merchant
   has more than one active payment method and wants to make sure that the
   client is presented with a specific payment method.
   Refer to the PAY_METHOD field above for more information
   */
  payMethodDetail: string;

  /**
   If the notify URL field is populated, then PayWeb will post the fields as
   specified in the Response table to the notify URL immediately when the
   transaction is completed. PayWeb will expect a response of 'OK'. If for any
   reason PayWeb cannot post to the notify URL successfully or if PayWeb
   doesn't receive the expected response of 'OK', then it will retry 3 times at
   30 minute intervals before giving up.
   */
  notifyUrl: string;

  /**
   This field is optional and has been included as a placeholder for merchant
   specific requirements. If this field is populated, then it must be included in
   the CHECKSUM calculation described below
   */
  user1: string;

  /**
   This field is optional and has been included as a placeholder for merchant
   specific requirements. If this field is populated, then it must be included in
   the CHECKSUM calculation described below
   */
  user2: string;

  /**
   This field is optional and has been included as a placeholder for merchant
   specific requirements. If this field is populated, then it must be included in
   the CHECKSUM calculation described below
   */
  user3: string;

  /**
   This field is optional but should only be included if PayVault credit card
   tokenisation is enabled on the merchant profile. This field is used to
   indicate whether a PayVault token should be issued for the credit card used
   to make the payment. If True (1) the credit card number will be added to
   PayVault and the associated Token will be returned in the response to the
   merchant.
   0 = False
   1 = True
   */
  vault: string;

  /**
   This field is optional and should only be included if PayVault credit card
   tokenisation is enabled. If a PayVault token GUID is sent the credit card
   transaction will be processed using the credit card associated with the
   token. The cardholder will be shown the last 4 digits and expiry date of the
   credit card on the PayWeb page and will need to enter Cardholder Name
   and Credit Card CVV, as well as 3D Secure OTP if needed
   */
  vaultId: string;

  /**
   This field contains a calculated MD5 hash based on the values of ALL the
   above-mentioned fields and a key.
   */
  checksum: string;
}
