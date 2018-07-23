package za.co.opencollab.simulator.paygate.dto;


/**
 * This class mimics the DTO as documented by die Paygate API document.
 */
public class PayWebRequestInfo {
    /**
     Your PayGateID – assigned by PayGate
     */
    private String paygateId;

    /**
     This is your reference number for use by your internal systems
     */
    private String reference;

    /**
     Transaction amount in cents.
     e.g. 32.99 is specified as 3299
     */
    private String amount;

    /**
     Currency code of the currency the customer is paying in.
     */
    private String currency;

    /**
     Once the transaction is completed, PayWeb will return the customer to
     a page on your web site. The page the customer must see is specified in
     this field
     */
    private String returnUrl;

    /**
     This is the date that the transaction was initiated on your website or
     system. The transaction date must be specified in 'Coordinated Universal
     Time' (UTC) and formatted as ‘YYYY-MM-DD HH:MM:SS’
     */
    private String transactionDate;

    /**
     The locale code identifies to PayGate the customer’s language, country
     and any special variant preferences (such as Date/Time format) which may
     be applied to the user interface. Not all the locales in the locale table are
     locale(s) you are using is supported. If the locale passed is not supported,
     then PayGate will default to the “en” locale
     */
    private String locale;

    /**
     Country code of the country the customer is paying from.
     */
    private String country;

    /**
     If the transaction is approved, PayWeb will email a payment confirmation to
     this email address – unless this is overridden at a gateway level by using
     the Payment Confirmation setting. This field remains compulsory but the
     sending of the confirmation email can be blocked
     */
    private String email;

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
    private String payMethod;

    /**
     The PAY_METHOD_DETAIL field should be left blank unless the merchant
     has more than one active payment method and wants to make sure that the
     client is presented with a specific payment method.
     Refer to the PAY_METHOD field above for more information
     */
    private String payMethodDetail;

    /**
     If the notify URL field is populated, then PayWeb will post the fields as
     specified in the Response table to the notify URL immediately when the
     transaction is completed. PayWeb will expect a response of 'OK'. If for any
     reason PayWeb cannot post to the notify URL successfully or if PayWeb
     doesn't receive the expected response of 'OK', then it will retry 3 times at
     30 minute intervals before giving up.
     */
    private String notifyUrl;

    /**
     This field is optional and has been included as a placeholder for merchant
     specific requirements. If this field is populated, then it must be included in
     the CHECKSUM calculation described below
     */
    private String user1;

    /**
     This field is optional and has been included as a placeholder for merchant
     specific requirements. If this field is populated, then it must be included in
     the CHECKSUM calculation described below
     */
    private String user2;

    /**
     This field is optional and has been included as a placeholder for merchant
     specific requirements. If this field is populated, then it must be included in
     the CHECKSUM calculation described below
     */
    private String user3;

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
    private String vault;

    /**
     This field is optional and should only be included if PayVault credit card
     tokenisation is enabled. If a PayVault token GUID is sent the credit card
     transaction will be processed using the credit card associated with the
     token. The cardholder will be shown the last 4 digits and expiry date of the
     credit card on the PayWeb page and will need to enter Cardholder Name
     and Credit Card CVV, as well as 3D Secure OTP if needed
     */
    private String vaultId;

    /**
     This field contains a calculated MD5 hash based on the values of ALL the
     above-mentioned fields and a key.
     */
    private String checksum;


    public String getPaygateId() {
        return paygateId;
    }

    public void setPaygateId(String paygateId) {
        this.paygateId = paygateId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethodDetail() {
        return payMethodDetail;
    }

    public void setPayMethodDetail(String payMethodDetail) {
        this.payMethodDetail = payMethodDetail;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getUser3() {
        return user3;
    }

    public void setUser3(String user3) {
        this.user3 = user3;
    }

    public String getVault() {
        return vault;
    }

    public void setVault(String vault) {
        this.vault = vault;
    }

    public String getVaultId() {
        return vaultId;
    }

    public void setVaultId(String vaultId) {
        this.vaultId = vaultId;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

}
