package com.pgjbz.hrpayroll.services.impl;

import com.pgjbz.hrpayroll.model.Payment;
import com.pgjbz.hrpayroll.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public Payment getPayment(long workerId, int days) {
        log.info("Performing payment process for worker id {} and days {}", workerId, days);
        return new Payment("Alex", 200.0, days);
    }

}
