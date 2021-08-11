package com.pgjbz.hrpayroll.controller;

import com.pgjbz.hrpayroll.exception.RequestTimeOutException;
import com.pgjbz.hrpayroll.model.Payment;
import com.pgjbz.hrpayroll.services.PaymentService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final Resilience4JCircuitBreaker circuitBreaker;

    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable(value = "workerId") Long workerId,
                                                  @PathVariable(value = "days") Integer days,
                                                  HttpServletRequest request){

        Payment payment = circuitBreaker.run(() -> paymentService.getPayment(workerId, days),
                this::getPaymentAlternative);
        log.info("Receiving request [worker id - {}, days - {} from ip {}", workerId, days, request.getRemoteAddr());
        return ResponseEntity.ok(payment);
    }

    private Payment getPaymentAlternative(Throwable throwable) {
        if(throwable instanceof FeignException.NotFound)
            throw (RuntimeException)throwable;
        log.info("Error on perform payment request, using alternative method");
        throw new RequestTimeOutException("Worker service is Unavailable");
    }


}
