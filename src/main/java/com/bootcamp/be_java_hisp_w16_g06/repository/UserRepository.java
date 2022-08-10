package com.bootcamp.be_java_hisp_w16_g06.repository;

import com.bootcamp.be_java_hisp_w16_g06.entity.User;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class UserRepository {

    private List<User> listUser = new ArrayList<>();

}
