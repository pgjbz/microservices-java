package com.pgjbz.hrworker.service;

import com.pgjbz.hrworker.model.Worker;
import org.springframework.data.domain.Pageable;

public interface WorkerService {

    Iterable<Worker> findAll(Pageable pageable);

    Worker findById(Long id);
}
