package com.example.case_study.service;

import com.example.case_study.model.FriendList;
import com.example.case_study.model.Users;

import java.util.List;

public interface IFriendListService extends ICommon<FriendList>{
    List<FriendList> findAllFriendList(Long id);
    List<FriendList> findAllFriendListConfirm(Long id);
    List<Users> findFriendOfUser(Long id);
    List<Users> findFriendOfUserConfirm(Long id);
}
