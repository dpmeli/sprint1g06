package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.FollowIdDto;
import com.bootcamp.be_java_hisp_w16_g06.dto.UserDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.UserNotFoundException;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocialMeliServiceE1 implements ISocialMeliServiceE1 {

    @Autowired
    UserFollowersRepository userFollowersRepository;

    @Autowired
    SocialMeliServiceE21 socialMeliServiceE21Service;

    @Override
    public List<UserDTO> followUser(FollowIdDto followIdDto) {

        int idFollower = followIdDto.getUserId();
        int idFollowed = followIdDto.getUserIdToFollow();

        String nameFollower = "";
        String nameFollowed = "";

        List<UserDTO> listUser = listUserDTO(userFollowersRepository.getUsersList());


        List<Follow> listFollowed = new ArrayList<>();
        List<Follow> listFollowers;

        Optional<UserDTO> userFollow= socialMeliServiceE21Service.findById(idFollower).stream().findFirst();
        Optional<UserDTO> userFollowed= socialMeliServiceE21Service.findById(idFollower).stream().findFirst();


        findById(idFollowed);
        findById(idFollower);

        nameFollowed = getNameUser(listUser, idFollowed);
        nameFollower = getNameUser(listUser, idFollower);


        //validateFollow(listUser, idFollowed, idFollower);


        listFollowed = userFollow.get().getFollowed();

        if(listFollowed == null){
            listFollowed = new ArrayList<>();
        }


        listFollowed = listFollowed.stream().map(x->{
            Follow followed = new Follow();
            followed.setName(x.getName());
            followed.setId(x.getId());
            return followed;
        }).collect(Collectors.toList());

        Follow followed = new Follow();
        followed.setId(idFollowed);
        followed.setName(nameFollowed);
        listFollowed.add(followed);

        userFollow.get().setFollowed(listFollowed);


        /*
        listFollowers = getListFollow(idFollower, listUser);

        Follow followers = new Follow();
        followers.setId(idFollower);
        followers.setName(nameFollower);

        listFollowers.add(followers);

         */


        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userFollow.get().getUserId());
        userDTO.setUserName(userFollow.get().getUserName());
        userDTO.setFollowed(userFollow.get().getFollowed());

        listUser.add(userDTO);


        userFollowersRepository.setUsersList(listUserEntity(listUser));

        return listUserDTO(userFollowersRepository.getUsersList());


    }

    private String getNameUser(List<UserDTO> listaUser, int id){

        for(UserDTO userDTO: listaUser){
            if(userDTO.getUserId() == id) {
                return userDTO.getUserName();
            }
        }

        return null;
    }


    private List<Follow> getListFollow(int idUser, List<UserDTO> listaUser){

        return listaUser.stream().filter(y->y.getUserId() == idUser).map(x->{
            Follow follow = new Follow();
            follow.setId(x.getUserId());
            follow.setName(x.getUserName());
            return follow;
        }).collect(Collectors.toList());

    }


    private void validateFollow(List<UserDTO> listaUser, int idFollowed, int idFollower){

        List exist = new ArrayList();

        for(UserDTO userDTO: listaUser){
            if(userDTO.getUserId() == idFollower) {

                exist.add(userDTO.getFollowed().stream()
                        .filter(x -> x.getId() == idFollowed)
                        .collect(Collectors.toList()));

                break;
            }
        }

        if(exist.size()>0){

        }

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
            throw new UserNotFoundException("User Not Found");
        } else {

            return true;
        }

    }


    private List<UserDTO> listUserDTO (List<User> Users) {

        return Users.stream().map(user -> {
            UserDTO userDto = new UserDTO();
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setFollowers(user.getFollowers());
            userDto.setFollowed(user.getFollowed());
            return userDto;
        }).collect(Collectors.toList());

    }

    private List<User> listUserEntity(List<UserDTO> userDTO){

        return userDTO.stream().map(user-> {
            User userEntity = new User();
            userEntity.setUserId(user.getUserId());
            userEntity.setUserName(user.getUserName());
            userEntity.setFollowers(user.getFollowers());
            userEntity.setFollowed(user.getFollowed());
            return userEntity;
        }).collect(Collectors.toList());

    }


}
