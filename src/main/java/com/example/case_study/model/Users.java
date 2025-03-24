package com.example.case_study.model;

import com.example.case_study.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;

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
    @Column(unique = true)
    private String userName;
    private String pass;
    private String fullName;
    private String gender;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private String address;
    private String avatar;
    private String hobby;
    @CreationTimestamp
    private LocalDateTime createDate;
    private Boolean blockAccount;

    public Users(UserDto userDto) {
        BeanUtils.copyProperties(userDto,this);
    }
}
