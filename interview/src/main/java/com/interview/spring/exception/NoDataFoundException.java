
package com.interview.spring.exception;

import com.interview.spring.enumeration.ErrorCodeEnum;

/**
 * The NoDataFoundException is the Exception class to throw if soap response returns error code
 *
 * @author Sushmitha
 *
 */
public class NoDataFoundException extends SystemException {
    /**
     * 
     */
    private static final long serialVersionUID = -92251001517057782L;

    /**
     * Constructor that takes just the error code enumeration as argument.
     *
     * @param errorCodeEnum error code enum
     */
    public NoDataFoundException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    /**
     * Constructor that takes just the error code enumeration as argument.
     *
     * @param errorCodeEnum error code enum
     * @param Throwable
     */
    public NoDataFoundException(ErrorCodeEnum errorCodeEnum, Throwable rootCause) {
        super(errorCodeEnum, rootCause);
    }
}
