package com.journal.repo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

public abstract class Base implements Serializable {
    @Id
    @Field(value = "_id")
    private String id;

    @CreatedDate
    @Field(value = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Field(value = "updated_date")
    private Date updatedDate;

    @Field(value = "is_deleted")
    private int isDeleted = 0;
}
