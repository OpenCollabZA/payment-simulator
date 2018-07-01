package za.co.opencollab.simulator.paygate.dto;

/**
 * This class mimics the DTO as documented by die Paygate API document.
 */
public class PayWebNotificationInfo {
    /**
     Your PayGateID – assigned by PayGate
     */
    private String paygateId;

    /**
     The PAY_REQUEST_ID is a GUID allocated by PayWeb to the transaction
     request received in step 1
     */
    private String payRequestId;

    /**
     This is your reference number for use by your internal systems
     */
    private String reference;

    /**
     The final status of the transaction
     */
    private String transactionStatus;

    /**
     This field contains a code indicating the result of the transaction. The description corresponding to
     this code is in the RESULT_DESC field.
     */
    private String resultCode;

    /**
     If the bank or financial institution approves the transaction, then the
     authorisation code will be placed in this field.
     For non-card payment methods this field is populated with “999999”.
     */
    private String authCode;

    /**
     Currency code of the currency the customer is paying in.
     */
    private String currency;

    /**
     Transaction amount in cents.
     e.g. 32.99 is specified as 3299
     This should be the same amount that was passed in the request. If it is not,
     then the data has been altered.
     */
    private String amount;

    /**
     This field contains a description for the result of the transaction. Refer to
     the Result Code table for a complete list. The numeric code corresponding
     to this description is in the RESULT_CODE field.
     */
    private String resultDesc;

    /**
     This field contains the PayGate unique reference number for the
     transaction.
     */
    private String transactionId;

    /**
     This is a 2-character field containing a risk indicator for this transaction. The
     first character describes the Verified-by-Visa / MasterCard SecureCode
     authentication; refer to the Authentication Indicator table for the possible
     values. The second character is for future use and will be set to ‘X’. Please
     refer to the MasterCard SecureCode & Verified by Visa section for more
     info.
     */
    private String riskIndicator;

    /**
     This field contains a code describing/confirming the payment method used
     to process the transaction. It is especially useful where the merchant has
     more than one payment method activated. Refer to the Payment Method
     Codes table for a complete list.
     */
    private String payMethod;

    /**
     This field may contain a description of the PAY_METHOD code. For
     instance if the PAY_METHOD is ‘CC’ to indicate credit card, then the
     PAY_METHOD_DETAIL will contain the type of credit card used
     ‘MasterCard’, ‘Visa’ etc. If the PAY_METHOD is something generic such as
     ‘EW’ = eWallet, then the PAY_METHOD_DETAIL field will contain the
     name of the eWallet. If populated, then this field is included in the
     CHECKSUM calculation described below.
     */
    private String payMethodDetail;

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
     This is the PayVault token associated to the card used to make the
     payment. This Vault ID can be re-used to process payments on the card
     either via PayWeb or PayBatch. Only the PAN and Expiry Date are linked
     to this token. This is an optional field and is only returned if PayVault
     tokenisation is requested.
     */
    private String vaultId;

    /**
     This field contains information on the credit card or e-wallet account linked
     to the PayVault token for the purpose of managing the use of the token.
     This is an optional field and is only returned if PayVault tokenisation is
     requested.
     */
    private String payvaultData1;

    /**
     This field contains information on the credit card or e-wallet account linked
     to the PayVault token for the purpose of managing the use of the token.
     This is an optional field and is only returned if PayVault tokenisation is
     requested.
     */
    private String payvaultData2;

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

    public String getPayRequestId() {
        return payRequestId;
    }

    public void setPayRequestId(String payRequestId) {
        this.payRequestId = payRequestId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRiskIndicator() {
        return riskIndicator;
    }

    public void setRiskIndicator(String riskIndicator) {
        this.riskIndicator = riskIndicator;
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

    public String getVaultId() {
        return vaultId;
    }

    public void setVaultId(String vaultId) {
        this.vaultId = vaultId;
    }

    public String getPayvaultData1() {
        return payvaultData1;
    }

    public void setPayvaultData1(String payvaultData1) {
        this.payvaultData1 = payvaultData1;
    }

    public String getPayvaultData2() {
        return payvaultData2;
    }

    public void setPayvaultData2(String payvaultData2) {
        this.payvaultData2 = payvaultData2;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
