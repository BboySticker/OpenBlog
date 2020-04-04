package com.openblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "userId")
    private String userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userPassword")
    private String userPass;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userUrl")
    private String userUrl;

    @Column(name = "userAvatar")
    private String userAvatar;

    @Column(name = "userLastLoginIp")
    private String userLastLoginIp;

    @Column(name = "userRegisterTime")
    private Date userRegisterTime;

    @Column(name = "userLastLoginTime")
    private Date userLastLoginTime;

    @Column(name = "userStatus")
    private Integer userStatus;

    @Transient
    private String token;

    @Transient
    private Integer articleCount;

}
