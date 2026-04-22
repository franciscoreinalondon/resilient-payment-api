package com.franciscoreina.resilientpayment.controller;

import com.franciscoreina.resilientpayment.dto.PartnerTransferRequest;
import com.franciscoreina.resilientpayment.dto.PartnerTransferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/partner")
public class FakePartnerController {

    private final Random random = new Random();

    @PostMapping("/send")
    public PartnerTransferResponse send(@RequestBody PartnerTransferRequest request) throws InterruptedException {
        int scenario = random.nextInt(10);

        if (scenario <= 4) {
            return new PartnerTransferResponse(
                    UUID.randomUUID().toString(),
                    "SUCCESS",
                    "Transfer processed successfully by partner"
            );
        }

        if (scenario <= 6) {
            Thread.sleep(5000);
            return new PartnerTransferResponse(
                    UUID.randomUUID().toString(),
                    "SUCCESS",
                    "Transfer processed successfully by partner"
            );
        }

        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Partner service failed"
        );
    }
}
