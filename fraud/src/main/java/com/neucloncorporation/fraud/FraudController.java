package com.neucloncorporation.fraud;

import com.neucloncorporation.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
       boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
       return new FraudCheckResponse(isFraudulentCustomer);

    }

    @GetMapping
    public List<FraudCheckHistory> getFraudList(){
        return fraudCheckService.getAllFraud();
    }
}
