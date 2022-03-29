package com.construo.ff4j.controller;

import com.construo.ff4j.model.request.CalcIn;
import com.construo.ff4j.model.response.CalcOut;
import com.construo.ff4j.service.ICalcService;
import lombok.extern.log4j.Log4j2;
import org.ff4j.FF4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.construo.ff4j.constants.FF4JKeys.ENABLE_SUBTRACTION_CALC;

@RestController("/calc")
@Log4j2
public class ApplicationController {

    private final ICalcService service;
    private final FF4j ff4j;

    @Autowired
    public ApplicationController(ICalcService service, FF4j ff4j) {
        this.service = service;
        this.ff4j = ff4j;
    }

    @PostMapping(value = "/multiplicationDivision",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalcOut> multiplicationDivisionCalc(@RequestBody @Valid CalcIn in) {
        return ResponseEntity.ok(CalcOut.builder().result(service.calc(in)).build());
    }

    @PostMapping(value = "/additionSubtraction",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalcOut> additionSubtractionCalc(@RequestBody @Valid CalcIn in) {
        if (ff4j.getFeature(ENABLE_SUBTRACTION_CALC).isEnable()) {
            log.info("Subtraction calculation ON");
            return ResponseEntity.ok(CalcOut.builder().result(service.subtractionCalc(in)).build());
        } else {
            log.info("Addition calculation ON");
            return ResponseEntity.ok(CalcOut.builder().result(service.additionCalc(in)).build());
        }
    }

}
