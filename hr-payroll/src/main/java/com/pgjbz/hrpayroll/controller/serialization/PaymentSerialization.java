package com.pgjbz.hrpayroll.controller.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pgjbz.hrpayroll.dto.PaymentResponseDTO;
import com.pgjbz.hrpayroll.model.Payment;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PaymentSerialization extends JsonSerializer<Payment> {

    @Override
    public void serialize(Payment payment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        PaymentResponseDTO paymentResponse = payment.toDto();
        jsonGenerator.writeObject(paymentResponse);
    }

}
