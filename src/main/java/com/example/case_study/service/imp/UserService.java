package com.example.case_study.service.imp;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.CustomUserDetail;
import com.example.case_study.model.Users;
import com.example.case_study.repository.IUserRepository;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    IUserRepository repository;

    @Override
    public List<UserDto> findAll() {
        return repository.findAllByBlockAccountFalse().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) {
        Users users = new Users(userDto);
        return new UserDto(repository.save(users));
    }

    @Override
    public UserDto updateUserInfo(UserDto userDto, Long id) {

        Users users = repository.findById(id).orElse(null);
        if (userDto.getPass() != null) {
            users.setPass(userDto.getPass());
        } else {
            users.setFullName(userDto.getFullName());
            users.setPhone(userDto.getPhone());
            users.setEmail(userDto.getEmail());
            users.setDateOfBirth(userDto.getDateOfBirth());
            users.setGender(userDto.getGender());
            users.setAddress(userDto.getAddress());
            users.setHobby(userDto.getHobby());
        }
        return new UserDto(repository.save(users));
    }


    @Override
    public UserDto findById(Long id) {
        Users users = repository.findByIdAndBlockAccountFalse(id);
        return new UserDto(users);
    }

    @Override
    public UserDto checkSignIn(UserDto userDto) {
        Users users = repository.findByUserNameAndPass(userDto.getUserName(), userDto.getPass());
        if (users != null) {
            return new UserDto(users);
        } else {
            return null;
        }
    }

    @Override
    public String checkSignUpUserName(UserDto userDto) {
        boolean isExist = repository.existsUsersByUserName(userDto.getUserName());
        if (isExist) {
            return "User already exists";
        }
        return null;
    }

    @Override
    public UserDto findByEmail(String email) {
        Users users = repository.findByEmail(email);
        if (users != null) {
            return new UserDto(users);
        }
        return null;
    }

    @Override
    public UserDto registUserLoginGoogle(OAuth2User principal) {
        String email = principal.getAttribute("email");
        UserDto userDto = new UserDto();
        userDto.setFullName(principal.getAttribute("name"));
        userDto.setAvatar(principal.getAttribute("picture"));
        userDto.setEmail(email);
        save(userDto);
        return findByEmail(email);
    }

    @Override
    public String checkRequired(UserDto userDto) {
        if (userDto.getUserName() == null) {
            return "Username is required";
        }
        if (userDto.getPass() == null) {
            return "Password is required";
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        UserDto userDto = findById(id);
        userDto.setBlockAccount(true);
        repository.save(new Users(userDto));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = repository.findByUserName(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetail(users);
    }
}
