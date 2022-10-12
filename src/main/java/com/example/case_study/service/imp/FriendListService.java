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
    public List<Users> findFriendOfUserConfirmTo(Long id) {
        List<FriendList> friendLists = iFriendListRepository.findAllFriendConfirmTo(id);
        List<Users> usersConfirmTo = new ArrayList<>();
        for (FriendList f : friendLists) {
            usersConfirmTo.add(f.getUsersTo());
        }
        return usersConfirmTo;
    }

    @Override
    public List<Users> findFriendOfUserConfirmFrom(Long id) {
        List<FriendList> friendLists = iFriendListRepository.findAllFriendConfirmFrom(id);
        List<Users> usersConfirmFrom = new ArrayList<>();
        for (FriendList f : friendLists) {
            usersConfirmFrom.add(f.getUsersFrom());
        }
        return usersConfirmFrom;
    }


    @Override
    public List<Users> findAllUserNotFriend(Long id) {
        List<Users> allUser = iUserRepository.findAll();
        List<Users> friendOfUser = findFriendOfUser(id);
        List<Users> friendOfUserConfirm1 = findFriendOfUserConfirmFrom(id);
        List<Users> friendOfUserConfirm2 = findFriendOfUserConfirmTo(id);
        friendOfUser.addAll(friendOfUserConfirm1);
        friendOfUser.addAll(friendOfUserConfirm2);
        for (Users users : friendOfUser) {
            allUser.remove(users);
        }
        allUser.remove(iUserRepository.findByIdAndBlockAccountFalse(id));
        return allUser;
    }

    @Override
    public List<Users> findAllMutualFriend(Long id1, Long id2) {
        List<Users> mutualFriend = new ArrayList<>();
        List<Users> friendOfUserPresent = findFriendOfUser(id1);
        List<Users> friendOfUserClick = findFriendOfUser(id2);
        if (friendOfUserPresent.size() >= friendOfUserClick.size()) {
            for (Users users : friendOfUserClick) {
                if (friendOfUserPresent.contains(users)) {
                    mutualFriend.add(users);
                }
            }
        } else {
            for (Users users : friendOfUserPresent) {
                if (friendOfUserClick.contains(users)) {
                    mutualFriend.add(users);
                }
            }
        }
        return mutualFriend;
    }


    @Override
    public FriendList confirm(Long id1, Long id2) {
        FriendList relationship = iFriendListRepository.findByUsersFromAndUsersTo(id1, id2);
        relationship.setType(1);
        return iFriendListRepository.save(relationship);
    }

    @Override
    public FriendList deleteByUserToAndUserFrom(Long id1, Long id2) {
        FriendList relationship = iFriendListRepository.findByUsersFromAndUsersTo(id1, id2);
        iFriendListRepository.deleteById(relationship.getId());
        return relationship;
    }
}
