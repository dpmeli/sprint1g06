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

    // Recibe del controller el id del usuario y valida si es usuario y llama al metodo de obtener los seguidores
    @Override
    public FollowedDTO userFollowed (int userId){

        Optional<UserDTO> user= findById(userId).stream().findFirst();
        if(user.isPresent()){
            return new FollowedDTO(user.get().getUserId(),user.get().getUserName(),userFollowedDTO(user.get()));
        }else{
            throw new UserNotFoundException("No se encuentra el usuario");
        }

    }
    // Recibe un userDTO y obtiene la lista de seguidores, y si no es null, devuelve una lista de FollowedDTO con el id y el nombre de la lista de seguidores
    private List<ListFollowedDTO> userFollowedDTO (UserDTO userDTO){

        List<ListFollowedDTO>  followedsDTO = new ArrayList<>();

        if(userDTO.getFollowed() != null) {
            for (Follow f : userDTO.getFollowed()) {
                followedsDTO.add(new ListFollowedDTO(f.getId(),f.getName()));
            }
        }else{
            throw new FollowedNotFounException("No tiene seguidores");
        }
        return followedsDTO;
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
            userDto.setFollowers(user.getFollowers());
            return userDto;
        }).collect(Collectors.toList());

        return userFollowers;
    }

    private FollowersCountDTO followersCountDTO(UserDTO userDTO){
        Integer follower = 0;
        if(userDTO.getFollowers() != null){
            follower = userDTO.getFollowers().size();
        }else{
            throw new UserNotFoundException("Null Followers");
        }
        return new FollowersCountDTO(userDTO.getUserId(), userDTO.getUserName(), follower);

    }

}
