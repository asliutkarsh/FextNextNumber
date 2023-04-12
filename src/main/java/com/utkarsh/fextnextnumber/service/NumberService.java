package com.utkarsh.fextnextnumber.service;

import com.utkarsh.fextnextnumber.dto.NumberResponse;

public interface NumberService {

    NumberResponse fetchNextNumber(String categoryCode);
}
