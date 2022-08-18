package testUnitario.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.FollowIdDto;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponseDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.UserNotFoundException;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SocialMeliServiceE1Tomas {

    @Mock
    UserFollowersRepository userFollowersRepository;

    @InjectMocks
    SocialMeliServiceE1 socialMeliServiceE1;

    @Test
    @DisplayName("El usuario a dejar de seguir NO existe")
    public void unFollowUserNotFoundTest(){

        //Arrange
        List<User> listUser = new ArrayList<>();
        List<Follow> listFollower = new ArrayList<>();
        List<Follow> listFollowed = new ArrayList<>();

        User user = new User(7, "Karen", listFollower, listFollowed);
        listUser.add(user);

        FollowIdDto followIdDto = new FollowIdDto(7,9);

        Mockito.when(userFollowersRepository.getUsersList()).thenReturn(listUser);

        //Act //Assert
        Assertions.assertThrows(UserNotFoundException.class, () -> socialMeliServiceE1.unFollowUser(followIdDto));

    }

    @Test
    @DisplayName("El usuario a dejar de seguir SI existe")
    public void unFollowUserFoundTest() {

        //Arrange
        ResponseDTO responseDTOExpected = new ResponseDTO("Unfollow Successful", 200);
        List<User> listUser = new ArrayList<>();

        List<Follow> listFollower = new ArrayList<>();
        List<Follow> listFollowed = new ArrayList<>();

        Follow follow = new Follow(7, "Juan");
        listFollowed.add(follow);

        User userOne = new User(3, "Tomas", listFollowed, listFollower);

        List<Follow> listFollowerTwo = new ArrayList<>();
        List<Follow> listFollowedTwo = new ArrayList<>();

        Follow followTwo = new Follow(3, "Tomas");
        listFollowerTwo.add(followTwo);

        User userTwo = new User(7, "Juan", listFollowerTwo, listFollowedTwo);

        FollowIdDto followIdDto = new FollowIdDto(3,7);

        listUser.add(userOne);
        listUser.add(userTwo);

        Mockito.when(userFollowersRepository.getUsersList()).thenReturn(listUser);

        //Act
        ResponseDTO responseDTOService;
        responseDTOService = socialMeliServiceE1.unFollowUser(followIdDto);

        //Assert
        Assertions.assertEquals(responseDTOExpected, responseDTOService);
    }

}
