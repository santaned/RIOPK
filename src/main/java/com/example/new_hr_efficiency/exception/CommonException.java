package com.example.new_hr_efficiency.exception;

import com.example.new_hr_efficiency.service.Code;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class CommonException extends RuntimeException {

    private final Code code;
    private final String message;
    private final HttpStatus httpStatus;
}
