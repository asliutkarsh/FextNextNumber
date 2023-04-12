package com.utkarsh.fextnextnumber.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utkarsh.fextnextnumber.dto.CategoryCodeRequest;
import com.utkarsh.fextnextnumber.dto.NumberResponse;
import com.utkarsh.fextnextnumber.service.NumberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class NumberControllerTest {


    private MockMvc mockMvc;

    @Mock
    private NumberService numberService;

    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        NumberController numberController = new NumberController(numberService);
        mockMvc = MockMvcBuilders.standaloneSetup(numberController).build();
        objectMapper = new ObjectMapper();

    }

    @Test
    void testFetchNextNumber() throws Exception{
        String categoryCode = "test";
        Long oldValue = 10L;
        Long newValue = 19L;
        NumberResponse expectedResponse = new NumberResponse(oldValue, newValue);
        CategoryCodeRequest request = new CategoryCodeRequest(categoryCode);

        when(numberService.fetchNextNumber(anyString())).thenReturn(expectedResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/FetchNextNumber")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        NumberResponse actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), NumberResponse.class);
        assert expectedResponse.equals(actualResponse);
    }
}