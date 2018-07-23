package za.co.opencollab.simulator.paygate.dto;

/**
 * This class mimics the DTO as documented by die Paygate API document.
 */
public class PayWebResponseInfo {

    /**
     This should be the same PayGate ID that was passed in the request; if it is
     not, then the data has been altered
     */
    private long paygateId;

    /**
     The PAY_REQUEST_ID is a GUID allocated by PayWeb to the transaction
     request received in step 1
     */
    private String payRequestId;

    /**
     The reference that was passed in the step 1 request is returned unaltered
     */
    private String reference;

    /**
     This field contains a calculated MD5 hash based on the values of ALL the
     above-mentioned fields and a key.
     */
    private String checksum;

    public long getPaygateId() {
        return paygateId;
    }

    public void setPaygateId(long paygateId) {
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

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
