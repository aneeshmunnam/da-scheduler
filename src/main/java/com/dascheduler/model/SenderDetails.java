package com.dascheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public class SenderDetails {

    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String emailAddress;
    @Column(length = 4096)
    private String message;

}
