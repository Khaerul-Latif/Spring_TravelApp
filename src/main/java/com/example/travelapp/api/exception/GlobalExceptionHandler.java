package com.example.travelapp.api.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        String message = ex.getMostSpecificCause().getMessage();
        if (message.contains("null value in column \"nama\"")) {
            errors.put("nama", "nama wajib diisi");
        } else if (message.contains("null value in column \"no_telp\"")) {
            errors.put("no_telp", "no_telp wajib diisi");
        } else if (message.contains("null value in column \"alamat\"")) {
            errors.put("alamat", "alamat wajib diisi");
        } else if (message.contains("null value in column \"no_polisi\"")) {
            errors.put("no_polisi", "no_polisi wajib diisi");
        } else if (message.contains("null value in column \"jenis_bus\"")) {
            errors.put("jenis_bus", "jenis_bus wajib diisi");
        } else if (message.contains("null value in column \"id_penumpang\"")) {
            errors.put("id_penumpang", "id_penumpang wajib diisi");
        } else if (message.contains("null value in column \"id_travel\"")) {
            errors.put("id_travel", "id_travel wajib diisi");
        } else if (message.contains("null value in column \"jadwal\"")) {
            errors.put("jadwal", "jadwal wajib diisi");
        } else {
            errors.put("error", "Terjadi kesalahan pada data yang dimasukkan");
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}