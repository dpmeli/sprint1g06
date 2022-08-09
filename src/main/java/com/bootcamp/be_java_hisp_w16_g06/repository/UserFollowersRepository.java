package com.bootcamp.be_java_hisp_w16_g06.repository;

import com.bootcamp.be_java_hisp_w16_g06.dto.UserDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserFollowersRepository {


    List<UserDTO> userDTOs;
    List<User> usersList = new ArrayList<>();

    private List<User> loadUser(){

        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:createUser.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<User>> typeRef = new TypeReference<>() {};
        List<User> users = null;

        try {
            users = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    //realizar la busqueda por medio de un INT no un String
    public List<UserDTO> findById(int userId){

        List<User> users = usersList
                .stream()
                .filter(userDTO -> userDTO.getUserId() == userId)
                .collect(Collectors.toList());

        return userDTO(users);
    }

    public List<UserDTO> userDTO(List<User> Users) {

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
