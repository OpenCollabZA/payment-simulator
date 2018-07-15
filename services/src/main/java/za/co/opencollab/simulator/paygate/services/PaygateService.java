package za.co.opencollab.simulator.paygate.services;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.opencollab.simulator.paygate.dto.PayWebNotificationInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebRequestInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebResponseInfo;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static za.co.opencollab.simulator.paygate.PaygateConstants.*;
import static za.co.opencollab.simulator.paygate.util.PaygateChecksumUtil.generateChecksum;
import static za.co.opencollab.simulator.paygate.util.PaygateFormUtil.createForm;

/**
 * This service uses a map to keep track of pending transactions
 */
@Service
public class PaygateService {

    private static final Logger LOG = LoggerFactory.getLogger(PaygateService.class);

    @Value("${simulator.paygate.checksumKey}")
    private String checksumKey;

    private Executor executor = Executors.newSingleThreadExecutor();


    private final Map<String, PayWebRequestInfo> PAYMENTS_MAP = new HashMap<>();

    /**
     * Generates a new request to perform a transaction.
     * @param request The request from the client.
     * @return
     * @throws Exception
     */
    public PayWebResponseInfo initiateTransaction(PayWebRequestInfo request) throws Exception {
        PayWebResponseInfo info = new PayWebResponseInfo();
        info.setPayRequestId(request.getPaygateId());
        info.setReference(request.getReference());
        info.setPayRequestId(RandomStringUtils.randomNumeric(10));
        info.setChecksum(generateChecksum(info, checksumKey));

        PAYMENTS_MAP.put(info.getPayRequestId(), request);

        return info;
    }

    public PayWebRequestInfo getRequestForPayRequestId(String id){
        return PAYMENTS_MAP.get(id);
    }

    private boolean sendPaymentComplete(String paygateId) throws Exception {
        PayWebRequestInfo request = getRequestForPayRequestId(paygateId);
        PayWebNotificationInfo response = new PayWebNotificationInfo();


        response.setPaygateId(RandomStringUtils.randomNumeric(10));
        response.setPayRequestId(request.getPaygateId());
        response.setReference(request.getReference());
        response.setTransactionStatus(PAYGATE_TRANSACTION_APPROVED);
        response.setResultCode("CODE"); // TODO
        response.setAuthCode(null); // TODO
        response.setCurrency(request.getCurrency());
        response.setAmount(request.getAmount());
        response.setResultDesc("SUCCESS"); // TODO
        response.setTransactionId("1"); // TODO
        response.setRiskIndicator("1"); // TODO
        response.setPayMethod("CC"); // TODO
        response.setPayMethodDetail("Card"); // TODO
        response.setUser1(null);
        response.setUser2(null);
        response.setUser3(null);
        response.setVaultId(null);
        response.setVaultId(null);
        response.setPayvaultData1(null);
        response.setPayvaultData2(null);
        response.setChecksum(generateChecksum(response, checksumKey));

        Form form = createForm(response);
        String notifyURL = request.getNotifyUrl();
        String serviceResponse = ClientBuilder.newClient()
                .target(notifyURL)
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .post(Entity.form(form), String.class);

        // Specification states that the service should reply with "OK"
        return "OK".equals(serviceResponse);
    }

    public void completeTransaction(final String paygateId){
        executor.execute(() -> {
            int retries = 3;
            // Paygate spec says it will retry 3 times
            while(retries > 0) {
                try {
                    TimeUnit.SECONDS.sleep(50);
                    boolean ok = sendPaymentComplete(paygateId);
                    // Only if the request succeeded will we break.
                    if(ok){
                        break;
                    }

                } catch (Exception ignored) {
                    retries--;
                }
            }
        });
    }

    public boolean compareChecksum(final String checksum, final PayWebRequestInfo request) throws Exception {
        if(StringUtils.isEmpty(checksum)){
            return false;
        }
        return checksum.equals(generateChecksum(request, checksumKey));
    }
}
