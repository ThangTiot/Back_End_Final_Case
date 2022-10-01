package com.example.case_study.repository;

import com.example.case_study.model.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFriendListRepository extends JpaRepository<FriendList, Long> {
    @Query(value = "select * from friend_list where users_to_id = :idUserPresent or users_from_id = :idUserPresent and status_request_to = 'Unfriend' ", nativeQuery = true)
    public List<FriendList> findAllFriend(@Param("idUserPresent") Long id);
}
