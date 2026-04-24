package com.franciscoreina.resilientpayment.client;

import com.franciscoreina.resilientpayment.dto.PartnerTransferRequest;
import com.franciscoreina.resilientpayment.dto.PartnerTransferResponse;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerClient {

    private final RestClient restClient;

    @Retry(name = "partnerClient", fallbackMethod = "sendMoneyFallback")
    public PartnerTransferResponse sendMoney(PartnerTransferRequest request) {
        log.info("Calling partner for transferId={}", request.transferId());

        return restClient.post()
                .uri("/partner/send")
                .body(request)
                .retrieve()
                .body(PartnerTransferResponse.class);
    }

    public PartnerTransferResponse sendMoneyFallback(PartnerTransferRequest request, Throwable throwable) {
        log.warn("Fallback triggered for transferId={}, reason={}", request.transferId(), throwable.toString());

        return new PartnerTransferResponse(
                UUID.randomUUID().toString(),
                "PENDING",
                "Partner unavailable. Transfer queued for manual review."
        );
    }
}
