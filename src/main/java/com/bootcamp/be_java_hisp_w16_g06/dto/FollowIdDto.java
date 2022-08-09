package com.bootcamp.be_java_hisp_w16_g06.dto;

import lombok.Data;

@Data
public class FollowIdDto {
    private int userId;
    private int userIdToFollow;
}
