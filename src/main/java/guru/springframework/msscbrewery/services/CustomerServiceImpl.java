package guru.springframework.msscbrewery.services;


import guru.springframework.msscbrewery.web.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Override
    public CustomerDTO getCustomerByID(UUID id) {
        return CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName("Miguel Saraiva")
                .build();
    }
}
