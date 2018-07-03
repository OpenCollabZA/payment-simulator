package za.co.opencollab.simulator.paygate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.opencollab.simulator.paygate.dto.PayWebRequestInfo;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This REST Service is used by the front-end of the paygate simulator
 */
@Service
@Path("ui")
public class PaygateUIRestService {

    @Autowired
    private PaygateService paygateService;


    /**
     * Callback when the transaction was cancelled from the front end.
     * THe front-end should do a full-screen POST (not ajax) so that the browser can be redirected
     * back to the client application
     * @return
     */
    @POST
    @Path("cancel")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response cancelTransaction(@FormParam("PAY_REQUEST_ID") String paygateId) throws URISyntaxException {
        PayWebRequestInfo request = paygateService.getRequestForPayRequestId(paygateId);

        // TODO schedule a async callback to client

        return Response.temporaryRedirect(new URI(request.getReturnUrl())).build();
    }

    /**
     * Callback when the transaction was cancelled from the front end.
     * THe front-end should do a full-screen POST (not ajax) so that the browser can be redirected
     * back to the client application
     * @return
     */
    @POST
    @Path("complete")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response completeTranasction(@FormParam("PAY_REQUEST_ID") String paygateId) throws URISyntaxException {
        PayWebRequestInfo request = paygateService.getRequestForPayRequestId(paygateId);

        // TODO schedule a async callback to client

        return Response.temporaryRedirect(new URI(request.getReturnUrl())).build();
    }
}
