package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CostumerController {

    public final CustomerService customerService;

    public CostumerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/{customerID}"})
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerID") UUID customerID){
        return new ResponseEntity<>(customerService.getCustomerByID(UUID.randomUUID()), HttpStatus.OK);
    }

}
