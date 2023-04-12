package com.utkarsh.fextnextnumber.controller;

import com.utkarsh.fextnextnumber.dto.CategoryCodeRequest;
import com.utkarsh.fextnextnumber.dto.NumberResponse;
import com.utkarsh.fextnextnumber.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/FetchNextNumber")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @PostMapping
    public ResponseEntity<NumberResponse> fetchNextNumber(@RequestBody CategoryCodeRequest request) {
        String categoryCode = request.getCategoryCode();

        NumberResponse numberResponse = numberService.fetchNextNumber(categoryCode);

        return ResponseEntity.ok(numberResponse);
    }
}
