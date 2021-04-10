package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;



@Entity
@Builder
@Data
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;



    @Column(name = "name")
    private String name;

    // 科室简介
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "department",cascade = {CascadeType.ALL})
    private Set<Doctor> doctors;

}