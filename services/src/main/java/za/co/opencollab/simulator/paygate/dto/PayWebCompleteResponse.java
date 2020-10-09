package za.co.opencollab.simulator.paygate.dto;

public class PayWebCompleteResponse {
	private String redirectUrl;
	private PayWebRedirect payWebRedirect;
	
	/**
	 * Getter for property 'redirectUrl'.
	 *
	 * @return Value for property 'redirectUrl'.
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}
	
	/**
	 * Setter for property 'redirectUrl'.
	 *
	 * @param redirectUrl Value to set for property 'redirectUrl'.
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	/**
	 * Getter for property 'payWebRedirect'.
	 *
	 * @return Value for property 'payWebRedirect'.
	 */
	public PayWebRedirect getPayWebRedirect() {
		return payWebRedirect;
	}
	
	/**
	 * Setter for property 'payWebRedirect'.
	 *
	 * @param payWebRedirect Value to set for property 'payWebRedirect'.
	 */
	public void setPayWebRedirect(PayWebRedirect payWebRedirect) {
		this.payWebRedirect = payWebRedirect;
	}
}
