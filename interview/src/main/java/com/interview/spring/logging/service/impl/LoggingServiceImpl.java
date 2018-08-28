package com.interview.spring.logging.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.interview.spring.enumeration.HttpHeaderRequestParamEnum;
import com.interview.spring.logging.service.LoggingService;

/**
 * The LoggingServiceImpl is the implementation class for Customer Logger class for the Service
 *
 * @author Sushmitha
 *
 */

@Service
public class LoggingServiceImpl implements LoggingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingServiceImpl.class);

    private static final String LOG_FORMAT = "requestHeader=%s, class-method=%s, message=%s";

    @Autowired private JmsTemplate jmsTemplate;
   
    @Autowired
    private HttpServletRequest servletRequest;

    private String callerClassAndMethodName(Thread currentThread) {
        final StackTraceElement[] stacktrace = currentThread.getStackTrace();
        return "className=" + stacktrace[3].getClassName() + " methodName=" + stacktrace[3].getMethodName();
    }

    @Override
    public void debug(String message) {
        String callerClassMethodName = null;
        try {
        	jmsTemplate.convertAndSend("OrderTransactionQueue", message);
            final Thread currentThread = Thread.currentThread();
            callerClassMethodName = callerClassAndMethodName(currentThread);
            final String logStatement = String.format(LOG_FORMAT, getBaseLogStatement(), callerClassMethodName, message);
            LOGGER.debug(logStatement);
        } catch (final Exception e) {
            writeToDefaultLogger(e);
        }
    }

    @Override
    public void error(String message) {
        String callerClassMethodName = null;
        try {
        	jmsTemplate.convertAndSend("OrderTransactionQueue", message);
        	final Thread currentThread = Thread.currentThread();
            callerClassMethodName = callerClassAndMethodName(currentThread);
            final String logStatement = String.format(LOG_FORMAT, getBaseLogStatement(), callerClassMethodName, message);
            LOGGER.error(logStatement);
        } catch (final Exception e) {
            writeToDefaultLogger(e);
        }
    }

    @Override
    public void error(String message, Throwable t) {
        String callerClassMethodName = null;
        try {
        	jmsTemplate.convertAndSend("OrderTransactionQueue", message);
            final Thread currentThread = Thread.currentThread();
            callerClassMethodName = callerClassAndMethodName(currentThread);
            final String logStatement = String.format(LOG_FORMAT, getBaseLogStatement(), callerClassMethodName, message);
            LOGGER.error(logStatement, t);
        } catch (final Exception e) {
            writeToDefaultLogger(e);
        }
    }

    @Override
    public void error(Throwable t) {
        String callerClassMethodName = null;
        try {
        	//jmsTemplate.convertAndSend(message);
            final Thread currentThread = Thread.currentThread();
            callerClassMethodName = callerClassAndMethodName(currentThread);
            final String logStatement = String.format("base=%s, class-method=%s", getBaseLogStatement(), callerClassMethodName);
            LOGGER.error(logStatement, t);
        } catch (final Exception e) {
            writeToDefaultLogger(e);
        }
    }

    @Override
    public void info(String message) {
        String callerClassMethodName = null;
        try {
        	jmsTemplate.convertAndSend("OrderTransactionQueue", message);
            final Thread currentThread = Thread.currentThread();
            callerClassMethodName = callerClassAndMethodName(currentThread);
            final String logStatement = String.format(LOG_FORMAT, getBaseLogStatement(), callerClassMethodName, message);
            LOGGER.info(logStatement);
            
        } catch (final Exception e) {
            writeToDefaultLogger(e);
        }
    }

  

    @Override
    public void warn(String message) {
        String callerClassMethodName = null;
        try {
        	jmsTemplate.convertAndSend("OrderTransactionQueue", message);
            final Thread currentThread = Thread.currentThread();
            callerClassMethodName = callerClassAndMethodName(currentThread);
            final String logStatement = String.format(LOG_FORMAT, getBaseLogStatement(), callerClassMethodName, message);
            LOGGER.warn(logStatement);
        } catch (final Exception e) {
            writeToDefaultLogger(e);
        }
    }

    private void writeToDefaultLogger(Exception e) {
        LOGGER.error(getBaseLogStatement(), e);
    }

    private String getBaseLogStatement() {

        final String applicationUserId = servletRequest.getHeader(HttpHeaderRequestParamEnum.APPLICATION_USER_ID.value());
        final String sessionid = servletRequest.getHeader(HttpHeaderRequestParamEnum.SESSION_ID.value());

        return String.format("Request headers are applicationUserId=%s,sessionid=%s", (applicationUserId==null? "" : applicationUserId), (sessionid==null?"" : sessionid));
    }

}