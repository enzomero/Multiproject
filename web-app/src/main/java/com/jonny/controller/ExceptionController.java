package com.jonny.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(ResourceAccessException.class)
    public ModelAndView handleHttpServerErrorException(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "You have problems on service side");
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("exception");
        LOGGER.error("Throwing '" + ex.getClass().getName());
        LOGGER.error("Exception message:" + ex.getLocalizedMessage());
        return mav;
    }

    //@ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgumentException(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "You have bad parameter");
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("exception");
        return mav;
    }
}
