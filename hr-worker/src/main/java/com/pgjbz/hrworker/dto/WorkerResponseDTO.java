package com.pgjbz.hrworker.dto;

import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public class WorkerResponseDTO {

    private final Long id;
    private final String name;
    private final Double dailyIncome;

    public WorkerResponseDTO(Long id, String name, Double dailyIncome) {
        this.id = requireNonNull(id, "id is mandatory");
        this.name = requireNonNull(name, "name is mandatory");
        this.dailyIncome = requireNonNull(dailyIncome, "daily income is mandatory");
    }
}
