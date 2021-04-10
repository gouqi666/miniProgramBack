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
@Table(name = "doctor")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar_url")
    private String avatarUrl;

    // 医生简介
    @Column(name = "description")
    private String description;

    // 身份证号
    @Column(name = "id_number")
    private String idNumber;

    @Column(name ="phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name="total_patients_num")
    private Integer totalPatientsNum;

    // 职称
    @Column(name = "title")
    private String title;

    // 科室
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="department_id")
    private  Department department;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable( name = "doctor_schedule",
            joinColumns={@JoinColumn(name="doctor_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="schedule_id", referencedColumnName="id")}
    )
    public Set<Schedule> schedules;

    @ManyToMany
    @JoinTable( name = "doctor_patient",
            joinColumns={@JoinColumn(name="doctor_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")}
    )
    public Set<Patient> patients;

}
