/**
 * The type Pay web redirect.
 */
export interface PayWebRedirect {
	/**
	 Your PayGateID – assigned by PayGate
	 */
	payRequestId: string;

	/**
	 * The final status of the transaction. Refer to the Transaction Status table for
	 * a list of possible values.
	 */
	transactionStatus: number;

	/**
	 * This field contains a calculated MD5 hash based on the values of the
	 * PAYGATE_ID, PAY_REQUEST_ID, TRANSACTION_STATUS,
	 * REFERENCE fields and a key.
	 */
  checksum: string;
}
