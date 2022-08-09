package com.bootcamp.be_java_hisp_w16_g06.repository;

import com.bootcamp.be_java_hisp_w16_g06.entity.Post;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class PostRepository {

    private List<Post> posts;

    public PostRepository() {
        this.posts = new ArrayList<>();
    }

}
