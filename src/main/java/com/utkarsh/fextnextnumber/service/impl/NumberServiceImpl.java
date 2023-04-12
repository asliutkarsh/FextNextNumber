package com.utkarsh.fextnextnumber.service.impl;

import com.utkarsh.fextnextnumber.dto.NumberResponse;
import com.utkarsh.fextnextnumber.entity.NumberTable;
import com.utkarsh.fextnextnumber.repository.NumberRepository;
import com.utkarsh.fextnextnumber.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NumberServiceImpl implements NumberService {

    private final NumberRepository numberRepository;

    @Override
    public NumberResponse fetchNextNumber(String categoryCode) throws InterruptedException {
        Optional<NumberTable> optionalNumberEntity = numberRepository.findByCategoryCode(categoryCode);
        Long fetchedValue = optionalNumberEntity.map(NumberTable::getValue).orElse(0L);

        Long nextValue = generateNextNumber(fetchedValue);
        NumberTable nextNumber;

        if (optionalNumberEntity.isEmpty()){
            nextNumber =  new NumberTable(categoryCode, nextValue);
            numberRepository.save(nextNumber);
        }else {
            nextNumber =  new NumberTable(optionalNumberEntity.get().getId(),categoryCode, nextValue);
            numberRepository.save(nextNumber);
        }

        Thread.sleep(5000);

        return new NumberResponse(fetchedValue, nextValue);
    }

    private Long generateNextNumber(Long fetchedValue) {
        Long nextValue = fetchedValue + 1; //greater than the fetched Value

        while (!isDigitSumEqualOne(nextValue)) { //the number is the smallest next available number
            nextValue++;
        }

        return nextValue;
    }

    private boolean isDigitSumEqualOne(Long value) {
        //sum of the individual digits become 1 [for example if fetched number is 10, then
        //the next number should be 19 as 1+9 = 10 => 1+0 = 1]


        if (value == 0) return false;
        if (value % 9 == 0) return false;
        return value % 9==1;
    }
}

