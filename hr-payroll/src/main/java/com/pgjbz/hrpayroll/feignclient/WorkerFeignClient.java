package com.pgjbz.hrpayroll.feignclient;

import com.pgjbz.hrpayroll.model.Worker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RefreshScope
@FeignClient(name = "${services.hr-worker.name}",  path = "${services.hr-worker.path}")
public interface WorkerFeignClient {

    @GetMapping(value = "/{workerId}")
    ResponseEntity<Worker> findWorkerById(@PathVariable(value = "workerId") Long id);

}
