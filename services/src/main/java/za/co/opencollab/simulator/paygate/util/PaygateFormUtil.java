package za.co.opencollab.simulator.paygate.util;

import za.co.opencollab.simulator.paygate.dto.PayWebNotificationInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebResponseInfo;

import javax.ws.rs.core.Form;

import static za.co.opencollab.simulator.paygate.PaygateConstants.*;

/**
 * Utility to convert DTO objects to formbased objects that can be submitted.
 */
public final class PaygateFormUtil {

	/**
	 * Convert a <code>PayWebNotificationInfo</code> to a form.
	 * @param payWebNotificationInfo The object to convert.
	 * @return The submittable form.
	 */
	public static Form createForm(PayWebNotificationInfo payWebNotificationInfo){
		Form form = new Form();
		form.param(PARAM_PAYGATE_ID, payWebNotificationInfo.getPaygateId());
		form.param(PARAM_PAY_REQUEST_ID, payWebNotificationInfo.getPayRequestId());
		form.param(PARAM_REFERENCE, payWebNotificationInfo.getReference());
		form.param(PARAM_TRANSACTION_STATUS, payWebNotificationInfo.getTransactionStatus());
		form.param(PARAM_RESULT_CODE, payWebNotificationInfo.getResultCode());
		form.param(PARAM_AUTH_CODE, payWebNotificationInfo.getAuthCode());
		form.param(PARAM_CURRENCY, payWebNotificationInfo.getCurrency());
		form.param(PARAM_AMOUNT, payWebNotificationInfo.getAmount());
		form.param(PARAM_RESULT_DESC, payWebNotificationInfo.getResultDesc());
		form.param(PARAM_TRANSACTION_ID, payWebNotificationInfo.getTransactionId());
		form.param(PARAM_RISK_INDICATOR, payWebNotificationInfo.getRiskIndicator());
		form.param(PARAM_PAY_METHOD, payWebNotificationInfo.getPayMethod());
		form.param(PARAM_PAY_METHOD_DETAIL, payWebNotificationInfo.getPayMethodDetail());
		form.param(PARAM_USER1, payWebNotificationInfo.getUser1());
		form.param(PARAM_USER2, payWebNotificationInfo.getUser2());
		form.param(PARAM_USER3, payWebNotificationInfo.getUser3());
		form.param(PARAM_VAULT_ID, payWebNotificationInfo.getVaultId());
		form.param(PARAM_PAYVAULT_DATA_1, payWebNotificationInfo.getPayvaultData1());
		form.param(PARAM_PAYVAULT_DATA_2, payWebNotificationInfo.getPayvaultData2());
		form.param(PARAM_CHECKSUM, payWebNotificationInfo.getChecksum());
		return form;
	}

	public static Form createForm(PayWebResponseInfo responseInfo){
		Form form = new Form();
		form.param(PARAM_PAY_REQUEST_ID, responseInfo.getPayRequestId());
		form.param(PARAM_PAYGATE_ID, Long.toString(responseInfo.getPaygateId()));
		form.param(PARAM_REFERENCE, responseInfo.getReference());
		form.param(PARAM_CHECKSUM, responseInfo.getChecksum());
		return form;
	}
}
