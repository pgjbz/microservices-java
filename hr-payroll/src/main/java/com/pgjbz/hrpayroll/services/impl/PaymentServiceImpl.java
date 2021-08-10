package com.pgjbz.hrpayroll.services.impl;

import com.pgjbz.hrpayroll.feignclient.WorkerFeignClient;
import com.pgjbz.hrpayroll.model.Payment;
import com.pgjbz.hrpayroll.model.Worker;
import com.pgjbz.hrpayroll.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final WorkerFeignClient workerFeignClient;

    @Override
    public Payment getPayment(long workerId, int days) {
        log.info("Performing request for worker id {}", workerId);
        ResponseEntity<Worker> workerResponse = workerFeignClient.findWorkerById(workerId);
        log.info("Response {}", workerResponse);
        Worker worker = workerResponse.getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

}
