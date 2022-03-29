package com.construo.ff4j.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalcIn {

    @Positive
    private double numberA;
    @Positive
    private double numberB;
}
