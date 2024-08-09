package com.neucloncorporation.customer;

import com.neucloncorporation.clients.fraud.FraudCheckResponse;
import com.neucloncorporation.clients.fraud.FraudClient;
import com.neucloncorporation.clients.notification.NotificationClient;
import com.neucloncorporation.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
//    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);

//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("https://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//                );

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) throw new IllegalStateException("fraudster");

//        send notification
        notificationClient.sendNotification( new NotificationRequest(
                   customer.getId(),
                   customer.getEmail(),
                   String.format("Hi %s, welcome to neucloncorporation....", customer.getFirstName())
           ));

    }
}
