package testUnitario.controller;

import com.bootcamp.be_java_hisp_w16_g06.controller.SocialMeliController;
import com.bootcamp.be_java_hisp_w16_g06.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SocialMeliControllerE1 {

    @InjectMocks
    SocialMeliController socialMeliController;

    @Test
    public void testHealth_OK() {
        int statusCodeTest = 200;
        HttpStatus httpStatus = HttpStatus.valueOf(statusCodeTest);
        ResponseDTO responseDTO = new ResponseDTO(httpStatus.getReasonPhrase(), httpStatus.value());
        ResponseEntity response = new ResponseEntity<>(responseDTO, httpStatus);

        //SocialMeliController meliController = mock(SocialMeliController.class);
        //when(socialMeliController.test(statusCodeTest)).thenReturn(response);
        //System.out.println(response);

        assertEquals(HttpStatus.OK, socialMeliController.test(statusCodeTest).getStatusCode());
    }
}
