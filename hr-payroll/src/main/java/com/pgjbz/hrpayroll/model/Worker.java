package com.pgjbz.hrpayroll.model;

import lombok.Getter;
import lombok.ToString;

import static java.util.Objects.requireNonNull;

import io.github.resilience4j.core.lang.NonNull;

@Getter
@ToString
public class Worker {

    private final Long id;
    private final String name;
    private final Double dailyIncome;

    public Worker(@NonNull Long id, @NonNull String name, @NonNull Double dailyIncome) {
        this.id = requireNonNull(id, "Id is mandatory");
        this.name = requireNonNull(name, "Name is mandatory");
        this.dailyIncome = requireNonNull(dailyIncome, "Daily income is mandatory");
    }
}
