package com.construo.ff4j.controller;

import com.construo.ff4j.model.request.CalcIn;
import com.construo.ff4j.model.response.CalcOut;
import org.ff4j.FF4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static com.construo.ff4j.constants.FF4JKeys.ENABLE_DIVISION_CALC;
import static com.construo.ff4j.constants.FF4JKeys.ENABLE_SUBTRACTION_CALC;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationControllerTest {

    @Autowired
    private FF4j ff4j;
    @Autowired
    private ApplicationController controller;

    @Test
    void TestMultiplicationDivisionCalculation() {
        CalcIn in = new CalcIn(60, 10);
        ResponseEntity<CalcOut> out = controller.multiplicationDivisionCalc(in);
        if (ff4j.getFeature(ENABLE_DIVISION_CALC).isEnable()) {
            assertEquals(6, out.getBody().getResult());
        } else {
            assertEquals(600, out.getBody().getResult());
        }
    }

    @Test
    void TestAdditionSubtractionCalculation() {
        CalcIn in = new CalcIn(60, 10);
        ResponseEntity<CalcOut> out = controller.additionSubtractionCalc(in);
        if (ff4j.getFeature(ENABLE_SUBTRACTION_CALC).isEnable()) {
            assertEquals(50, out.getBody().getResult());
        } else {
            assertEquals(70, out.getBody().getResult());
        }
    }
}