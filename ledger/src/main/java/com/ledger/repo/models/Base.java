package com.ledger.repo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public abstract class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @Field(value = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Field(value = "updated_at")
    private Date updatedAt;

    @Field(value = "is_deleted")
    private boolean isDeleted = Boolean.FALSE;
}
