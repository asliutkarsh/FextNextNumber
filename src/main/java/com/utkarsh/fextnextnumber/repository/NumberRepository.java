package com.utkarsh.fextnextnumber.repository;

import com.utkarsh.fextnextnumber.entity.NumberTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NumberRepository extends JpaRepository<NumberTable,Long> {

    Optional<NumberTable> findFirstByCategoryCodeOrderByValueDesc(String categoryCode);
}
