package com.franciscoreina.resilientpayment.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CircuitBreakerListener {

    private final CircuitBreakerRegistry registry;

    @PostConstruct
    public void registerEvents() {
        CircuitBreaker cb = registry.circuitBreaker("partnerClient");
        cb.getEventPublisher()
                .onStateTransition(event -> {
                    log.warn("CircuitBreaker state transition: {} -> {}",
                            event.getStateTransition().getFromState(),
                            event.getStateTransition().getToState());
                })
                .onCallNotPermitted(event ->
                        log.warn("Call blocked because CircuitBreaker is OPEN")
                );
    }
}
