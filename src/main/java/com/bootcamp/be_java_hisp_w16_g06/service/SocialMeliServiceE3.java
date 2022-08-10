package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.ProductDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.RequestPostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponsePostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.UserDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.Follow;
import com.bootcamp.be_java_hisp_w16_g06.entity.Post;
import com.bootcamp.be_java_hisp_w16_g06.entity.Product;
import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import com.bootcamp.be_java_hisp_w16_g06.repository.PostRepository;
import com.bootcamp.be_java_hisp_w16_g06.repository.UserFollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialMeliServiceE3 implements ISocialMeliServiceE3 {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserFollowersRepository userFollowersRepository;
    @Autowired
    ISocialMeliServiceE1 socialMeliServiceE1;

    @Override
    public void createPost(RequestPostDTO requestPostDTO) {
        if(socialMeliServiceE1.findById(requestPostDTO.getUser_id())) {
            List<Post> lstRepository = postRepository.getPosts();
            lstRepository.add(requestDTOToEntity(requestPostDTO));
            postRepository.setPosts(lstRepository);
        }
    }

    @Override
    public ResponsePostDTO getAllPost(int userId) {

        List<Post> post = postRepository.getPosts();
        List<UserDTO> users = listUserDTO(userFollowersRepository.getUsersList().stream().filter(x -> (x.getUserId() == userId && x.getFollowed() != null)).collect(Collectors.toList()));

        List<Post> posts = new ArrayList<>();

        for (int i=0; i < post.size(); i++) {
            for (int j=0; j < users.size(); j++) {
                if (post.get(j).getUserId().equals(users.get(i).getFollowed())){
                    posts.add(post.get(j));
                }
            }
        }

        ResponsePostDTO responsePostDTO = responsePostDTO(posts, userId);

        return responsePostDTO;

    }

    private Post requestDTOToEntity(RequestPostDTO dto) {
        Product product = new Product(dto.getProduct().getProduct_id(), dto.getProduct().getProduct_name(), dto.getProduct().getType(), dto.getProduct().getBrand(), dto.getProduct().getColor(), dto.getProduct().getNotes());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dto.getDate(), formatter);

        return new Post(product, dto.getUser_id(), postRepository.getCorrelativo(), localDate, dto.getCategory(), dto.getPrice(), false, 0d);
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

    private ResponsePostDTO responsePostDTO (List<Post> posts, int userId) {

        ProductDTO productDTO = new ProductDTO();
        ResponsePostDTO responsePostDTO = new ResponsePostDTO();

        List<RequestPostDTO> requestPostDTOS = new ArrayList<>();
        RequestPostDTO requestPostDTO = new RequestPostDTO();

        responsePostDTO.setUser_id(userId);
        for (Post post : posts) {

            requestPostDTO.setCategory(post.getCategory());
            requestPostDTO.setDate(post.getDate().toString());
            requestPostDTO.setPrice(post.getPrice());
            requestPostDTO.setUser_id(post.getUserId());

            productDTO.setProduct_id(post.getProduct().getProductId());
            productDTO.setProduct_name(post.getProduct().getProductName());
            productDTO.setBrand(post.getProduct().getBrand());
            productDTO.setColor(post.getProduct().getColor());
            productDTO.setNotes(post.getProduct().getProductName());
            productDTO.setType(post.getProduct().getType());

            requestPostDTO.setProduct(productDTO);

            requestPostDTOS.add(requestPostDTO);

            responsePostDTO.setPosts(requestPostDTOS);
        }

        return responsePostDTO;

    }
}
