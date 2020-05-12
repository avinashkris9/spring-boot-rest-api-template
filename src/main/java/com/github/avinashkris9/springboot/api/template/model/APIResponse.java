package com.github.avinashkris9.springboot.api.template.model;
/*
Bean class for  parsing single object to JSON.
 */
public class APIResponse {

    private String field1;


    public APIResponse()
    {

    }
    public APIResponse(String field1) {
        this.field1 = field1;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }
}
