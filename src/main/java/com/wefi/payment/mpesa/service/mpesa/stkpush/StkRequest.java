package ke.co.webwire.turaco.service.mpesa.stk;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://developer.safaricom.co.ke/lipa-na-m-pesa-online/apis/post/stkpush/v1/processrequest
 */
public class StkRequest {

    /**
     *This is organizations shortcode (Paybill or Buygoods - A 5 to 6 digit account number) used to identify an organization and receive the transaction.
     */
    @JsonProperty("BusinessShortCode")
    private String shortCode;

    /**
     * This is the password used for encrypting the request sent: A base64 encoded string.
     * (The base64 string is a combination of Shortcode+Passkey+Timestamp)
     */
    @JsonProperty("Password")
    private String password;

    /**
     * This is the Timestamp of the transaction, normaly in the formart of YEAR+MONTH+DATE+HOUR+MINUTE+SECOND (YYYYMMDDHHMMSS)
     * Each part should be at least two digits apart from the year which takes four digits.
     */
    @JsonProperty("Timestamp")
    private String timestamp;

    /**
     * This is the transaction type that is used to identify the transaction when sending the request to M-Pesa.
     * The transaction type for M-Pesa Express is "CustomerPayBillOnline"
     */
    @JsonProperty("TransactionType")
    private String transactionType;

    /**
     * This is the Amount transacted normaly a numeric value.
     * Money that customer pays to the Shorcode. Only whole numbers are supported.
     */
    @JsonProperty("Amount")
    private int amount;

    /**
     * The phone number sending money.
     * The parameter expected is a Valid Safaricom Mobile Number that is M-Pesa registered in the format 2547XXXXXXXX
     */
    @JsonProperty("PartyA")
    private String partyA;

    /**
     * The organization receiving the funds.
     * The parameter expected is a 5 to 6 digit as defined on the Shortcode description above.
     * This can be the same as BusinessShortCode value above.
     */
    @JsonProperty("PartyB")
    private String partyB;

    /**
     * The Mobile Number to receive the STK Pin Prompt. This number can be the same as PartyA value above.
     */
    @JsonProperty("PhoneNumber")
    private String phoneNumber;

    /**
     * A CallBack URL is a valid secure URL that is used to receive notifications from M-Pesa API.
     * It is the endpoint to which the results will be sent by M-Pesa API.
     */
    @JsonProperty("CallBackURL")
    private String callbackURL;

    /**
     * Account Reference:
     * This is an Alpha-Numeric parameter that is defined by your system as an Identifier of the transaction for CustomerPayBillOnline transaction type.
     * Along with the business name, this value is also displayed to the customer in the STK Pin Prompt message.
     * Maximum of 12 characters.
     */
    @JsonProperty("AccountReference")
    private String accountReference;

    /**
     * This is any additional information/comment that can be sent along with the request from your system.
     * Maximum of 13 Characters.
     */
    @JsonProperty("TransactionDesc")
    private String transactionDescription;

    public String getShortCode() {
        return shortCode;
    }

    public StkRequest setShortCode(String shortCode) {
        this.shortCode = shortCode;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public StkRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public StkRequest setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public StkRequest setTransactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public StkRequest setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getPartyA() {
        return partyA;
    }

    public StkRequest setPartyA(String partyA) {
        this.partyA = partyA;
        return this;
    }

    public String getPartyB() {
        return partyB;
    }

    public StkRequest setPartyB(String partyB) {
        this.partyB = partyB;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public StkRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCallbackURL() {
        return callbackURL;
    }

    public StkRequest setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
        return this;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public StkRequest setAccountReference(String accountReference) {
        this.accountReference = accountReference;
        return this;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public StkRequest setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
        return this;
    }

    @Override
    public String toString() {
        return (
            "StkRequest{" +
            "shortCode='" +
            shortCode +
            '\'' +
            ", password='" +
            password +
            '\'' +
            ", timestamp='" +
            timestamp +
            '\'' +
            ", transactionType='" +
            transactionType +
            '\'' +
            ", amount=" +
            amount +
            ", partyA='" +
            partyA +
            '\'' +
            ", partyB='" +
            partyB +
            '\'' +
            ", phoneNumber='" +
            phoneNumber +
            '\'' +
            ", callbackURL='" +
            callbackURL +
            '\'' +
            ", accountReference='" +
            accountReference +
            '\'' +
            ", transactionDescription='" +
            transactionDescription +
            '\'' +
            '}'
        );
    }
}
