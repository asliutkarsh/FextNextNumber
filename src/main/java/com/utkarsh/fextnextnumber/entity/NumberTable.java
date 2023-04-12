package com.utkarsh.fextnextnumber.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "number_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NumberTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "value")
    private Long value;


}
