package com.bootcamp.be_java_hisp_w16_g06.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String userName;
    private List<Follow> followed;
    private List<Follow> followers;
}
