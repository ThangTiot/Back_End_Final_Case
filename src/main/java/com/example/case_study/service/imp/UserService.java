package com.example.case_study.service.imp;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.Users;
import com.example.case_study.repository.IUserRepository;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
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
    public UserDto updateAvatar(Long id, String url) {
        Users users = repository.findById(id).orElse(null);
        users.setAvatar(url);
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
    public boolean checkSignUpUserName(UserDto userDto) {
        return repository.existsUsersByUserName(userDto.getUserName());
    }

    @Override
    public void delete(Long id) {
        UserDto userDto = findById(id);
        userDto.setBlockAccount(true);
        repository.save(new Users(userDto));
    }

}
