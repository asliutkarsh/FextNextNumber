package com.utkarsh.fextnextnumber.service;

import com.utkarsh.fextnextnumber.dto.NumberResponse;
import com.utkarsh.fextnextnumber.entity.NumberTable;
import com.utkarsh.fextnextnumber.repository.NumberRepository;
import com.utkarsh.fextnextnumber.service.impl.NumberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NumberServiceTest {


    @InjectMocks
    private NumberServiceImpl numberService;

    @Mock
    private NumberRepository numberRepository;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        numberService = new NumberServiceImpl(numberRepository);
    }


    @DisplayName("Junit test for fetchNextNumber method")
    @Test
    void fetchNextNumber() throws InterruptedException{
        String categoryCode = "test";
        Long oldValue = 10L;
        Long newValue = 19L;
        Optional<NumberTable> optionalNumberEntity = Optional.of(new NumberTable(categoryCode, oldValue));
        NumberResponse expectedResponse = new NumberResponse(oldValue, newValue);

        when(numberRepository.findFirstByCategoryCodeOrderByValueDesc(anyString())).thenReturn(optionalNumberEntity);

        NumberResponse actualResponse = numberService.fetchNextNumber(categoryCode);


        Assertions.assertEquals(expectedResponse, actualResponse);

    }

    @DisplayName("Junit test when no categoryCode is found")
    @Test
    void testFetchNextNumber_NoPreviousValue() throws InterruptedException {
        String categoryCode = "test";
        Long newValue = 1L;
        Optional<NumberTable> optionalNumberEntity = Optional.empty();
        NumberResponse expectedResponse = new NumberResponse(0L, newValue);

        when(numberRepository.findFirstByCategoryCodeOrderByValueDesc(anyString())).thenReturn(optionalNumberEntity);

        NumberResponse actualResponse = numberService.fetchNextNumber(categoryCode);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

}