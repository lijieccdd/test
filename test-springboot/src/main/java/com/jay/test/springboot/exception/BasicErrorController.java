/*
package com.jay.test.springboot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
    @RequestMapping("${server.error.path:${error.path:/error}}")
    public class BasicErrorController extends AbstractErrorController {
        private final ErrorProperties errorProperties;
        private static final Logger LOGGER = LoggerFactory.getLogger(BasicErrorController.class);
        @Autowired
        private ApplicationContext applicationContext;

        */
/**
         * Create a new {@link org.springframework.boot.autoconfigure.web.BasicErrorController} instance.
         *
         * @param errorAttributes the error attributes
         * @param errorProperties configuration properties
         *//*

        public BasicErrorController(ErrorAttributes errorAttributes,
                                    ErrorProperties errorProperties) {
            this(errorAttributes, errorProperties,
                    Collections.<ErrorViewResolver>emptyList());
        }

        */
/**
         * Create a new {@link org.springframework.boot.autoconfigure.web.BasicErrorController} instance.
         *
         * @param errorAttributes    the error attributes
         * @param errorProperties    configuration properties
         * @param errorViewResolvers error view resolvers
         *//*

        public BasicErrorController(ErrorAttributes errorAttributes,
                                    ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
            super(errorAttributes, errorViewResolvers);
            Assert.notNull(errorProperties, "ErrorProperties must not be null");
            this.errorProperties = errorProperties;
        }

        @Override
        public String getErrorPath() {
            return this.errorProperties.getPath();
        }

        @RequestMapping(produces = "text/html")
        public ModelAndView errorHtml(HttpServletRequest request,
                                      HttpServletResponse response) {
            HttpStatus status = getStatus(request);
            Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
                    request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
            response.setStatus(status.value());
            ModelAndView modelAndView = resolveErrorView(request, response, status, model);
            insertError(request);
            return modelAndView == null ? new ModelAndView("error", model) : modelAndView;
        }

    private void insertError(HttpServletRequest request) {
    }


    @RequestMapping
        @ResponseBody
        public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
            Map<String, Object> body = getErrorAttributes(request,
                    isIncludeStackTrace(request, MediaType.ALL));
            HttpStatus status = getStatus(request);
            insertError(request);
            return new ResponseEntity(body, status);
        }

        */
/**
         * Determine if the stacktrace attribute should be included.
         *
         * @param request  the source request
         * @param produces the media type produced (or {@code MediaType.ALL})
         * @return if the stacktrace attribute should be included
         *//*

        protected boolean isIncludeStackTrace(HttpServletRequest request,
                                              MediaType produces) {
            ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
            if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
                return true;
            }
            if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
                return getTraceParameter(request);
            }
            return false;
        }

        */
/**
         * Provide access to the error properties.
         *
         * @return the error properties
         *//*

        protected ErrorProperties getErrorProperties() {
            return this.errorProperties;
        }
    }*/
