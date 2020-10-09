package za.co.opencollab.simulator.paygate.util;

import za.co.opencollab.simulator.paygate.dto.PayWebNotificationInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebRedirect;
import za.co.opencollab.simulator.paygate.dto.PayWebRequestInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebResponseInfo;

import java.security.MessageDigest;

import static org.apache.commons.lang.StringUtils.defaultString;

/**
 * Utitliy class for calculating checksums for various paygate entities.
 */
public final class PaygateChecksumUtil {

	/**
	 * Generate the checksum for a <code>PayWebRequestInfo</code>
	 * @param request The request to create the checksum for.
	 * @return The calculated checksum
	 * @throws Exception
	 */
	public static String generateChecksum(PayWebRequestInfo request, final String checksumKey) throws Exception {
		String concatenatedFields = "";
		concatenatedFields += defaultString(request.getPaygateId());
		concatenatedFields += defaultString(request.getReference());
		concatenatedFields += defaultString(request.getAmount());
		concatenatedFields += defaultString(request.getCurrency());
		concatenatedFields += defaultString(request.getReturnUrl());
		concatenatedFields += defaultString(request.getTransactionDate());
		concatenatedFields += defaultString(request.getLocale());
		concatenatedFields += defaultString(request.getCountry());
		concatenatedFields += defaultString(request.getEmail());
		concatenatedFields += defaultString(request.getPayMethod());
		concatenatedFields += defaultString(request.getPayMethodDetail());
		concatenatedFields += defaultString(request.getNotifyUrl());
		concatenatedFields += defaultString(request.getUser1());
		concatenatedFields += defaultString(request.getUser2());
		concatenatedFields += defaultString(request.getUser3());
		concatenatedFields += defaultString(request.getVault());
		concatenatedFields += defaultString(request.getVaultId());
		concatenatedFields += checksumKey;

		return generateMD5Hash(concatenatedFields);
	}

	public static String generateChecksumForRedirect(PayWebRequestInfo payWebRequestInfo, PayWebRedirect redirect, String checksumKey) throws Exception {
		String concatenatedFields = "";
		concatenatedFields += defaultString(payWebRequestInfo.getPaygateId());
		concatenatedFields += defaultString(redirect.getPayRequestId());
		concatenatedFields += defaultString(Integer.toString(redirect.getTransactionStatus()));
		concatenatedFields += defaultString(payWebRequestInfo.getReference());
		concatenatedFields += checksumKey;

		return generateMD5Hash(concatenatedFields);
	}

	/**
	 * Generate the checksum for a <code>PayWebRequestInfo</code>
	 * @param response The object to create the checksum for.
	 * @return The calculated checksum
	 * @throws Exception
	 */
	public static String generateChecksum(PayWebResponseInfo response, final String checksumKey) throws Exception {
		String concatenatedFields = "";
		concatenatedFields += String.valueOf(response.getPaygateId());
		concatenatedFields += response.getPayRequestId();
		concatenatedFields += response.getReference();
		concatenatedFields += checksumKey;
		return generateMD5Hash(concatenatedFields);
	}

	public static String generateChecksum(final PayWebNotificationInfo notification, final String checksumKey) throws Exception {
		String concatenatedFields = "";
		concatenatedFields += defaultString(notification.getPaygateId());
		concatenatedFields += defaultString(notification.getPayRequestId());
		concatenatedFields += defaultString(notification.getReference());
		concatenatedFields += defaultString(notification.getTransactionStatus());
		concatenatedFields += defaultString(notification.getResultCode());
		concatenatedFields += defaultString(notification.getAuthCode());
		concatenatedFields += defaultString(notification.getCurrency());
		concatenatedFields += defaultString(notification.getAmount());
		concatenatedFields += defaultString(notification.getResultDesc());
		concatenatedFields += defaultString(notification.getTransactionId());
		concatenatedFields += defaultString(notification.getRiskIndicator());
		concatenatedFields += defaultString(notification.getPayMethod());
		concatenatedFields += defaultString(notification.getPayMethodDetail());
		concatenatedFields += defaultString(notification.getUser1());
		concatenatedFields += defaultString(notification.getUser2());
		concatenatedFields += defaultString(notification.getUser3());
		concatenatedFields += defaultString(notification.getVaultId());
		concatenatedFields += defaultString(notification.getPayvaultData1());
		concatenatedFields += defaultString(notification.getPayvaultData2());
		concatenatedFields += checksumKey;
		return generateMD5Hash(concatenatedFields);
	}


	private static String generateMD5Hash(String source) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(source.getBytes());
		byte[] digest = md.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}

}
