package com.franciscoreina.resilientpayment.dto;

import java.math.BigDecimal;

public record PartnerTransferRequest(
        String transferId,
        String recipientAccount,
        BigDecimal amount,
        String destinationCountry
) {
}
