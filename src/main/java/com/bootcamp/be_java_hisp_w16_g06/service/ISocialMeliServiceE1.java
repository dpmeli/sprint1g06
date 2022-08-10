package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.FollowIdDto;
import com.bootcamp.be_java_hisp_w16_g06.dto.Response;
import com.bootcamp.be_java_hisp_w16_g06.dto.UserDTO;

import java.util.List;

public interface ISocialMeliServiceE1 {
    Response followUser(FollowIdDto followIdDto);

    Response unFollowUser(FollowIdDto followIdDto);

    boolean findById(int userId);

}
