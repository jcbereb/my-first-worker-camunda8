package com.camunda.worker_scoring;

public record  ScoringInput (
    String userName,
    Integer interacciones
) {
}