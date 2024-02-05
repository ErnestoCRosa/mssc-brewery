package erosa.springframework.msscbrewery.services;


import erosa.springframework.msscbrewery.web.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {


    @Override
    public CustomerDTO getCustomerByID(UUID id) {
        return CustomerDTO.builder()
                .id(id)
                .customerName("Miguel Saraiva")
                .build();
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        return CustomerDTO.builder()
                .customerName("Miguel Saraiva")
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {
        //TODO connection to backend
        log.debug("Updating customer");
    }

    @Override
    public void deletebyId(UUID customerId) {
        //TODO connection to backend
        log.debug("Deleting customer");
    }
}
