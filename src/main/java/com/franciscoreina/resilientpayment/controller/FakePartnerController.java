package com.franciscoreina.resilientpayment.controller;

import com.franciscoreina.resilientpayment.dto.PartnerTransferRequest;
import com.franciscoreina.resilientpayment.dto.PartnerTransferResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/partner")
public class FakePartnerController {

    private final Random random = new Random();

    @PostMapping("/send")
    public PartnerTransferResponse send(@RequestBody PartnerTransferRequest request) throws InterruptedException {
        log.info("Partner endpoint called");
        int scenario = random.nextInt(10);

        if (scenario <= 4) {
            log.info("Scenario: Success");
            return new PartnerTransferResponse(
                    UUID.randomUUID().toString(),
                    "SUCCESS",
                    "Transfer processed successfully by partner"
            );
        }

        if (scenario <= 6) {
            log.info("Scenario: Sleep");
            Thread.sleep(5000);
            return new PartnerTransferResponse(
                    UUID.randomUUID().toString(),
                    "SUCCESS",
                    "Transfer processed successfully by partner"
            );
        }

        log.info("Scenario: Throw exception");
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Partner service failed"
        );
    }
}
