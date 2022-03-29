package com.construo.ff4j.service;

import com.construo.ff4j.model.request.CalcIn;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@Log4j2
public class MultiplicationCalculation implements ICalcService {

    @Override
    public double calc(CalcIn in) {
        log.info("Multiplication calculation ON");
        return in.getNumberA() * in.getNumberB();
    }
}
