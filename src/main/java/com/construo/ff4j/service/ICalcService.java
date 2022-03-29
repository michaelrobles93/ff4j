package com.construo.ff4j.service;

import com.construo.ff4j.model.request.CalcIn;
import org.ff4j.aop.Flip;

import static com.construo.ff4j.constants.FF4JKeys.ENABLE_DIVISION_CALC;

@Flip(name = ENABLE_DIVISION_CALC, alterClazz = DivisionCalculation.class)
public interface ICalcService {

    double calc(CalcIn in);

    default double additionCalc(CalcIn in) {
        return in.getNumberA() + in.getNumberB();
    }

    default double subtractionCalc(CalcIn in) {
        return in.getNumberA() - in.getNumberB();
    }
}
