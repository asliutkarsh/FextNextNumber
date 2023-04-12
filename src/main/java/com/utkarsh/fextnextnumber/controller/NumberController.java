package com.utkarsh.fextnextnumber.controller;

import com.utkarsh.fextnextnumber.dto.CategoryCodeRequest;
import com.utkarsh.fextnextnumber.dto.NumberResponse;
import com.utkarsh.fextnextnumber.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/FetchNextNumber")
@RequiredArgsConstructor
public class NumberController {

    private final NumberService numberService;

    @PostMapping
    public ResponseEntity<NumberResponse> fetchNextNumber(@RequestBody CategoryCodeRequest request) throws InterruptedException {
        String categoryCode = request.getCategoryCode();

        NumberResponse numberResponse = numberService.fetchNextNumber(categoryCode);

        return ResponseEntity.ok(numberResponse);
    }
}
