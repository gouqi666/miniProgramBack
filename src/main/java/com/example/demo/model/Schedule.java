package com.example.demo.model;


import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Data
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    // 1-21 代表星期一到星期天(上午，下午，全天)
    @Column(name="week_day")
    private Integer weekday;


    @Column(name="date_str")
    private String dateStr;

    @ManyToMany
    @JoinTable( name = "doctor_schedule",
            joinColumns={@JoinColumn(name="schedule_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="doctor_id", referencedColumnName="id")}
    )
    public Set<Doctor> doctors;

}
