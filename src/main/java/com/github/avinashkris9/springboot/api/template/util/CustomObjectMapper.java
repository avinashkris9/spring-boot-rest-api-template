package com.github.avinashkris9.springboot.api.template.util;

import com.github.avinashkris9.springboot.api.template.entity.APIEntity;
import com.github.avinashkris9.springboot.api.template.model.APIResponse;
import org.springframework.stereotype.Component;

/*
Utility class for all model object mapper from entity to dto and vice versa.
 */
@Component
public class CustomObjectMapper {

    /*
    Map DAO to DTO
    @param APIEntity object
    @return APIResponse object

     */
    public APIResponse mapAPIEntity2APIResponseDTO(APIEntity apiEntity)
    {
        APIResponse apiResponse=new APIResponse();
        apiResponse.setField1(apiEntity.getField1());

        return apiResponse;
    }
    /*

    Map DTO object to DAO
     */

    public  APIEntity mapAPIResponseDTO2APIEntity(APIResponse apiResponse)
    {
        APIEntity apiEntity=new APIEntity();
        apiEntity.setField1(apiResponse.getField1());
        return apiEntity;
    }


}
