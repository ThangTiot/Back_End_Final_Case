package com.example.case_study.service.imp;

import com.example.case_study.model.Users;
import com.example.case_study.repository.IUserRepository;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public List<Users> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Users save(Users users) {
        return iUserRepository.save(users);
    }

    @Override
    public Optional<Users> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public Users checkSignIn(Users users) {
        for (Users a : findAll()) {
            if (a.getBlockAccount() && a.getUserName().equals(users.getUserName()) && a.getPass().equals(users.getPass())) {
                return a;
            }
        }
        return null;
    }

    @Override
    public Users checkSignUpUserName(Users users) {
        for (Users a : findAll()) {
            if (a.getUserName().equals(users.getUserName())) {
                return users;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Users> users = findById(id);
        if (users.isPresent()) {
            users.get().setBlockAccount(false);
            iUserRepository.save(users.get());
        }
    }
}
