package za.co.opencollab.simulator.paygate.services;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.opencollab.simulator.paygate.dto.PayWebRequestInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebResponseInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This rest service is used by clients that want to perform payments using the paygate simulator
 */
@Path("payweb3")
public class PaygateServerRestService {


    @Autowired
    private PaygateService paygateService;

    @POST
    @Path("initiate.trans")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public PayWebResponseInfo initiateTransaction(
            @FormParam("PAYGATE_ID") String paygateId,
            @FormParam("REFERENCE") String reference,
            @FormParam("AMOUNT") String amount,
            @FormParam("CURRENCY") String currency,
            @FormParam("RETURN_URL") String returnUrl,
            @FormParam("TRANSACTION_DATE") String transactionDate,
            @FormParam("LOCALE") String locale,
            @FormParam("COUNTRY") String country,
            @FormParam("EMAIL") String email,
            @FormParam("NOTIFY_URL") String notifyUrl,
            @FormParam("CHECKSUM") String checksum
            ) throws Exception {
        PayWebRequestInfo request = new PayWebRequestInfo();
        request.setPaygateId(paygateId);
        request.setReference(reference);
        request.setAmount(amount);
        request.setCurrency(currency);
        request.setReturnUrl(returnUrl);
        request.setTransactionDate(transactionDate);
        request.setLocale(locale);
        request.setCountry(country);
        request.setEmail(email);
        request.setNotifyUrl(notifyUrl);
        request.setChecksum(checksum);

        if(!checksum.equals(paygateService.generateChecksum(request))){
            throw new BadRequestException("Invalid checksum");
        }

        return paygateService.initiateTransaction(request);
    }

    @POST
    @Path("process.trans")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response processTransaction(
            @FormParam("PAY_REQUEST_ID") String paygateId,
            @FormParam("CHECKSUM") String reference) throws URISyntaxException {
        return Response.temporaryRedirect(new URI("angular/screen")).build();
    }
}