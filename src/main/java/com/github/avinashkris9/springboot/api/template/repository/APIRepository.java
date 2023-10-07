package com.github.avinashkris9.springboot.api.template.repository;

import com.github.avinashkris9.springboot.api.template.entity.APIEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Repository class for API Entity operations
 */
@Repository
public interface APIRepository  extends CrudRepository<APIEntity,Long> {

    /*
    Override findAll method to return List of objects instead of iterables.
     */
    List<APIEntity> findAll();

}
