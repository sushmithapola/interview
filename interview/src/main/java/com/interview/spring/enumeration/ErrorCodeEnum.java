
package com.interview.spring.enumeration;

/**
 * ErrorCodeEnum is the enumeration class to hold constants
 *
 * @author Sushmitha
 *
 */
public enum ErrorCodeEnum {
    GENERIC_ERROR("Error occured while executing service!"),
    GENERIC_ERROR_RESPONSE_NOT_AVAILABLE_SERVICE_FAILED("Application response is null/service call failed"),
    NO_DATA_FOUND_EXCEPTION("No data available from backend system."), // using
    ID_REQUEST_VALIDATION_EXCEPTION("User Id field(s) are mandatory."), // using
    LASTNAME_REQUEST_VALIDATION_EXCEPTION("User lastName field(s) are mandatory."), // using
    NAME_REQUEST_VALIDATION_EXCEPTION("User name field(s) are mandatory."), // using
    COUNTRY_REQUEST_VALIDATION_EXCEPTION("User country field(s) are mandatory."), // using
    REQUEST_VALIDATION_EXCEPTION("User field(s) are mandatory."); // using
 

    private final String value;

    ErrorCodeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
