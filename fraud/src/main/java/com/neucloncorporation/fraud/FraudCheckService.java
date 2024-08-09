package com.neucloncorporation.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

//    public FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository){
//        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
//    }
    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

    public List<FraudCheckHistory> getAllFraud(){
        return fraudCheckHistoryRepository.findAll();
    }
}
