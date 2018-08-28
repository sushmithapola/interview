
package com.interview.spring.exception;

import com.interview.spring.enumeration.ErrorCodeEnum;

/**
 * The BadRequestException is the Exception class to throw customized exception
 *
 * @author Sushmitha
 *
 */
public class BadRequestException extends SystemException {
    /**
     *
     */
    private static final long serialVersionUID = -3052798098161335125L;

    /**
     * Constructor that takes just the error code enumeration as argument.
     *
     * @param errorCodeEnum error code enum
     */
    public BadRequestException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public BadRequestException(ErrorCodeEnum errorCodeEnum, Throwable rootCause) {
        super(errorCodeEnum, rootCause);
    }
}
