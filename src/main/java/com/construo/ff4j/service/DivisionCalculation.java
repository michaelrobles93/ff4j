package com.construo.ff4j.service;

import com.construo.ff4j.model.request.CalcIn;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class DivisionCalculation implements ICalcService {

    @Override
    public double calc(CalcIn in) {
        log.info("Division calculation ON");
        return in.getNumberA() / in.getNumberB();
    }
}
