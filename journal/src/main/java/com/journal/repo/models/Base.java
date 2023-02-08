package com.journal.repo.models;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

public abstract class Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Field(value = "_id")
    private String id;

    @Version
    @Field(value = "version")
    private Long version;

    @CreatedDate
    @Field(value = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Field(value = "updated_date")
    private Date updatedDate;

    @Field(value = "is_deleted")
    private int isDeleted = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Base{" +
                "id='" + id + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
