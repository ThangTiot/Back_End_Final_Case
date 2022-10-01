package com.example.case_study.service.imp;

import com.example.case_study.model.User;
import com.example.case_study.repository.ICommentRepository;
import com.example.case_study.repository.IUserRepository;
import com.example.case_study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository repository;
    @Autowired
    ICommentRepository commentRepository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User users) {
        return repository.save(users);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User checkSignIn(User users) {
        boolean exists = repository.existsByUserNameAndPass(users.getUserName(),users.getPass());
        User user = repository.findFirstByUserNameAndPass(users.getUserName(),users.getPass());
        for (User a : findAll()) {
            if (a.getBlockAccount() && a.getUserName().equals(users.getUserName()) && a.getPass().equals(users.getPass())) {
                return a;
            }
        }
        return null;
    }

    @Override
    public User checkSignUpUserName(User users) {
        for (User a : findAll()) {
            if (a.getUserName().equals(users.getUserName())) {
                return users;
            }
        }
        return null;
    }

    @Override
    public User register(User users) {
        boolean exists = repository.existsByUserName(users.getUserName());
        if (exists) {
            return null;
        }
        return repository.save(users);
    }

    @Override
    public User create(User users) {
        boolean existed = repository.existsByUserName(users.getUserName());
        if (existed) {
            return null;
        }
        return repository.save(users);
    }

    @Override
    public void delete(Long id) {
        Optional<User> users = findById(id);
        if (users.isPresent()) {
            users.get().setBlockAccount(false);
            repository.save(users.get());
        }
    }
}
