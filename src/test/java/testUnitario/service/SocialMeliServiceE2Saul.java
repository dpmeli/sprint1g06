package testUnitario.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.FollowedDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.FollowersCountDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.FollowedNotFounException;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE2;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SocialMeliServiceE2Saul {

    @InjectMocks
    SocialMeliServiceE2 socialMeli;

    @Mock
    UserFollowersRepository userFollowersRepository;

    @Test
    public void seguidoresCorrectosTest(){

        //Arage
        Integer idUser = 1;
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        followed.add(new Follow(10,"Carlos"));
        List<Follow> followers = new ArrayList<>();
        followers.add(new Follow(20,"Saul"));
        followers.add(new Follow(21,"Pedro"));
        followers.add(new Follow(22,"Juan"));
        followers.add(new Follow(23,"Karla"));
        users.add(new User(idUser,"Juan",followed,followers));

        //Act
        when(userFollowersRepository.getUsersList()).thenReturn(users);
        FollowersCountDTO followersCountDTO = socialMeli.userFollowers(1);

        //Assert
        Assertions.assertTrue(followersCountDTO.getFollowers_count() == 4);

    }
    @Test
    public void seguidoresNullTest(){

        //Arage
        Integer idUser = 1;
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        List<Follow> followers = null;
        //followers.add(new Follow(20,"Saul"));
        //followers.add(new Follow(21,"Pedro"));
        //followers.add(new Follow(22,"Juan"));
        //followers.add(new Follow(23,"Karla"));
        users.add(new User(idUser,"Juan",followed,followers));

        //Act
        when(userFollowersRepository.getUsersList()).thenReturn(users);
        FollowersCountDTO followersCountDTO = socialMeli.userFollowers(1);

        //Assert
        Assertions.assertTrue(followersCountDTO.getFollowers_count() == 0);

    }

    @Test
    public void seguidoresErroneoTest(){

        //Arage
        Integer idUser = 1;
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        followed.add(new Follow(10,"Carlos"));
        List<Follow> followers = new ArrayList<>();
        followers.add(new Follow(20,"Saul"));
        followers.add(new Follow(21,"Pedro"));
        followers.add(new Follow(22,"Juan"));
        followers.add(new Follow(23,"Karla"));
        users.add(new User(idUser,"Juan",followed,followers));

        //Act
        when(userFollowersRepository.getUsersList()).thenReturn(users);
        FollowersCountDTO followersCountDTO = socialMeli.userFollowers(1);

        //Assert
        Assertions.assertFalse(followersCountDTO.getFollowers_count() == 6);

    }


}
