package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.RequestPostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponsePostDTO;

import java.util.List;

public interface ISocialMeliServiceE3 {

    void createPost(RequestPostDTO requestPostDTO);

    List<ResponsePostDTO> getAllPost(int userId);

}
