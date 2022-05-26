package com.developer.rest.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "STUDENT")
public class Student {



    @Transient
    public static final String SEQUENCE_NAME = "student_sequence";

    @Id
    private  Long id;
    private  String name;
    private  Integer rollNumber;
    private Date createdAt;
    private Date updateAt;


}
