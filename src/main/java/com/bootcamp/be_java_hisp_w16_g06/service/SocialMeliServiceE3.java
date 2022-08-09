package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.RequestPostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponsePostDTO;
import com.bootcamp.be_java_hisp_w16_g06.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMeliServiceE3 implements ISocialMeliServiceE3 {

    @Autowired
    PostRepository postRepository;

    @Override
    public void createPost(RequestPostDTO requestPostDTO) {

    }

    @Override
    public List<ResponsePostDTO> getAllPost() {
        return null;
    }

}
