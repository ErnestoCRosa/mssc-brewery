package erosa.springframework.msscbrewery.web.controller;

import erosa.springframework.msscbrewery.services.CustomerService;
import erosa.springframework.msscbrewery.web.model.CustomerDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    public final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/{customerID}"})
    public ResponseEntity<CustomerDTO> getCustomer(@NotNull @PathVariable("customerID") UUID customerID){
        return new ResponseEntity<>(customerService.getCustomerByID(UUID.randomUUID()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody CustomerDTO customerDTO){
        CustomerDTO savedDTO = customerService.saveNewCustomer(customerDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/customer" + savedDTO.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{customerid}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@NotNull @PathVariable("customerid") UUID customerId, @Valid @NotNull @RequestBody CustomerDTO customerDTO){

        customerService.updateCustomer(customerId, customerDTO);

    }

    @DeleteMapping({"/{customerid}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@NotNull @PathVariable("customerid") UUID customerId){
        customerService.deletebyId(customerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
