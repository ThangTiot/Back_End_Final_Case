package com.example.case_study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FriendList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int type = 0; // 0: Chờ xác nhận; 1: Bạn bè
    @ManyToOne
    private User usersTo;
    @ManyToOne
    private User usersFrom;
}
