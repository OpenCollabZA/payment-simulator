package za.co.opencollab.simulator.paygate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.opencollab.simulator.paygate.dto.PayWebRequestInfo;
import za.co.opencollab.simulator.paygate.dto.PayWebResponseInfo;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

import static za.co.opencollab.simulator.paygate.PaygateConstants.*;
import static za.co.opencollab.simulator.paygate.util.PaygateFormUtil.createForm;

/**
 * This rest service is used by clients that want to perform payments using the paygate simulator
 */
@Service
@Path("payweb3")
public class PaygateServerRestService {


    @Autowired
    private PaygateService paygateService;

    @POST
    @Path("initiate.trans")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    public Response initiateTransaction(
            @FormParam(PARAM_PAYGATE_ID) String paygateId,
            @FormParam(PARAM_REFERENCE) String reference,
            @FormParam(PARAM_AMOUNT) String amount,
            @FormParam(PARAM_CURRENCY) String currency,
            @FormParam(PARAM_RETURN_URL) String returnUrl,
            @FormParam(PARAM_TRANSACTION_DATE) String transactionDate,
            @FormParam(PARAM_LOCALE) String locale,
            @FormParam(PARAM_COUNTRY) String country,
            @FormParam(PARAM_EMAIL) String email,
            @FormParam(PARAM_NOTIFY_URL) String notifyUrl,
            @FormParam(PARAM_CHECKSUM) String checksum
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

        if(!paygateService.compareChecksum(checksum, request)){
            throw new BadRequestException("Invalid checksum");
        }

        PayWebResponseInfo response = paygateService.initiateTransaction(request);
        Form form = createForm(response);
        return Response.ok()
                .entity(form)
                .build();
    }


    @POST
    @Path("process.trans")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response processTransaction(
            @FormParam(PARAM_PAY_REQUEST_ID) String paygateId,
            @FormParam(PARAM_CHECKSUM) String reference) throws URISyntaxException {
        return Response.temporaryRedirect(new URI("angular/screen")).build();
    }
}
