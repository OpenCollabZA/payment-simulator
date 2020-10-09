package za.co.opencollab.simulator.paygate.services;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.opencollab.simulator.paygate.dto.PayWebCompleteResponse;
import za.co.opencollab.simulator.paygate.dto.PayWebNotificationInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebRedirect;
import za.co.opencollab.simulator.paygate.dto.PayWebRequestInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebResponseInfo;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static za.co.opencollab.simulator.paygate.PaygateConstants.*;
import static za.co.opencollab.simulator.paygate.util.PaygateChecksumUtil.generateChecksum;
import static za.co.opencollab.simulator.paygate.util.PaygateChecksumUtil.generateChecksumForRedirect;
import static za.co.opencollab.simulator.paygate.util.PaygateFormUtil.createForm;

/**
 * This service uses a map to keep track of pending transactions
 */
@Service
public class PaygateService {

    private static final Logger LOG = LoggerFactory.getLogger(PaygateService.class);

    /**
     * Single checksum key used for all clients of the simulator
     */
    @Value("${simulator.paygate.checksumKey}")
    private String checksumKey;



    /**
     * Executor for async processes
     */
    private final Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Map of pending transactions
     */
    private final Map<String, PayWebRequestInfo> PAYMENTS_MAP = new ConcurrentHashMap<>();


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

    /**
     * Gets a payment request for the specified ID.
     * @param id ID of the request to retrieve
     * @return
     */
    public Optional<PayWebRequestInfo> getRequestForPayRequestId(String id){
        return Optional.ofNullable(PAYMENTS_MAP.get(id));
    }

    /**
     * Completes a payment request.
     * @param payRequestId
     * @param result
     * @return
     * @throws Exception
     */
    private boolean sendPaymentComplete(String payRequestId, final PaymentResult result) throws Exception {
        LOG.info("Sending payment complete: " + payRequestId);

        Optional<PayWebRequestInfo> requestOptional = getRequestForPayRequestId(payRequestId);
        if(!requestOptional.isPresent()){
            throw new IllegalArgumentException(String.format("Transaction with paygate id %s does not exist", payRequestId));
        }
        PayWebRequestInfo request = requestOptional.get();
        PayWebNotificationInfo response = new PayWebNotificationInfo();


        response.setPaygateId(RandomStringUtils.randomNumeric(10));
        response.setPayRequestId(request.getPaygateId());
        response.setReference(request.getReference());
        response.setTransactionStatus(result.statusCode);
        response.setResultCode("CODE"); // TODO
        response.setAuthCode(null); // TODO
        response.setCurrency(request.getCurrency());
        response.setAmount(request.getAmount());
        response.setResultDesc(result.description);
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
                .request(MediaType.TEXT_HTML)
                .post(Entity.form(form), String.class);

        // Specification states that the service should reply with "OK"
        return "OK".equals(serviceResponse);
    }

    /**
     * Completes a pending transaction. The transaction can be completed with any <code>PaymentResult</code>. eg. cancelled,
     * approved, etc.
     * @param paygateId ID of the transaction to complete
     * @param result Result to complete the transaction with
     */
    public PayWebCompleteResponse completeTransaction(final String payRequestId, final PaymentResult result) throws Exception {
        executor.execute(() -> {
            int retries = 3;
            // Paygate spec says it will retry 3 times
            while(retries > 0) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    boolean ok = sendPaymentComplete(payRequestId, result);
                    // Only if the request succeeded will we break.
                    if(ok){
                        break;
                    }

                } catch (Exception ignored) {
                    retries--;
                }
            }
            this.PAYMENTS_MAP.remove(payRequestId);
        });
        PayWebRequestInfo request = getRequestForPayRequestId(payRequestId).orElseThrow(InvalidParameterException::new);
        PayWebCompleteResponse completeResponse = new PayWebCompleteResponse();
        PayWebRedirect redirect = new PayWebRedirect();
        redirect.setPayRequestId(payRequestId);
        redirect.setTransactionStatus(1);
        redirect.setChecksum(generateChecksumForRedirect(request, redirect, checksumKey)); // TODO generate proper checksum

        completeResponse.setPayWebRedirect(redirect);
        completeResponse.setRedirectUrl(request.getReturnUrl());
        return completeResponse;
    }

    boolean compareChecksum(final String checksum, final PayWebRequestInfo request) throws Exception {
        if(StringUtils.isEmpty(checksum)){
            LOG.warn("Checksum is empty");
            return false;
        }
        String generatedChecksum = generateChecksum(request, checksumKey);
        LOG.info("Checksum key: " + checksumKey);
        LOG.info("Generated checksum: " + generatedChecksum);
        LOG.info("Provided  checksum: " + checksum);

        return checksum.equals(generatedChecksum);
    }
}
