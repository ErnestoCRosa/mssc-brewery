package erosa.springframework.msscbrewery.web.controller.v2;


import erosa.springframework.msscbrewery.services.BeerService;
import erosa.springframework.msscbrewery.services.v2.BeerServiceV2;
import erosa.springframework.msscbrewery.web.model.BeerDto;
import erosa.springframework.msscbrewery.web.model.v2.BeerDtoV2;
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
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;

    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@NotNull @PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }


    //using response entity to customize the header before returning
    @PostMapping
    public ResponseEntity handlePost(@Valid @NotNull @RequestBody BeerDtoV2 beerDto){

        BeerDtoV2 savedDto = beerService.saveNewBeer(beerDto);
        HttpHeaders header = new HttpHeaders();
        //todo add hostname to url
        header.add("Location", "/api/v1/beer"+ savedDto.getId().toString());

        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }

    //returning the http code through the response entity
    @PutMapping({"/{beerid}"})
    public ResponseEntity handleUpdate(@PathVariable("beerid") UUID beerId, @Valid @NotNull @RequestBody BeerDtoV2 beerDto){

        beerService.updateBeer(beerId,beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    //returning the http code through anotation
    @DeleteMapping({"/{beerid}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@NotNull @PathVariable("beerid") UUID beerID){
        beerService.deleteById(beerID);

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
