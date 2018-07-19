package za.co.opencollab.simulator.paygate;

public interface PaygateConstants {

	String PAYGATE_TRANSACTION_NOT_DONE = "0";
	String PAYGATE_TRANSACTION_APPROVED = "1";
	String PAYGATE_TRANSACTION_DECLINED = "2";
	String PAYGATE_TRANSACTION_CANCELLED = "3";
	String PAYGATE_TRANSACTION_USER_CANCELLED = "4";



	String PARAM_PAYGATE_ID = "PAYGATE_ID";
	String PARAM_REFERENCE = "REFERENCE";
	String PARAM_AMOUNT = "AMOUNT";
	String PARAM_CURRENCY = "CURRENCY";
	String PARAM_RETURN_URL = "RETURN_URL";
	String PARAM_TRANSACTION_DATE = "TRANSACTION_DATE";
	String PARAM_LOCALE = "LOCALE";
	String PARAM_COUNTRY = "COUNTRY";
	String PARAM_EMAIL = "EMAIL";
	String PARAM_NOTIFY_URL = "NOTIFY_URL";
	String PARAM_CHECKSUM = "CHECKSUM";
	String PARAM_PAY_REQUEST_ID = "PAY_REQUEST_ID";
	String PARAM_TRANSACTION_STATUS = "TRANSACTION_STATUS";
	String PARAM_RESULT_CODE = "RESULT_CODE";
	String PARAM_AUTH_CODE = "AUTH_CODE";
	String PARAM_RESULT_DESC = "RESULT_DESC";
	String PARAM_TRANSACTION_ID = "TRANSACTION_ID";
	String PARAM_RISK_INDICATOR = "RISK_INDICATOR";
	String PARAM_PAY_METHOD = "PAY_METHOD";
	String PARAM_PAY_METHOD_DETAIL = "PAY_METHOD_DETAIL";
	String PARAM_USER1 = "USER1";
	String PARAM_USER2 = "USER2";
	String PARAM_USER3 = "USER3";
	String PARAM_VAULT_ID = "VAULT_ID";
	String PARAM_PAYVAULT_DATA_1 = "PAYVAULT_DATA_1";
	String PARAM_PAYVAULT_DATA_2 = "PAYVAULT_DATA_2";

	enum PaymentResult {
		APPROVED(PAYGATE_TRANSACTION_APPROVED, "Transaction approved"),
		CANCELLED(PAYGATE_TRANSACTION_CANCELLED, "Transaction cancelled"),
		NOT_DONE(PAYGATE_TRANSACTION_NOT_DONE, "Transaction could not be completed"),
		DECLINED(PAYGATE_TRANSACTION_DECLINED, "Transaction could not be completed"),
		USER_CANCELLED(PAYGATE_TRANSACTION_USER_CANCELLED, "User cancelled the transaction");


		public String statusCode;

		public String description;

		PaymentResult(String statusCode, String description){
			this.statusCode = statusCode;
			this.description = description;
		}


	}
}
