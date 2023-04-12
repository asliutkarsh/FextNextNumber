package com.utkarsh.fextnextnumber.service.impl;

import com.utkarsh.fextnextnumber.dto.NumberResponse;
import com.utkarsh.fextnextnumber.repository.NumberRepository;
import com.utkarsh.fextnextnumber.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberServiceImpl implements NumberService {

    @Autowired
    private NumberRepository numberRepository;

    @Override
    public NumberResponse fetchNextNumber(String categoryCode) {
        return null;
    }
}

