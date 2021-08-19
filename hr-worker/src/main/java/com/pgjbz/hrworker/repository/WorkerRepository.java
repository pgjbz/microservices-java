package com.pgjbz.hrworker.repository;

import com.pgjbz.hrworker.model.Worker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;

@RefreshScope
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
