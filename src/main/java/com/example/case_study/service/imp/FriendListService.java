package com.example.case_study.service.imp;

import com.example.case_study.model.FriendList;
import com.example.case_study.model.Users;
import com.example.case_study.repository.IFriendListRepository;
import com.example.case_study.repository.IUserRepository;
import com.example.case_study.service.IFriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FriendListService implements IFriendListService {
    @Autowired
    IFriendListRepository iFriendListRepository;
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public List<FriendList> findAll() {
        return iFriendListRepository.findAll();
    }

    @Override
    public FriendList save(FriendList friendList) {
        return iFriendListRepository.save(friendList);
    }

    @Override
    public void delete(Long id) {
        iFriendListRepository.deleteById(id);
    }

    @Override
    public List<FriendList> findAllFriendList(Long id) {
        return iFriendListRepository.findAllFriend(id);
    }

    @Override
    public List<FriendList> findAllFriendListConfirm(Long id) {
        return iFriendListRepository.findAllFriendConfirm(id);
    }

    @Override
    public List<Users> findFriendOfUser(Long id) {
        List<FriendList> friendListsRaw = findAllFriendList(id);
        List<Users> friendListReal = new ArrayList<>();
        for (int i = 0; i < friendListsRaw.size(); i++) {
            Long idUserTo = friendListsRaw.get(i).getUsersTo().getId();
            Users users;
            if (idUserTo == id) {
                users = friendListsRaw.get(i).getUsersFrom();
            } else {
                users = friendListsRaw.get(i).getUsersTo();
            }
            friendListReal.add(users);
        }
        return friendListReal;
    }

    @Override
    public List<Users> findFriendOfUserConfirm(Long id) {
        List<FriendList> friendListsRaw = findAllFriendListConfirm(id);
        List<Users> friendListReal = new ArrayList<>();
        for (int i = 0; i < friendListsRaw.size(); i++) {
            Long idUserTo = friendListsRaw.get(i).getUsersTo().getId();
            Users users;
            if (idUserTo == id) {
                users = friendListsRaw.get(i).getUsersFrom();
            } else {
                users = friendListsRaw.get(i).getUsersTo();
            }
            friendListReal.add(users);
        }
        return friendListReal;
    }

    @Override
    public List<Users> findAllUserNotFriend(Long id) {
        List<Users> allUser = iUserRepository.findAll();
        List<Users> friendOfUser = findFriendOfUser(id);
        List<Users> friendOfUserConfirm = findFriendOfUserConfirm(id);
        friendOfUser.addAll(friendOfUserConfirm);
        for (Users users : friendOfUser) {
            allUser.remove(users);
        }
        allUser.remove(iUserRepository.findByIdAndBlockAccountFalse(id));
        return allUser;
    }

    @Override
    public List<Users> findAllMutualFriend(Long id1, Long id2) {
        List<Users> mutualFriend = null;
        List<Users> friendOfUserPresent = findFriendOfUser(id1);
        List<Users> friendOfUserClick = findFriendOfUser(id2);
        if (friendOfUserPresent.size() > friendOfUserClick.size()){
            for (Users users : friendOfUserClick) {
                for (Users value : friendOfUserPresent) {
                    if (Objects.equals(value.getId(), users.getId())) {
                        mutualFriend.add(users);
                    }
                }
            }
        }
        return mutualFriend;
    }
}
