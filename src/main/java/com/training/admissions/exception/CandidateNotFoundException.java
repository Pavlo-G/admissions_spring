package com.training.admissions.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(String message) {
        super(message);
    }
}
