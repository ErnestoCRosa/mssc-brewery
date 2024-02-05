package erosa.springframework.msscbrewery.services;

import erosa.springframework.msscbrewery.web.model.CustomerDTO;

import java.util.UUID;

public interface CustomerService {


    CustomerDTO getCustomerByID(UUID id);
    
    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);

    void updateCustomer(UUID customerId, CustomerDTO customerDTO);

    void deletebyId(UUID customerId);
}
