
package com.interview.spring.logging.service;

/**
 * The LoggingService is interface with methods to log customized logger messages
 *
 * @author Sushmitha
 *
 */
public interface LoggingService {

    /**
     * Method to log debug message
     *
     * @param message
     */
    public void debug(String message);

    /**
     * Method to log error message
     *
     * @param message
     */
    public void error(String message);

    /**
     * Method to log error message with exception details
     *
     * @param message
     */
    public void error(String message, Throwable t);

    /**
     * Method to log error message with exception details
     *
     * @param message
     */
    public void error(Throwable t);

    /**
     * Method to log info message
     *
     * @param message
     */
    public void info(String message);
    

    /**
     * Method to log warn message
     *
     * @param message
     */
    public void warn(String message);
}
