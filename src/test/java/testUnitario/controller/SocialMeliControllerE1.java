package testUnitario.controller;

import com.bootcamp.be_java_hisp_w16_g06.controller.SocialMeliController;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SocialMeliControllerE1 {

    @InjectMocks
    SocialMeliController socialMeliController;

    @Test
    public void testHealth_OK() {
        HttpStatus httpStatus = HttpStatus.valueOf(200);
        ResponseDTO responseDTO = new ResponseDTO(httpStatus.getReasonPhrase(), httpStatus.value());

        //when(socialMeliController.test(200)).thenReturn(new ResponseEntity<>(responseDTO, httpStatus));

        assertEquals(HttpStatus.OK, socialMeliController.test(200).getStatusCode());
    }
}
