
package com.interview.spring.exception;

import lombok.Data;

/**
 * The ErrorResponse is Model class to hold Error info
 *
 * @author Sushmitha
 *
 */
@Data
public class ErrorResponse {
    /*
     * property for errorCode
     */
    private String code;
    /*
     * property for custom user defined message
     */
    private String userMessage;
    /*
     * property for custom system message
     */
    private String systemMessage;
    /*
     * property for detail link information
     */
    private String detailLink;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public String getSystemMessage() {
		return systemMessage;
	}
	public void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}
	public String getDetailLink() {
		return detailLink;
	}
	public void setDetailLink(String detailLink) {
		this.detailLink = detailLink;
	}
    
    
    
}

