package testUnitario.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.FollowedDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.FollowedNotFounException;
import com.bootcamp.be_java_hisp_w16_g06.exceptions.UserNotFoundException;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE2;
import jdk.jfr.Description;
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
public class SocialMeliServiceE2Juliana {


    @InjectMocks
    SocialMeliServiceE2 socialMeli;
    @Mock
    UserFollowersRepository userFollowersRepository;

    @Test
    @Description("Modo ordenamiento no existe")
    // T-003 Verificar que el tipo de ordenamiento alfabético exista (US-0008)
    public void noExisteOrdenamientoTest() {
        // arrange
        Integer idUser = 1;
        Boolean exception = false;
        String order = "asc";
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        followed.add(new Follow(1, "Andres"));
        List<Follow> followers = new ArrayList<>();
        followers.add(new Follow(1, "Manuel"));
        users.add(new User(1, "Juan", followed, followers));

        // act

        when(userFollowersRepository.getUsersList()).thenReturn(users);

        // assert
        Assertions.assertThrows(FollowedNotFounException.class, () -> socialMeli.userFollowed(idUser, order));

    }

    @Test
    @Description("Modo ordenamiento ascendente")
    public void ExisteOrdenamientoAscendenteTest() {
        // arrange
        Integer idUser = 1;
        Boolean exception = false;
        String order = "name_asc";
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        followed.add(new Follow(1, "Andres"));
        followed.add(new Follow(2, "Marco"));
        List<Follow> followers = new ArrayList<>();
        followers.add(new Follow(1, "Manuel"));
        users.add(new User(1, "Juan", followed, followers));
        String first = "Andres";

        // act

        when(userFollowersRepository.getUsersList()).thenReturn(users);
        FollowedDTO followedDto = socialMeli.userFollowed(idUser, order);

        // assert
        Assertions.assertTrue(followedDto.getFollowed().stream().findFirst().get().getUser_name() == first);

    }

    @Test
    @Description("Modo ordenamiento descendente")
    public void ExisteOrdenamientoDescendenteTest() {
        // arrange
        Integer idUser = 1;
        Boolean exception = false;
        String order = "name_desc";
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        followed.add(new Follow(1, "Andres"));
        followed.add(new Follow(2, "Marco"));
        List<Follow> followers = new ArrayList<>();
        followers.add(new Follow(1, "Manuel"));
        users.add(new User(1, "Juan", followed, followers));
        String first = "Marco";

        // act

        when(userFollowersRepository.getUsersList()).thenReturn(users);
        FollowedDTO followedDto = socialMeli.userFollowed(idUser, order);

        // assert
        Assertions.assertTrue(followedDto.getFollowed().stream().findFirst().get().getUser_name() == first);

    }

    @Test
    @Description("Modo ordenamiento null")
    public void ExisteOrdenamientoNullTest() {
        // arrange
        Integer idUser = 1;
        Boolean exception = false;
        String order = null;
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        followed.add(new Follow(1, "Andres"));
        followed.add(new Follow(2, "Marco"));
        List<Follow> followers = new ArrayList<>();
        followers.add(new Follow(1, "Manuel"));
        users.add(new User(1, "Juan", followed, followers));
        String first = "Andres";

        // act

        when(userFollowersRepository.getUsersList()).thenReturn(users);
        FollowedDTO followedDto = socialMeli.userFollowed(idUser, order);

        // assert
        Assertions.assertTrue(followedDto.getFollowed().stream().findFirst().get().getUser_name() == first);

    }

    @Test
    @Description("No se encuentra el usuario")
    public void NoExisteUsuario() {
        // arrange
        Integer idUser = 333;
        Boolean exception = false;
        String order = "name_asc";
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        followed.add(new Follow(1, "Andres"));
        followed.add(new Follow(2, "Marco"));
        List<Follow> followers = new ArrayList<>();
        followers.add(new Follow(1, "Manuel"));
        users.add(new User(1, "Juan", followed, followers));

        // act

        when(userFollowersRepository.getUsersList()).thenReturn(users);

        // assert
        Assertions.assertThrows(UserNotFoundException.class, () -> socialMeli.userFollowed(idUser, order));

    }

    @Test
    @Description("No sigue vendedores")
    public void NoExisteFollowedDeUsuario() {
        // arrange
        Integer idUser = 1;
        Boolean exception = false;
        String order = "name_asc";
        List<User> users = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        List<Follow> followers = new ArrayList<>();
        //followers.add(new Follow(1,"Manuel"));
        users.add(new User(1, "Juan", followed, followers));

        // act

        when(userFollowersRepository.getUsersList()).thenReturn(users);

        // assert
        Assertions.assertThrows(FollowedNotFounException.class, () -> socialMeli.userFollowed(idUser, order));

    }


}
