package com.pgjbz.hrworker.controller;

import com.pgjbz.hrworker.model.Worker;
import com.pgjbz.hrworker.service.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/workers")
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping
    public ResponseEntity<Iterable<Worker>> findPage(Pageable pageable, HttpServletRequest request){
        log.info("Receiving request {} from ip {}", pageable, request.getRemoteAddr());
        return ResponseEntity.ok(workerService.findAll(pageable));
    }

    @GetMapping(value = "/{workerId}")
    public ResponseEntity<Worker> findWorkerById(@PathVariable(value = "workerId") Long id, HttpServletRequest request) {
        log.info("Receiving request to search worker by id{} from ip {}", id, request.getRemoteAddr());
        return ResponseEntity.ok(workerService.findById(id));
    }

}
