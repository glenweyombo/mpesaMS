package ke.co.webwire.turaco.service.mpesa.stk;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * https://developer.safaricom.co.ke/lipa-na-m-pesa-online/apis/post/stkpush/v1/processrequest
 */
public class StkCallbackResponse {

    /**
     * This is the root key for the entire Callback message.
     */
    @JsonProperty("Body")
    private Body body;

    public Body getBody() {
        return body;
    }

    public boolean isSuccess() {
        return "0".equals(body.content.resultCode);
    }

    public Optional<String> getTransactionId() {
        return (isSuccess()) ? body.content.metadata.findValue("MpesaReceiptNumber") : Optional.empty();
    }

    public Optional<BigDecimal> getAmount() {
        return isSuccess() ? body.content.metadata.findValue("Amount").map(BigDecimal::new) : Optional.empty();
    }

    public StkCallbackResponse setBody(Body body) {
        this.body = body;
        return this;
    }

    public static class Body {

        /**
         * This is the first child of the Body.
         */
        @JsonProperty("stkCallback")
        private BodyContent content;

        public BodyContent getContent() {
            return content;
        }

        public Body setContent(BodyContent content) {
            this.content = content;
            return this;
        }

        @Override
        public String toString() {
            return "Body{" + "content=" + content + '}';
        }
    }

    public static class BodyContent {

        /**
         * This is a global unique identifier of the processed checkout transaction request.
         * This is the same value returned in the acknowledgement message of the initial request.
         */
        @JsonProperty("CheckoutRequestID")
        private String checkoutRequestId;

        /**
         * This is a numeric status code that indicates the status of the transaction processing.
         * 0 means successful processing and any other code means an error occured or the transaction failed.
         */
        @JsonProperty("ResultCode")
        private String resultCode;

        /**
         * Result description is a message from the API that gives the status of the request processing, usualy maps to a specific ResultCode value.
         * It can be a Success processing message or an error description message.
         */
        @JsonProperty("ResultDesc")
        private String resultDescription;

        /**
         * Returned only when transaction is successfuly processed.
         */
        @JsonProperty("CallbackMetadata")
        private Metadata metadata;

        public String getCheckoutRequestId() {
            return checkoutRequestId;
        }

        public BodyContent setCheckoutRequestId(String checkoutRequestId) {
            this.checkoutRequestId = checkoutRequestId;
            return this;
        }

        public String getResultCode() {
            return resultCode;
        }

        public BodyContent setResultCode(String resultCode) {
            this.resultCode = resultCode;
            return this;
        }

        public String getResultDescription() {
            return resultDescription;
        }

        public BodyContent setResultDescription(String resultDescription) {
            this.resultDescription = resultDescription;
            return this;
        }

        public Metadata getMetadata() {
            return metadata;
        }

        public BodyContent setMetadata(Metadata metadata) {
            this.metadata = metadata;
            return this;
        }

        @Override
        public String toString() {
            return (
                "BodyContent{" +
                "checkoutRequestId='" +
                checkoutRequestId +
                '\'' +
                ", resultCode='" +
                resultCode +
                '\'' +
                ", resultDescription='" +
                resultDescription +
                '\'' +
                ", metadata=" +
                metadata +
                '}'
            );
        }
    }

    /**
     * This is the JSON object that holds more details for the transaction. It is only returned for Successful transaction.
     */
    public static class Metadata {

        /**
         * This is a JSON Array, within the CallbackMetadata, that holds additional transaction details in JSON objects.
         * Since this array is returned under the CallbackMetadata, it is only returned for Successful transaction.
         */
        @JsonProperty("Item")
        private List<Item> items;

        public List<Item> getItems() {
            return items;
        }

        public Metadata setItems(List<Item> items) {
            this.items = items;
            return this;
        }

        public Optional<String> findValue(String name) {
            return items.stream().filter(item -> item.name.equals(name)).map(Item::getValue).findFirst();
        }

        @Override
        public String toString() {
            return "Metadata{" + "items=" + items + '}';
        }
    }

    /**
     * holds additional transaction details it is only returned for Successful transaction.
     */
    public static class Item {

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Value")
        private String value;

        public String getName() {
            return name;
        }

        public Item setName(String name) {
            this.name = name;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Item setValue(String value) {
            this.value = value;
            return this;
        }

        @Override
        public String toString() {
            return "Item{" + "name='" + name + '\'' + ", value='" + value + '\'' + '}';
        }
    }
}
