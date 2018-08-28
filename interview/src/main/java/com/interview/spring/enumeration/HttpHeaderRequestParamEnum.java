
package com.interview.spring.enumeration;

/**
 * HttpHeaderRequestParamEnum is the enumeration class to hold constants
 *
 * @author Sushmitha
 *
 */
public enum HttpHeaderRequestParamEnum {
    APPLICATION_USER_ID("applicationUserId"),
    SESSION_ID("sessionid"),
    APPLICATION_ID("applicationid"),
    SENDER_ID("senderid");

    private String value;

    HttpHeaderRequestParamEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
