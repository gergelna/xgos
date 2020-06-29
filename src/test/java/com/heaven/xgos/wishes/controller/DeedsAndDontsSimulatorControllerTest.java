package com.heaven.xgos.wishes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeedsAndDontsSimulatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getScore() throws Exception {
        // Given
        int kidId = 3;
        int itemId = 4;
        int expectedScore = 100;

        // Execute the GET request
        mockMvc.perform(get("/api/getScore?kidId=" + kidId + "&itemId=" + itemId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score", is(expectedScore)))
        ;
    }
}