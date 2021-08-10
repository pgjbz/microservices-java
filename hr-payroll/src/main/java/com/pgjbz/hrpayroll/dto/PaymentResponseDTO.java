package com.pgjbz.hrpayroll.dto;

import lombok.Getter;
import org.springframework.lang.NonNull;

import static java.util.Objects.requireNonNull;

@Getter
public class PaymentResponseDTO {

    private final String name;
    private final Double dailyIncome;
    private final Integer days;

    public PaymentResponseDTO(@NonNull String name, @NonNull Double dailyIncome, @NonNull Integer days) {
        this.name = requireNonNull(name, "Name is mandatory");
        this.dailyIncome = requireNonNull(dailyIncome, "Daily incoming is mandatory");
        this.days = requireNonNull(days, "Days is mandatory");
    }

    public Double getTotal() {
        return this.dailyIncome * this.days;
    }
}
