package com.github.avinashkris9.springboot.api.template.model;

import java.util.List;
/*
Bean class to map a list of objects to parse as JSON output
 */
public class APIResponses {

    private List<APIResponse> apiResponseList;

    public APIResponses(List<APIResponse> apiResponseList) {
        this.apiResponseList = apiResponseList;
    }

    public APIResponses() {
    }

    public List<APIResponse> getApiResponseList() {
        return apiResponseList;
    }

    public void setApiResponseList(List<APIResponse> apiResponseList) {
        this.apiResponseList = apiResponseList;
    }
}
