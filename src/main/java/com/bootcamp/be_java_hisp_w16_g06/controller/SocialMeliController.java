package com.bootcamp.be_java_hisp_w16_g06.controller;


import com.bootcamp.be_java_hisp_w16_g06.dto.FollowIdDto;
import com.bootcamp.be_java_hisp_w16_g06.dto.RequestPostDTO;
import com.bootcamp.be_java_hisp_w16_g06.dto.Response;
import com.bootcamp.be_java_hisp_w16_g06.dto.UserDTO;
import com.bootcamp.be_java_hisp_w16_g06.service.ISocialMeliServiceE3;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE1;
import com.bootcamp.be_java_hisp_w16_g06.service.SocialMeliServiceE21;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialMeliController {

    @Autowired
    ISocialMeliServiceE3 socialMaMeliServiceE3;

    @GetMapping
    public ResponseEntity<Response> test() {
        return new ResponseEntity<>(new Response("Mensaje Aceptado", 200), HttpStatus.valueOf(200));
    }

    //US 0001: Poder realizar la acción de “Follow” (seguir) a un determinado vendedor
    @Autowired
    SocialMeliServiceE1 serviceE1;

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity US001(@PathVariable int userId, @PathVariable int userIdToFollow) {
        try {
            serviceE1.followUser(new FollowIdDto(userId, userIdToFollow));
            return new ResponseEntity<>(new Response("User Followed Successful", 200), HttpStatus.valueOf(200));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //SocialMeliServiceE2 socialMeliServiceE2 = new SocialMeliServiceE2();
    @Autowired
    SocialMeliServiceE21 service2;


    //US 0002: Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<List<UserDTO>> US002(@PathVariable int userId) {
        return new ResponseEntity<>(service2.userFollowers(userId), HttpStatus.ACCEPTED);
        // return new ResponseEntity<>(socialMeliServiceE2.userFollowers(userId), HttpStatus.ACCEPTED);
    }


    //US 0003: Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
    @GetMapping("/users/{userId}/followers/list")
    public void US003() {

    }

    //US 0004: Obtener un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)
    @GetMapping("/users/{userId}/followed/list")
    public void US004() {

    }

    //US 0005: Dar de alta una nueva publicación
    @PostMapping("/products/post")

    public ResponseEntity<Response> US005(@RequestBody RequestPostDTO dto) {
        socialMaMeliServiceE3.createPost(dto);
        return new ResponseEntity<>(new Response("", 200), HttpStatus.valueOf(200));
    }

    //US 0006: Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las
    // últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).
    @PostMapping("/products/followed/{userId}/list")
    public void US006() {

    }

    //US 0007: Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public void US007() {

    }

    //US 0008: Ordenamiento alfabético ascendente y descendente
    //      users/{UserID}/followers/list?order=name_asc
    //      users/{UserID}/followers/list?order=name_desc
    //      users/{UserID}/followed/list?order=name_asc
    //      users/{UserID}/followed/list?order=name_desc
    //  *Nota: Este ordenamiento aplica solo para US-003 y US-004.
    @GetMapping("/US008") //Cambiar Endpoint
    public void US008() {

    }

    //US 0009: Ordenamiento por fecha ascendente y descendente
    //     products/followed/{userId}/list?order=date_asc
    //     products/followed/{userId}/list?order=date_desc
    //  *Nota: Este ordenamiento aplica solo para la US-006
    @GetMapping("US009") //Cambiar Endpoint
    public void US009() {

    }

    /* B_Requerimientos_incrementales */

    //US 0010: Llevar a cabo la publicación de un nuevo producto en promoción
    @PostMapping("/products/promo-post")
    public void US0010() {

    }

    //US 0011: Obtener la cantidad de productos en promoción de un determinado vendedor
    @GetMapping("/products/promo-post/count?user_id={userId}")
    public void US0011() {

    }

    /* C_Ejemplo_Requerimiento_Bonus */

    //US 0012: Obtener un listado de todos los productos en promoción de un determinado vendedor
    @GetMapping("/products/promo-post/list?user_id={userId}")
    public void US0012() {

    }

}
