package com.github.avinashkris9.springboot.api.template.service;

import com.github.avinashkris9.springboot.api.template.entity.APIEntity;
import com.github.avinashkris9.springboot.api.template.exception.EntityNotFoundException;
import com.github.avinashkris9.springboot.api.template.model.APIResponse;
import com.github.avinashkris9.springboot.api.template.model.APIResponses;
import com.github.avinashkris9.springboot.api.template.repository.APIRepository;
import com.github.avinashkris9.springboot.api.template.util.CustomObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
/*
Service class for all business functions

 */
public class APIService {

    private final Logger logger = LoggerFactory.getLogger(APIService.class);
    // apiRepository reference for db operations. autowire by constructor.
    private final APIRepository apiRepository;
    private final CustomObjectMapper customObjectMapper;

    public APIService(APIRepository apiRepository, CustomObjectMapper customObjectMapper) {
        this.apiRepository = apiRepository;
        this.customObjectMapper=customObjectMapper;
    }

    /*
         Retrieve all entities objects from repository
         @return APIResponses object
        @throws En
         */
    public APIResponses getDetails()
    {
       List<APIEntity> apiEntityList=apiRepository.findAll();

       if(apiEntityList.isEmpty())
       {
           throw new EntityNotFoundException("Not found");
       }

      //convert entity object to dto object

        List<APIResponse>apiResponseList=
                apiEntityList.stream().
                map(customObjectMapper::mapAPIEntity2APIResponseDTO).
                collect(Collectors.toList());
        return new APIResponses(apiResponseList);
    }

    public  void getDetailsById()
    {


    }

    public void insertDetails()
    {

    }

    public void updateDetails()
    {

    }

    public void deleteDetails()
    {

    }
}
