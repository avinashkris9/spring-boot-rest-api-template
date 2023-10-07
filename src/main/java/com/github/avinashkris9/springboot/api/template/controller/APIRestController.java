package com.github.avinashkris9.springboot.api.template.controller;

import com.github.avinashkris9.springboot.api.template.model.APIResponse;
import com.github.avinashkris9.springboot.api.template.model.APIResponses;
import com.github.avinashkris9.springboot.api.template.service.APIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Rest Controller class for API End points.

 */

@RestController
public class APIRestController {

    private final Logger logger = LoggerFactory.getLogger(APIRestController.class);

    private final APIService apiService;

    public APIRestController(APIService apiService) {
        this.apiService = apiService;
    }

    /*
    API Controller to map GET request end point
    @return APIResponse object
    Exceptions handled at ControllerAdvice
     */
    @GetMapping()
    public APIResponses getAllDetails()
    {

        return  apiService.getDetails();
    }
}
