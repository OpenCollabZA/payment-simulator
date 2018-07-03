package za.co.opencollab.simulator.paygate.services;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.opencollab.simulator.paygate.dto.PayWebRequestInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebResponseInfo;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.defaultString;

/**
 * This service uses a map to keep track of pending transactions
 */
@Service
public class PaygateService {

    private static final Logger LOG = LoggerFactory.getLogger(PaygateService.class);

    @Value("${simulator.paygate.checksumKey}")
    private String checksumKey;


    private final Map<String, PayWebRequestInfo> PAYMENTS_MAP = new HashMap<>();

    public PayWebResponseInfo initiateTransaction(PayWebRequestInfo request) throws Exception {
        PayWebResponseInfo info = new PayWebResponseInfo();
        info.setPayRequestId(request.getPaygateId());
        info.setReference(request.getReference());
        info.setPayRequestId(RandomStringUtils.randomNumeric(10));
        info.setChecksum(generateChecksum(info));

        PAYMENTS_MAP.put(info.getPayRequestId(), request);

        return info;
    }

    public PayWebRequestInfo getRequestForPayRequestId(String id){
        return PAYMENTS_MAP.get(id);
    }

    protected String generateChecksum(PayWebRequestInfo request) throws Exception {

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

    protected String generateChecksum(PayWebResponseInfo response) throws Exception {
        String concatenatedFields = "";
        concatenatedFields += String.valueOf(response.getPaygateId());
        concatenatedFields += response.getPayRequestId();
        concatenatedFields += response.getReference();
        concatenatedFields += checksumKey;

        return generateMD5Hash(concatenatedFields);
    }


    protected String generateMD5Hash(String source) throws Exception {
        LOG.info(String.format("Generating checksum for \"%s\'", source));

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(source.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        LOG.info(String.format("Generated checksum is \"%s\'", sb.toString()));
        return sb.toString();
    }

}
