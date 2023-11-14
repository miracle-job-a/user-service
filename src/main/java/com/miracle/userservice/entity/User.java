package com.miracle.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    private Long id;
}
