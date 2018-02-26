package com.astar.andy.com.astar.andy.dao.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Primary parent entity in the database.
 */
@Data
@Entity
public class Company implements Serializable {
    @Id()
    @GeneratedValue
    private Long id;

    @Column
    private Timestamp dateCreated = new Timestamp(System.currentTimeMillis());

    @Column(nullable = false)
    private String companyName;

    @Column
    private String Street1;

    @Column
    private String Street2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String zip;

    @Column
    private String phone;

    @Column
    private String email;

    @OneToMany
    @JoinColumn(name = "company_id")
    private Set<Employee> employees;


}
