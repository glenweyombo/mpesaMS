package ke.co.webwire.turaco.service.mpesa.stk;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StkResponse {

    @JsonProperty("CheckoutRequestID")
    private String checkoutRequestId;

    @JsonProperty("ResponseCode")
    private String responseCode;

    @JsonProperty("ResponseDescription")
    private String description;

    public String getCheckoutRequestId() {
        return checkoutRequestId;
    }

    public StkResponse setCheckoutRequestId(String checkoutRequestId) {
        this.checkoutRequestId = checkoutRequestId;
        return this;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public StkResponse setResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StkResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return (
            "StkResponse{" +
            "checkoutRequestId='" +
            checkoutRequestId +
            '\'' +
            ", responseCode='" +
            responseCode +
            '\'' +
            ", description='" +
            description +
            '\'' +
            '}'
        );
    }
}
