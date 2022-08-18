package testUnitario.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.ProductDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.RequestPostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponsePostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.UserDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.Post;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.repository.PostRepository;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class SocialMeliServiceE3Juan {

    @InjectMocks
    SocialMeliServiceE3 socialMeliServiceE3;

    @Mock
    PostRepository postRepository;

    @Mock
    UserFollowersRepository userFollowersRepository;

    @Test
    @DisplayName("Obtener los post de las personas que sigue un usuario")
    void getAllPost() {

        //Arrange
        ResponsePostDTO res;
        List<Post> posts = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        List<Follow> followers = new ArrayList<>();
        User user = new User(2,"Boris",followed,followers);
        List<User> users = new ArrayList<>();
        users.add(user);
        List<RequestPostDTO> list = new ArrayList<>();
        int userId = 2;

        ResponsePostDTO response = new ResponsePostDTO(userId,list);


        Mockito.when(postRepository.getPosts()).thenReturn(posts);
        Mockito.when(userFollowersRepository.getUsersList()).thenReturn(users);

        //Act
        res = socialMeliServiceE3.getAllPost(userId);

        //Assert
        Assertions.assertEquals(res, response);

    }

    @Test
    @DisplayName("Obtener los post de las personas que sigue un usuario ordenadas ascendentemente")
    void getAllPostOrderAsc() {

        //Arrange
        ResponsePostDTO res;
        List<Post> posts = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        List<Follow> followers = new ArrayList<>();
        User user = new User(2,"Boris",followed,followers);
        List<User> users = new ArrayList<>();
        users.add(user);
        List<RequestPostDTO> list = new ArrayList<>();
        int userId = 2;
        String order = "date_asc";

        ResponsePostDTO response = new ResponsePostDTO(userId,list);


        Mockito.when(postRepository.getPosts()).thenReturn(posts);
        Mockito.when(userFollowersRepository.getUsersList()).thenReturn(users);

        //Act
        res = socialMeliServiceE3.getAllPost(userId,order);

        //Assert
        Assertions.assertEquals(res, response);

    }

    @Test
    @DisplayName("Obtener los post de las personas que sigue un usuario ordenadas descendentemente")
    void getAllPostOrderDesc() {

        //Arrange
        ResponsePostDTO res;
        List<Post> posts = new ArrayList<>();
        List<Follow> followed = new ArrayList<>();
        List<Follow> followers = new ArrayList<>();
        User user = new User(2,"Boris",followed,followers);
        List<User> users = new ArrayList<>();
        users.add(user);
        List<RequestPostDTO> list = new ArrayList<>();
        int userId = 2;
        String order = "date_desc";

        ResponsePostDTO response = new ResponsePostDTO(userId,list);


        Mockito.when(postRepository.getPosts()).thenReturn(posts);
        Mockito.when(userFollowersRepository.getUsersList()).thenReturn(users);

        //Act
        res = socialMeliServiceE3.getAllPost(userId,order);

        //Assert
        Assertions.assertEquals(res, response);

    }

}
