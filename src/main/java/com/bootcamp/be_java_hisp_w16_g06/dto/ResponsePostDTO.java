package com.bootcamp.be_java_hisp_w16_g06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePostDTO extends RequestPostDTO {

    private int post_id;

}
