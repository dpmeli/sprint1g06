package com.bootcamp.be_java_hisp_w16_g06.service;

import com.bootcamp.be_java_hisp_w16_g06.dto.FollowIdDto;

public interface ISocialMeliServiceE1 {
    void followUser(FollowIdDto followIdDto);
    boolean findById(int userId);
    void unfollowUser(FollowIdDto followIdDto);
}
