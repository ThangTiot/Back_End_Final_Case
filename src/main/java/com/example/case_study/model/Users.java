package com.example.case_study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String pass;
    private String fullName;
    private String gender;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private String address;
    private String avatar = "https://firebasestorage.googleapis.com/v0/b/case-study-md4-6d4d1.appspot.com/o/avatar-mac-dinh-1.png?alt=media&token=f17a49a4-3e0f-4d87-b642-5308b3586cb0";
    private String hobby;
    private LocalDateTime createDate = LocalDateTime.now();
    private Boolean blockAccount = true;
}
