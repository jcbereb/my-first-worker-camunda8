package com.camunda.worker_scoring;

import io.camunda.spring.client.annotation.JobWorker;
import io.camunda.spring.client.annotation.VariablesAsType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WorkerNivelScoring {

    private static final Logger LOG = LoggerFactory.getLogger(WorkerNivelScoring.class);
    private static final String JOB_TYPE = "DeterminarNivelScoring";

   @JobWorker(type = JOB_TYPE , autoComplete = true)
   public Map<String, Object> handleWorkerNivelScoring(@VariablesAsType ScoringInput input){

       String nivelScoring;

       int interacciones = input.interacciones() != null? input.interacciones() : 0;
       String userName = input.userName();

       if (interacciones>=10){
           nivelScoring = "Decision";
       } else if (interacciones>=5) {
           nivelScoring = "Consideracion";
       } else {
           nivelScoring = "Descubrimiento";
       }

       LOG.info("Scoring del usuario {}. Resultado {} interacciones ", userName, interacciones);

       return Map.of("Nivel del Scoring", (Object) nivelScoring);
   }
}