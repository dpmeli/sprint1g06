package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.UserDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialMeliServiceE21 implements ISocialMeliServiceE2{

    @Autowired
    UserFollowersRepository repository;

    List<User> usersList = new ArrayList<>();
    List<UserDTO> userDTOs = new ArrayList<>();

    @Override
    public List<UserDTO> userFollowers(Integer userId){
        return null;
    }

    public List<UserDTO> findById(int userId) {

        List<User> users = usersList
                .stream()
                .filter(userDTO -> userDTO.getUserId() == userId)
                .collect(Collectors.toList());

        return listUserDTO(users);
    }

    private List<UserDTO>listUserDTO(List<User> Users) {

        List<UserDTO> userFollowers = Users.stream().map(user -> {
            UserDTO userDto = new UserDTO();
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setFollowers(user.getFollowers());
            return userDto;
        }).collect(Collectors.toList());

        return userFollowers;
    }
}
