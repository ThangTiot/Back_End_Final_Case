package com.example.case_study.service.imp;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.Users;
import com.example.case_study.repository.IUserRepository;
import com.example.case_study.sercurity.userprincal.UserPrinciple;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;
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
    public boolean checkSignUpUserName(UserDto userDto) {
        return repository.existsUsersByUserName(userDto.getUserName());
    }

    @Override
    public UserDto findByUserName(String username) {
        Users users = repository.findByUserName(username);
        return new UserDto(users);
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
        return UserPrinciple.build(users);
    }
}
