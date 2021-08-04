package com.pgjbz.hrworker.repository;

import com.pgjbz.hrworker.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
