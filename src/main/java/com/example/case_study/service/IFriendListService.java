package com.example.case_study.service;

import com.example.case_study.model.FriendList;
import com.example.case_study.model.Users;

import java.util.List;
import java.util.Optional;

public interface IFriendListService extends ICommon<FriendList>{
    List<FriendList> findAllFriendList(Long id);
    List<Users> findFriendOfUser(Long id);
    List<Users> findFriendOfUserConfirmTo(Long id);
    List<Users> findFriendOfUserConfirmFrom(Long id);
    List<Users> findAllUserNotFriend(Long id);
    List<Users> findAllMutualFriend(Long id1 , Long id2);
    FriendList confirm(Long id1, Long id2);
    FriendList deleteByUserToAndUserFrom(Long id1, Long id2);
}
