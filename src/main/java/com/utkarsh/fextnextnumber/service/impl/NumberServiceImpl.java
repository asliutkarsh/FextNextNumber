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
        Optional<NumberTable> optionalNumberEntity = numberRepository.findFirstByCategoryCodeOrderByValueDesc(categoryCode);
        Long fetchedValue = optionalNumberEntity.map(NumberTable::getValue).orElse(0L);

        Long nextValue = generateNextNumber(fetchedValue);

        NumberTable nextNumber =  new NumberTable(categoryCode, nextValue);

        numberRepository.save(nextNumber);

        Thread.sleep(5000);

        return new NumberResponse(fetchedValue, nextValue);
    }

    private Long generateNextNumber(Long fetchedValue) {
        Long nextValue = fetchedValue + 1;

        while (!isDigitSumEqualOne(nextValue)) {
            nextValue++;
        }

        return nextValue;
    }

    private boolean isDigitSumEqualOne(Long value) {
        Long sum = 0L;
        while (value > 0) {
            sum += value % 10;
            value /= 10;

            if (value ==0 && sum > 9){
                value =sum;
                sum = 0L;
            }
        }

        return sum == 1;
    }
}

