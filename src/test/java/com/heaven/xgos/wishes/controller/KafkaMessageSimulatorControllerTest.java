package com.heaven.xgos.wishes.controller;

import com.heaven.xgos.wishes.listener.KafkaListener;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class KafkaMessageSimulatorControllerTest {

    @MockBean
    KafkaListener mockedKafkaListener;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/kafka")
    public void kafkaMessageReceived() throws Exception {
        int kidId = 3;
        int itemId = 4;
        // Setup our mocked service
        doNothing().when(mockedKafkaListener).messageReceived(any());

        // Execute the GET request
        mockMvc.perform(get("/api/kafka?kidId=" + kidId + "&itemId=" + itemId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kidId", is(kidId)))
                .andExpect(jsonPath("$.itemId", is(itemId)))
        ;

    }
}