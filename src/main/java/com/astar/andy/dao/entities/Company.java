package com.astar.andy.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Primary parent entity in the database.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "employees")
@Entity
@Table(name = "company")
public class Company implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @Column
    private Date dateCreated = new Date();

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

    @OneToMany(mappedBy = "company", cascade =  CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Employee> employees = new HashSet<>();


}
