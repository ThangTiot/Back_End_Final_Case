package com.example.case_study.service;

import com.example.case_study.model.FriendList;

import java.util.List;

public interface IFriendListService extends ICommon<FriendList>{
    List<FriendList> findAllFriend(Long id);
}
