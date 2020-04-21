package com.openblog.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "userId")
    private String userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userPassword", length = 40)
    private String userPass;

    @Column(name = "userEmail")
    @Email(message = "Please provide valid email address")
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

    @Column(name = "token")
    private String token;

    @Column(name = "isAdmin")
    private Integer isAdmin;

    @Transient
    private Integer articleCount;

}
