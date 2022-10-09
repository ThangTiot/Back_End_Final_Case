package com.example.case_study.repository;

import com.example.case_study.model.Comments;
import com.example.case_study.model.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFriendListRepository extends JpaRepository<FriendList, Long> {
    @Query(value = "select * from friend_list where (users_to_id = :idUserPresent or users_from_id = :idUserPresent) and type = 1", nativeQuery = true)
     List<FriendList> findAllFriend(@Param("idUserPresent") Long id);
    @Query(value = "select * from friend_list where (users_from_id = :idUserPresent and type = 0)", nativeQuery = true)
     List<FriendList> findAllFriendConfirmTo(@Param("idUserPresent") Long id);
    @Query(value = "select * from friend_list where (users_to_id = :idUserPresent and type = 0)", nativeQuery = true)
     List<FriendList> findAllFriendConfirmFrom(@Param("idUserPresent") Long id);

    @Query(value = "select * from friend_list where ((users_to_id = :idUser1 and users_from_id = :idUser2) or (users_to_id = :idUser2 and users_from_id = :idUser1))", nativeQuery = true)
    FriendList findByUsersFromAndUsersTo(@Param("idUser1") Long idUser1, @Param("idUser2") Long idUser2);
}
