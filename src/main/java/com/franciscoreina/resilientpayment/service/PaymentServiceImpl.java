package com.franciscoreina.resilientpayment.service;

import com.franciscoreina.resilientpayment.client.PartnerClient;
import com.franciscoreina.resilientpayment.dto.PartnerTransferRequest;
import com.franciscoreina.resilientpayment.dto.PartnerTransferResponse;
import com.franciscoreina.resilientpayment.dto.TransferRequest;
import com.franciscoreina.resilientpayment.dto.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PartnerClient partnerClient;

    @Override
    public TransferResponse createTransfer(TransferRequest request) {
        String transferId = UUID.randomUUID().toString();

        PartnerTransferRequest partnerRequest = new PartnerTransferRequest(
                transferId,
                request.recipientAccount(),
                request.amount(),
                request.destinationCountry()
        );

        PartnerTransferResponse partnerResponse = partnerClient.sendMoney(partnerRequest);

        return new TransferResponse(
                transferId,
                partnerResponse.status(),
                partnerResponse.message()
        );
    }
}
