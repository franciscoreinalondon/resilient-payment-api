package com.franciscoreina.resilientpayment.dto;

public record PartnerTransferResponse(
        String partnerReference,
        String status,
        String message
) {
}
