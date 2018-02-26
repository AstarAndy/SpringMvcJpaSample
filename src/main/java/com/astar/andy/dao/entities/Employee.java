package com.astar.andy.dao.entities;

import com.astar.andy.dao.entities.Company;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String directPhone;

    @Column
    private String phone_ext;

    @Column
    private String email_address;



}
