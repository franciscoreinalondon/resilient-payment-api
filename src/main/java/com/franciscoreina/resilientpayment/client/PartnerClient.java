package com.franciscoreina.resilientpayment.client;

import com.franciscoreina.resilientpayment.dto.PartnerTransferRequest;
import com.franciscoreina.resilientpayment.dto.PartnerTransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class PartnerClient {

    private final RestClient restClient;

    public PartnerTransferResponse sendMoney(PartnerTransferRequest request) {
        return restClient.post()
                .uri("/partner/send")
                .body(request)
                .retrieve()
                .body(PartnerTransferResponse.class);
    }

}
