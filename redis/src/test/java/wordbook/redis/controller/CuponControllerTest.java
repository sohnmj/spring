package wordbook.redis.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import wordbook.redis.entity.GetCuponDTO;
import wordbook.redis.service.CuponService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CuponController.class)
class CuponControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CuponService cuponService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getCupon() throws Exception {

        GetCuponDTO dto = new GetCuponDTO();
        dto.setUsername("admin");
        when(cuponService.getCupon(any())).thenReturn(true);

        mockMvc.perform(post("/api/v1/cupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }


}