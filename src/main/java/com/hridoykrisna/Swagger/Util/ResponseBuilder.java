package com.hridoykrisna.Swagger.Util;

import com.hridoykrisna.Swagger.DTO.ErrorResponse;
import com.hridoykrisna.Swagger.DTO.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ResponseBuilder {
    private ResponseBuilder() {
    }

    private static List<ErrorResponse> getCustomError(BindingResult bindingResult) {
        List<ErrorResponse> dtoList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            ErrorResponse errorResponseDTO = ErrorResponse.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            dtoList.add(errorResponseDTO);
        });
        return dtoList;
    }

    public static Response getFailureMessage(BindingResult result, String message) {
        return Response.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorResponseDTO(getCustomError(result))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getFailureMessage(HttpStatus status, String message) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessMessage(HttpStatus status, String message) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessMessage(HttpStatus status, String message, Object content) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessMessage(HttpStatus status, String message, Object content, int numberOfElement) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .numberOfElement(numberOfElement)
                .timestamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessMessage(HttpStatus status, String message, Object content, int numberOfElement, int rowCount) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .content(content)
                .statusCode(status.value())
                .numberOfElement(numberOfElement)
                .rowCount(rowCount)
                .timestamp(new Date().getTime())
                .build();
    }
}
