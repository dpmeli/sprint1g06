package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.RequestPostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponsePostDTO;
import com.bootcamp.be_java_hisp_w16_g06.entity.Post;
import com.bootcamp.be_java_hisp_w16_g06.entity.Product;
import com.bootcamp.be_java_hisp_w16_g06.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SocialMeliServiceE3 implements ISocialMeliServiceE3 {

    @Autowired
    PostRepository postRepository;

    @Override
    public void createPost(RequestPostDTO requestPostDTO) {
        List<Post> lstRepository = postRepository.getPosts();
        lstRepository.add(requestDTOToEntity(requestPostDTO));
        postRepository.setPosts(lstRepository);
    }

    @Override
    public List<ResponsePostDTO> getAllPost(int userId) {
        return null;
    }

    private Post requestDTOToEntity(RequestPostDTO dto)
    {
        Product product = new Product(dto.getProduct().getProduct_id(), dto.getProduct().getProduct_name(),dto.getProduct().getType(), dto.getProduct().getBrand(), dto.getProduct().getColor(), dto.getProduct().getNotes());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dto.getDate(), formatter);

        return  new Post(product,dto.getUser_id(), postRepository.getCorrelativo(), localDate,dto.getCategory(),dto.getPrice(),false, 0d);
    }
}
