package com.example.case_study.service.imp;

import com.example.case_study.dto.UserDto;
import com.example.case_study.model.Posts;
import com.example.case_study.model.Users;
import com.example.case_study.repository.IPostRepository;
import com.example.case_study.service.IFriendListService;
import com.example.case_study.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService implements IPostService {
    @Autowired
    IPostRepository iPostRepository;
    @Autowired
    IFriendListService iFriendListService;

    @Override
    public List<Posts> findAll() {
        return iPostRepository.findAll();
    }

    @Override
    public Posts save(Posts posts) {
        return iPostRepository.save(posts);
    }

    @Override
    public void delete(Long id) {
        iPostRepository.deleteById(id);
    }

    @Override
    public Posts findById(Long id) {
        return iPostRepository.findById(id).orElse(null);
    }

    @Override
    public List<Posts> listPostOfNewFeed(Long id) {
        List<Posts> postsRaw = findAll();
        List<Posts> postsReal = new ArrayList<>();
        List<Users> friendList = iFriendListService.findFriendOfUser(id);
        for (Posts posts : postsRaw) {
            if (posts.getUsers().getId() == id) {
                postsReal.add(posts);
            } else if (posts.getPermissionPost().equals("public")) {
                postsReal.add(posts);
            } else if (friendList.contains(posts.getUsers()) && (posts.getPermissionPost().equals("friend"))) {
                postsReal.add(posts);
            }
        }
        postsReal.sort(Comparator.comparing(Posts::getCreateDate));
        return postsReal;
    }

    @Override
    public List<Posts> findPostById(Long id) {
        return iPostRepository.findPostById(id);
    }
}
