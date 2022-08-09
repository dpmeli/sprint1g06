package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.RequestPostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponsePostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMeliServiceE3 {

    List<ResponsePostDTO> responsePostDTOS;

    public void createPost(RequestPostDTO requestPostDTO) {

    }

    public List<ResponsePostDTO> getAllPost() {
        return responsePostDTOS;
    }
}
