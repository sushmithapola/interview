package com.interview.spring.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.interview.spring.logging.service.LoggingService;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_RESPONSE = "error response:";

    @Autowired
    private LoggingService loggingService;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequest(BadRequestException ex, WebRequest request) {
        loggingService.error(ERROR_RESPONSE + "Bad Request", ex);
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSystemMessage(StringUtils.isEmpty(ex.getMessage()) ? ex.getErrorMessage() : ex.getMessage());
        errorResponse.setUserMessage(ex.getErrorMessage());
        errorResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        loggingService.error(ERROR_RESPONSE + "Generic Error", ex);
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSystemMessage(ex.getMessage());
        errorResponse.setUserMessage(ex.getMessage());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorResponse> noDataFoundExceptions(NoDataFoundException ex, WebRequest request) {
        loggingService.error(ERROR_RESPONSE + "No Data Found Error", ex);
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSystemMessage(StringUtils.isEmpty(ex.getMessage()) ? ex.getErrorMessage() : ex.getMessage());
        errorResponse.setUserMessage(ex.getErrorMessage());
        errorResponse.setCode(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BackEndSystemException.class)
    public ResponseEntity<ErrorResponse> backEndSystemException(BackEndSystemException ex, WebRequest request) {
        loggingService.error(ERROR_RESPONSE + "Back End returned error", ex);
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSystemMessage(StringUtils.isEmpty(ex.getMessage()) ? ex.getErrorMessage() : ex.getMessage());
        errorResponse.setUserMessage(ex.getErrorMessage());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorResponse> systemException(SystemException ex, WebRequest request) {
        loggingService.error(ERROR_RESPONSE + "Internal Error", ex);
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSystemMessage(StringUtils.isEmpty(ex.getMessage()) ? ex.getErrorMessage() : ex.getMessage());
        errorResponse.setUserMessage(ex.getErrorMessage());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
