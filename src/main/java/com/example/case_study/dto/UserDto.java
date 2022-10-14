package com.example.case_study.dto;

import com.example.case_study.model.Role;
import com.example.case_study.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String userName;
    private String pass;
    private String fullName;
    private String gender;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private String address;
    private String avatar = "https://firebasestorage.googleapis.com/v0/b/case-study-md6.appspot.com/o/avatar-mac-dinh-1.png?alt=media&token=93a1cbfe-8abc-4548-8a70-e77632767394";
    private String hobby;
    private Boolean blockAccount = false;
    private Set<Role> roles;
    public UserDto(Users users) {
        BeanUtils.copyProperties(users, this);
    }
}
