package za.co.opencollab.simulator.paygate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.opencollab.simulator.paygate.PaygateConstants;
import za.co.opencollab.simulator.paygate.dto.PayWebRequestInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static za.co.opencollab.simulator.paygate.PaygateConstants.*;

/**
 * This REST Service is used by the front-end of the paygate simulator
 */
@Service
@Path("ui")
public class PaygateUIRestService {

    @Autowired
    private PaygateService paygateService;


    /**
     * Get the transaction details for a paygateId
     * @param paygateId
     * @return
     */
    @GET
    @Path("transactionDetail")
    @Produces(MediaType.APPLICATION_JSON)
    public PayWebRequestInfo getTransactionDetails(@QueryParam("paygateId") String paygateId){
        Optional<PayWebRequestInfo> request = paygateService.getRequestForPayRequestId(paygateId);
        if(request.isPresent()){
            return request.get();
        }
        else{
            throw new NotFoundException(String.format("Paygate ID %s does not exist", paygateId));
        }
    }

    /**
     * Callback when the transaction was cancelled from the front end.
     * The front-end should do a full-screen POST (not ajax) so that the browser can be redirected
     * back to the client application
     * @return
     */
    @POST
    @Path("cancel")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response cancelTransaction(@FormParam(PARAM_PAY_REQUEST_ID) String paygateId) throws URISyntaxException {
        Optional<PayWebRequestInfo> request = paygateService.getRequestForPayRequestId(paygateId);
        if(request.isPresent()){
            paygateService.completeTransaction(paygateId, PaygateConstants.PaymentResult.USER_CANCELLED);
            return Response.temporaryRedirect(new URI(request.get().getReturnUrl())).build();
        }
        else{
            return Response.status(BAD_REQUEST).build();
        }
    }

    /**
     * Callback when the transaction was cancelled from the front end.
     * The front-end should do a full-screen POST (not ajax) so that the browser can be redirected
     * back to the client application
     * @return
     */
    @POST
    @Path("complete")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response completeTransaction(@FormParam(PARAM_PAY_REQUEST_ID) String paygateId) throws URISyntaxException {
        Optional<PayWebRequestInfo> request = paygateService.getRequestForPayRequestId(paygateId);
        if(request.isPresent()){
            paygateService.completeTransaction(paygateId, PaygateConstants.PaymentResult.APPROVED);
            return Response.temporaryRedirect(new URI(request.get().getReturnUrl())).build();
        }
        else{
            return Response.status(BAD_REQUEST).build();
        }
    }
}
