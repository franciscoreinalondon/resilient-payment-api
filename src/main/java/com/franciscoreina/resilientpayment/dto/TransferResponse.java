package com.franciscoreina.resilientpayment.dto;

public record TransferResponse(
        String transferId,
        String status,
        String message
) {
}
