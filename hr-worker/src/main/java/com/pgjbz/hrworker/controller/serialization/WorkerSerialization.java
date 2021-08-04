package com.pgjbz.hrworker.controller.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pgjbz.hrworker.dto.WorkerResponseDTO;
import com.pgjbz.hrworker.model.Worker;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class WorkerSerialization extends JsonSerializer<Worker> {

    @Override
    public void serialize(Worker worker, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        WorkerResponseDTO workerResponse = worker.toDto();
        jsonGenerator.writeObject(workerResponse);
    }

}
