package com.utkarsh.fextnextnumber.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberResponse {

    private Long oldValue;
    private Long newValue;

}
