package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDTO;

import java.util.UUID;

public interface CustomerService {


    CustomerDTO getCustomerByID(UUID id);
}
