package com.neucloncorporation.fraud;

import com.neucloncorporation.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {
    private final FraudCheckService fraudCheckService;

//    public FraudController(FraudCheckService fraudCheckService) {
//        this.fraudCheckService = fraudCheckService;
//    }

    @GetMapping(path="{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerID){
//        log.info("customer fraud status {}", customerID);
       boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
       return new FraudCheckResponse(isFraudulentCustomer);

    }

}
