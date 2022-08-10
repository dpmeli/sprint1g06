package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.FollowIdDto;
import com.bootcamp.be_java_hisp_w16_g06.dto.UserDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.UserNotFoundException;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SocialMeliServiceE1 implements ISocialMeliServiceE1 {

    @Autowired
    UserFollowersRepository userFollowersRepository;
    @Autowired
    SocialMeliServiceE21 socialMeliServiceE21Service;
    Map<Integer, String> followMap = new HashMap<Integer, String>();
    List<UserDTO> listUser = new ArrayList<>();
    List<Follow> listFollowed = new ArrayList<Follow>();
    List<Follow> listFollowers;
    String nameFollower = "";
    String nameFollowed = "";

    @Override
    public List<UserDTO> followUser(FollowIdDto followIdDto) {

        int idFollower = followIdDto.getUserId();           //  Seguidor
        int idFollowed = followIdDto.getUserIdToFollow();   //  Seguido

        findById(idFollower);
        findById(idFollowed);

        if (followMap.isEmpty()) {
            listUser = listUserDTO(userFollowersRepository.getUsersList());
            for (UserDTO follow : listUser) {
                followMap.put(follow.getUserId(), follow.getUserName());
            }
        }


        for (UserDTO userDTO : listUser) {
            List<Follow> followedList = new ArrayList<>();

            if (idFollower == userDTO.getUserId()) {
                Follow followeder = new Follow(idFollowed, followMap.get(idFollowed));
                if (userDTO.getFollowed() != null) { // importatnte
                    followedList = userDTO.getFollowed();
                }
                followedList.add(followeder);
                userDTO.setFollowed(followedList);
            }
            // Pendiente Validar () - Daniel
            // Follower - Tomas
            // Quemar en repository
        }

        return listUserDTO(userFollowersRepository.getUsersList());
    }

    private String getNameUser(List<UserDTO> listaUser, int id) {

        for (UserDTO userDTO : listaUser) {
            if (userDTO.getUserId() == id) {
                return userDTO.getUserName();
            }
        }

        return null;
    }

    private List<Follow> getListFollow(int idUser, List<UserDTO> listaUser) {

        return listaUser.stream().filter(y -> y.getUserId() == idUser).map(x -> {
            Follow follow = new Follow();
            follow.setId(x.getUserId());
            follow.setName(x.getUserName());
            return follow;
        }).collect(Collectors.toList());

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

        if (users.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        } else {

            return true;
        }

    }

    private List<UserDTO> listUserDTO(List<User> Users) {

        return Users.stream().map(user -> {
            UserDTO userDto = new UserDTO();
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setFollowers(user.getFollowers());
            userDto.setFollowed(user.getFollowed());
            return userDto;
        }).collect(Collectors.toList());

    }

    private List<User> listUserEntity(List<UserDTO> userDTO) {

        return userDTO.stream().map(user -> {
            User userEntity = new User();
            userEntity.setUserId(user.getUserId());
            userEntity.setUserName(user.getUserName());
            userEntity.setFollowers(user.getFollowers());
            userEntity.setFollowed(user.getFollowed());
            return userEntity;
        }).collect(Collectors.toList());

    }


}
