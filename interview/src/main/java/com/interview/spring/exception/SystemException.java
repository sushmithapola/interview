
package com.interview.spring.exception;
import com.interview.spring.enumeration.ErrorCodeEnum;

/**
 * System Exception Class is the custom Exception class
 * @author Sushmitha
 */
public class SystemException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -92251001517057782L;
    private final ErrorCodeEnum errorCodeEnum;

    /**
     * Constructor that takes just the error code enumeration as argument.
     *
     * @param errorCodeEnum error code enum
     */
    public SystemException(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }

    /**
     * Overloaded Constructor that takes the error code enumeration and Throwable root cause as arguments.
     *
     * @param errorCodeEnum error code enum
     * @param rootCause throwable root cause
     */
    public SystemException(ErrorCodeEnum errorCodeEnum, Throwable rootCause) {
        super(rootCause);
        this.errorCodeEnum = errorCodeEnum;
    }

    /**
     * Gets the error code enum value for the exception object instance
     *
     * @return error code enum
     */
    public ErrorCodeEnum getErrorCode() {
        return errorCodeEnum;
    }

    /**
     * Gets the error message from code enum
     *
     * @return message
     */
    public String getErrorMessage() {
        return errorCodeEnum.value();
    }

}
