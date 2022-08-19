package testUnitario.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.ProductDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.RequestPostDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.repository.PostRepository;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE1;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE3;
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
public class SocialMeliServiceE3Aldo {

    @InjectMocks
    SocialMeliServiceE3 socialMeli;
    @Mock
    PostRepository repositoryPost;
    @Mock
    SocialMeliServiceE1 socialMeliServiceE1;
    @Mock
    UserFollowersRepository userFollowersRepository;


    @Test
    @Description("Creación de Post")
    public void CreatePostTest() {
        // arrange
        RequestPostDTO requestPostDTO = new RequestPostDTO();
        ProductDTO productDTO = new ProductDTO();
        requestPostDTO.setProduct(productDTO);
        requestPostDTO.setUser_id(1);
        requestPostDTO.setDate("18-08-2022");
        List<User> usersListMock = new ArrayList<>();
        User userMock = new User();
        userMock.setUserId(1);
        usersListMock.add(userMock);
        boolean usuarioEncontrado = true;

        //when(userFollowersRepository.getUsersList()).thenReturn(usersListMock);
        when(socialMeliServiceE1.findById(requestPostDTO.getUser_id())).thenReturn(usuarioEncontrado);


        // act
        socialMeli.createPost(requestPostDTO);

        // assert
        Assertions.assertTrue(usuarioEncontrado);
    }

    @Test
    @Description("No Creacion de Post")
    public void NoCreatePostTest() {
        // arrange
        RequestPostDTO requestPostDTO = new RequestPostDTO();
        ProductDTO productDTO = new ProductDTO();
        requestPostDTO.setProduct(productDTO);
        requestPostDTO.setUser_id(1);
        requestPostDTO.setDate("18-08-2022");
        List<User> usersListMock = new ArrayList<>();
        User userMock = new User();
        userMock.setUserId(1);
        usersListMock.add(userMock);
        boolean usuarioEncontrado = false;

        //when(userFollowersRepository.getUsersList()).thenReturn(usersListMock);
        when(socialMeliServiceE1.findById(requestPostDTO.getUser_id())).thenReturn(usuarioEncontrado);


        // act
        socialMeli.createPost(requestPostDTO);

        // assert
        Assertions.assertFalse(usuarioEncontrado);
    }
}
