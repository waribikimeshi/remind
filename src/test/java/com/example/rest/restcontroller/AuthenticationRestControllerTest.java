package com.example.rest.restcontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.rest.common.Consts;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationRestControllerTest {

    @Test
    void GETでidの3が取得されることを確認(@Autowired MockMvc mvc) throws Exception {

    	String testURL = "/" + Consts.REST_URL_BASE + Consts.REST_URL_AUTHENTICATION_GET + "/3";
    	
    	//http200
        mvc.perform(
                MockMvcRequestBuilders.get(testURL))
                .andExpect(MockMvcResultMatchers.status().isOk()
        );
        
    }

    @Test
    void GETでlistが取得されることを確認(@Autowired MockMvc mvc) throws Exception {

    	String testURL = "/" + Consts.REST_URL_BASE + Consts.REST_URL_AUTHENTICATION_LIST;
    	
    	//http200
        mvc.perform(
                MockMvcRequestBuilders.get(testURL))
                .andExpect(MockMvcResultMatchers.status().isOk()
        );
        
    }

    
    
}
