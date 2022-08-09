package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.FollowIdDto;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.UserNotFoundException;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialMeliServiceE1 implements ISocialMeliServiceE1 {

    List<Follow> listFollowed = new ArrayList<>();
    List<Follow> listFollowers = new ArrayList<>();
    User user1 = new User(234, "jose");
    User user2 = new User(756, "pepa");
    User user3 = new User(123, "maria");

    @Autowired
    UserFollowersRepository userFollowersRepository;

    @Override
    public void followUser(FollowIdDto followIdDto) {
        int id = followIdDto.getUserId();
        int fid = followIdDto.getUserIdToFollow();

        findById(id);

        // Accion Follow
        listFollowed.add(new Follow(fid, user3.getUserName()));
        user1.setFollowed(listFollowed);

        listFollowers.add(new Follow(id, user1.getUserName()));
        user3.setFollowers(listFollowers);



        //listFollowed = user1.getFollowed(); //listFollowers = user3.getFollowers();

        System.out.println(user1.getUserName() + ": " + user1);
        System.out.println(user2.getUserName() + ": " + user2);
        System.out.println(user3.getUserName() + ": " + user3);
        System.out.println("\n");

    }

    @Override
    public void unfollowUser(FollowIdDto followIdDto) {

    }

    @Override
    public boolean findById(int userId) {

        List<User> users = userFollowersRepository.getUsersList()
                .stream()
                .filter(userDTO -> userDTO.getUserId() == userId)
                .collect(Collectors.toList());

        if(users.isEmpty()) {
            throw new UserNotFoundException("Not Found");
        } else {
            return true;
        }

    }


}
