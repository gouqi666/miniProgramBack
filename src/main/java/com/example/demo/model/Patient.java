package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.sun.org.apache.xpath.internal.objects.XString;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.sql.Update;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    /**
     * 微信openId
     */
    @ManyToOne
     private User user;


    @Column(name = "name")
    private String name;

    // 身份证号
    @Column(name = "id_number")
    private String idNumber;

    @Column(name ="phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    @ManyToMany
    @JoinTable( name = "doctor_patient",
            joinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="doctor_id", referencedColumnName="id")}
    )
    public Set<Patient> doctors;

}
