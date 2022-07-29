package com.rc.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Data
@Table(name = "employees")
@NoArgsConstructor//для mapper
@AllArgsConstructor
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String email;


    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sec_id")
    private Section section;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
