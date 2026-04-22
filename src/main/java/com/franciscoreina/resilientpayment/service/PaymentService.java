package com.franciscoreina.resilientpayment.service;

import com.franciscoreina.resilientpayment.dto.TransferRequest;
import com.franciscoreina.resilientpayment.dto.TransferResponse;

public interface PaymentService {

    TransferResponse createTransfer(TransferRequest request);

}
