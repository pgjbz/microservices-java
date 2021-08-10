package com.pgjbz.hrpayroll.services;

import com.pgjbz.hrpayroll.model.Payment;

public interface PaymentService {

    Payment getPayment(long workerId, int days);
}
