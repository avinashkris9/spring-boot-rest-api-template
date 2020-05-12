package com.github.avinashkris9.springboot.api.template.entity;

import javax.persistence.*;

/*

JPA Entity class.
 */
@Entity
@Table(name="APIEntity")
public class APIEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String field1;


    public  APIEntity()
    {

    }
    public APIEntity(long id, String field1) {
        this.id = id;
        this.field1 = field1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }
}
