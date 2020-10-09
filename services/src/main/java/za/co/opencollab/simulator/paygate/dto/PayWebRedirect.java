package za.co.opencollab.simulator.paygate.dto;

/**
 * The type Pay web redirect.
 */
public class PayWebRedirect {
	/**
	 Your PayGateID – assigned by PayGate
	 */
	private String payRequestId;
	
	/**
	 * The final status of the transaction. Refer to the Transaction Status table for
	 * a list of possible values.
	 */
	private Integer transactionStatus;
	
	/**
	 * This field contains a calculated MD5 hash based on the values of the
	 * PAYGATE_ID, PAY_REQUEST_ID, TRANSACTION_STATUS,
	 * REFERENCE fields and a key.
	 */
	private String checksum;
	
	/**
	 * Gets paygate id.
	 *
	 * @return the paygate id
	 */
	public String getPayRequestId() {
		return payRequestId;
	}
	
	/**
	 * Sets paygate id.
	 *
	 * @param payRequestId the paygate id
	 */
	public void setPayRequestId(String payRequestId) {
		this.payRequestId = payRequestId;
	}
	
	/**
	 * Gets transaction status.
	 *
	 * @return the transaction status
	 */
	public Integer getTransactionStatus() {
		return transactionStatus;
	}
	
	/**
	 * Sets transaction status.
	 *
	 * @param transactionStatus the transaction status
	 */
	public void setTransactionStatus(Integer transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	/**
	 * Gets checksum.
	 *
	 * @return the checksum
	 */
	public String getChecksum() {
		return checksum;
	}
	
	/**
	 * Sets checksum.
	 *
	 * @param checksum the checksum
	 */
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
}
