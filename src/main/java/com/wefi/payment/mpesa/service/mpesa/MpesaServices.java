package com.wefi.payment.mpesa.service.mpesa;

import com.wefi.payment.mpesa.service.mpesa.stkpush.StkRequest;
import com.wefi.payment.mpesa.service.mpesa.stkpush.StkResponse;
import java.time.Clock;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MpesaServicesFind {

    private static final Pattern PATTERN_DECIMAL_NUMBER = Pattern.compile("^[0-9]+(\\.[0-9]+)?$");

    private final WebClient webClient;
    private final Clock clock = Clock.systemDefaultZone();

    private static final Logger log = LoggerFactory.getLogger(MpesaServicesFind.class);

    public MpesaServicesFind(WebClient mpesaWebClient) {
        this.webClient = mpesaWebClient;
    }

    public Mono<StkResponse> stkPush() {
        final String timestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(clock.getZone()).format(Instant.now(clock));
        final int shortcode = 174379;
        final String passkey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
        final String password = Base64.getEncoder().encodeToString((shortcode + passkey + timestamp).getBytes());
        StkRequest stkRequest = new StkRequest()
            .setShortCode(String.valueOf(shortcode))
            .setPassword(password)
            .setTimestamp(timestamp)
            .setTransactionType("CustomerBuyGoodsOnline")
            .setAmount(1)
            .setPartyA("254722268163")
            .setPartyB(String.valueOf(shortcode))
            .setPhoneNumber("254722268163")
            .setCallbackURL("http://localhost.com/api/mpesa-callbacks")
            .setAccountReference(timestamp)
            .setTransactionDescription("Nothing");

        return webClient
            .post()
            .uri("mpesa/stkpush/v1/processrequest")
            .bodyValue(stkRequest)
            .retrieve()
            .bodyToMono(StkResponse.class)
            .doOnSuccess(stkResponse -> log.info("response from stkPush: {}", stkResponse));
    }
}
