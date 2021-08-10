package com.pgjbz.hrpayroll.feignclient;

import com.pgjbz.hrpayroll.model.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "${services.hr-worker.name}",  path = "${services.hr-worker.path}")
public interface WorkerFeignClient {

    @GetMapping(value = "/{workerId}")
    ResponseEntity<Worker> findWorkerById(@PathVariable(value = "workerId") Long id);

}
