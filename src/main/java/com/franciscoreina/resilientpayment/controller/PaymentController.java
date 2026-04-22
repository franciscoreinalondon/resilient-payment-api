package com.franciscoreina.resilientpayment.controller;

import com.franciscoreina.resilientpayment.dto.TransferRequest;
import com.franciscoreina.resilientpayment.dto.TransferResponse;
import com.franciscoreina.resilientpayment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transfers")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public TransferResponse createTransfer(@Valid @RequestBody TransferRequest request) {
        return paymentService.createTransfer(request);
    }

}
