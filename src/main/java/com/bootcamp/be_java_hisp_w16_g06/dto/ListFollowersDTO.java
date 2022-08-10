package com.bootcamp.be_java_hisp_w16_g06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListFollowersDTO {

    private int user_id;
    private String user_name;
    private List<FollowIdDto> followers;

    public ListFollowersDTO(int userId, String userName, List<FollowIdDto> followers) {
    }
}
