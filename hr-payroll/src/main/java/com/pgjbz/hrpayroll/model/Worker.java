package com.pgjbz.hrpayroll.model;

import lombok.Getter;
import lombok.ToString;

import static java.util.Objects.requireNonNull;

@Getter
@ToString
public class Worker {

    private final Long id;
    private final String name;
    private final Double dailyIncome;

    public Worker(Long id, String name, Double dailyIncome) {
        this.id = requireNonNull(id, "Id is mandatory");
        this.name = requireNonNull(name, "Name is mandatory");
        this.dailyIncome = requireNonNull(dailyIncome, "Daily income is mandatory");
    }
}
