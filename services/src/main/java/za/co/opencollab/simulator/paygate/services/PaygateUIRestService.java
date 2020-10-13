package za.co.opencollab.simulator.paygate.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.opencollab.simulator.paygate.PaygateConstants;
import za.co.opencollab.simulator.paygate.dto.PayWebCompleteResponse;
import za.co.opencollab.simulator.paygate.dto.PayWebRedirect;
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

    private static final Logger LOG = LoggerFactory.getLogger(PaygateUIRestService.class);

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
            LOG.warn(String.format("Paygate ID %s does not exist", paygateId));
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
    public PayWebCompleteResponse cancelTransaction(@QueryParam("payRequestId") String paygateId) throws URISyntaxException {
        Optional<PayWebRequestInfo> request = paygateService.getRequestForPayRequestId(paygateId);
        if(request.isPresent()){
            try {
                return paygateService.completeTransaction(paygateId, PaymentResult.USER_CANCELLED);
            } catch (Exception e) {
                throw new BadRequestException("Exception handling request", e);
            }
        }
        throw new BadRequestException("Invalid id");
    }

    /**
     * Callback when the transaction was cancelled from the front end.
     * The front-end should do a full-screen POST (not ajax) so that the browser can be redirected
     * back to the client application
     * @return
     */
    @POST
    @Path("complete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PayWebCompleteResponse completeTransaction(@QueryParam("payRequestId") String payRequestId) throws URISyntaxException {
        Optional<PayWebRequestInfo> request = paygateService.getRequestForPayRequestId(payRequestId);
        if(request.isPresent()){
            try {
                return paygateService.completeTransaction(payRequestId, PaymentResult.APPROVED);
            } catch (Exception e) {
                throw new BadRequestException("Exception handling request", e);
            }
        }
        throw new BadRequestException("Invalid id");
    }
}
