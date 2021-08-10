package com.pgjbz.hrpayroll.model;

import com.pgjbz.hrpayroll.dto.PaymentResponseDTO;
import lombok.Getter;
import org.springframework.lang.NonNull;

import static java.util.Objects.requireNonNull;

@Getter
public class Payment {

    private final String name;
    private final Double dailyIncome;
    private final Integer days;

    public Payment(@NonNull String name,@NonNull Double dailyIncome,@NonNull Integer days) {
        this.name = requireNonNull(name, "Name is mandatory");
        this.dailyIncome = requireNonNull(dailyIncome, "Daily incoming is mandatory");
        this.days = requireNonNull(days, "Days is mandatory");
    }

    public PaymentResponseDTO toDto(){
        return new PaymentResponseDTO(this.name, this.dailyIncome, this.days);
    }

}
