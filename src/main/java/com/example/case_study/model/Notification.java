package com.example.case_study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime dateCreate = LocalDateTime.now();
    private int seen = 0; //0: chưa xem; 1: đã xem
    private int type; // 1: Bình luận; 2:Like; 3:Yêu cầu kết bạn
    @ManyToOne
    private Users usersTo;
    @ManyToOne
    private Users usersFrom;
}
