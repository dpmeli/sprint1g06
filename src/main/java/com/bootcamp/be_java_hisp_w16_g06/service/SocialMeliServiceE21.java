package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.*;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.FollowedNotFounException;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.UserNotFoundException;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocialMeliServiceE21 implements ISocialMeliServiceE2{

    @Autowired
    UserFollowersRepository repository;



    @Override
    public FollowersCountDTO userFollowers(Integer userId){
        Optional<UserDTO> user= findById(userId).stream().findFirst();
        if(user.isPresent()){
            return followersCountDTO(user.get());
        }else{
            throw new UserNotFoundException("No se encuentra el usuario");
        }
    }

    @Override
    public List<FollowedDTO> userFollowed (int userId){

        Optional<UserDTO> user= findById(userId).stream().findFirst();
        if(user.isPresent()){
            return userFollowedDTO(user.get());
        }else{
            throw new UserNotFoundException("No se encuentra el usuario");
        }

    }

    private List<FollowedDTO> userFollowedDTO (UserDTO userDTO){

        List<FollowedDTO>  followedsDTO = new ArrayList<>();

        if(userDTO.getFollowed() != null) {
            for (Follow f : userDTO.getFollowed()) {
                followedsDTO.add(new FollowedDTO(f.getId(),f.getName()));
            }
        }else{
            throw new FollowedNotFounException("No tiene seguidores");
        }
        return followedsDTO;
    }



    public List<ListFollowersDTO> listFollowers(Integer userId){
        Optional<UserDTO> user= findById(userId).stream().findFirst();
        if(user.isPresent()){
            return userListFollowersDTO(user.get());
        }else{
            throw new UserNotFoundException("No se encuentra el usuario");
        }
    }

    private List<ListFollowersDTO> userListFollowersDTO (UserDTO userDTO){

        List<ListFollowersDTO> listFollowersDTO = new ArrayList<>();

        if(userDTO.getFollowers() != null) {
            for (Follow f : userDTO.getFollowers()) {
                listFollowersDTO.add(new ListFollowersDTO(f.getId(),f.getName()));
            }
        }else{
            throw new FollowedNotFounException("Null followers");
        }
        return listFollowersDTO;
    }

    public List<UserDTO> findById(int userId) {

        List<User> users = repository.getUsersList()
                .stream()
                .filter(userDTO -> userDTO.getUserId().equals(userId))
                .collect(Collectors.toList());

        return listUserDTO(users);
    }

    private List<UserDTO>listUserDTO(List<User> Users) {

        List<UserDTO> userFollowers = Users.stream().map(user -> {
            UserDTO userDto = new UserDTO();
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setFollowed(user.getFollowed());
            userDto.setFollowers(user.getFollowers());
            return userDto;
        }).collect(Collectors.toList());

        return userFollowers;
    }

    private FollowersCountDTO followersCountDTO(UserDTO userDTO){
        Integer follower = 0;
        if(userDTO.getFollowers() != null){
            follower = userDTO.getFollowers().size();
        }
        return new FollowersCountDTO(userDTO.getUserId(), userDTO.getUserName(), follower);

    }

}
