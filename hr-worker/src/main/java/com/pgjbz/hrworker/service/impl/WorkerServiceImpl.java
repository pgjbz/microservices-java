package com.pgjbz.hrworker.service.impl;

import com.pgjbz.hrworker.model.Worker;
import com.pgjbz.hrworker.repository.WorkerRepository;
import com.pgjbz.hrworker.service.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;

    @Override
    public Iterable<Worker> findAll(Pageable pageable) {
        log.info("Searching workers from {}", pageable);
        Page<Worker> workerPage = workerRepository.findAll(pageable);
        if(workerPage.isEmpty()) {
            log.info("Workers from {} not found", pageable);
            throw new NoResultException("No worker founded");
        }
        return workerPage;
    }

    @Override
    public Worker findById(Long id) {
        log.info("Searching worker by id {}", id);
        return workerRepository.findById(id).orElseThrow(() -> {
            log.info("No worker founded by id {}", id);
            return new NoResultException(String.format("No worker founded by id %s", id));
        });
    }
}
