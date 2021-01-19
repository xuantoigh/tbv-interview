package com.interview.tbv.controller.exception;

import com.interview.tbv.utility.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@ControllerAdvice
@Slf4j
public class ExceptionTranslator {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Map<String, Object> processException(NullPointerException ex) {
        log.error("NullPointerException  " + ex);
        return Utilities.generateErrorResponse(ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Map<String, Object>  processException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException  " + ex);
        return Utilities.generateErrorResponse(ex);
    }

}
