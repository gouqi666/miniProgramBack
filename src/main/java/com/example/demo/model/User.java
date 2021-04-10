package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.sql.Update;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;


    @OneToMany
    private Set<Patient> patients = new HashSet<>();




    /**
     * 微信openId
     */
    @Column(unique = true, name = "open_id")
    private String openId;

    /**
     * 用户昵称
     */
    @Column(name="nick_name")
    private String nickName;

    /**
     * 性别 0-未知 1-male,2-female
     */
    private Integer gender;

    /**
     * 头像地址
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "union_id")
    private String unionId;

    private String country;

    private String province;

    private String city;

    private String language;

    private String email;

    private String phone;

    private String remarks;

    private Boolean enabled;


    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

}