package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }


    //using response entity to customize the header before returning
    @PostMapping
    public ResponseEntity handlePost(@RequestBody BeerDto beerDto){

        BeerDto savedDto = beerService.saveNewBeer(beerDto);
        HttpHeaders header = new HttpHeaders();
        //todo add hostname to url
        header.add("Location", "/api/v1/beer"+ savedDto.getId().toString());

        return new ResponseEntity<>(header, HttpStatus.CREATED);
    }

    //returning the http code through the response entity
    @PutMapping({"/{beerid}"})
    public ResponseEntity handleUpdate(@PathVariable("beerid") UUID beerId, @RequestBody BeerDto beerDto){

        beerService.updateBeer(beerId,beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    //returning the http code through anotation
    @DeleteMapping({"/{beerid}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerid") UUID beerID){
        beerService.deleteById(beerID);

    }

    protected boolean canEqual(final Object other) {
        return other instanceof BeerController;
    }

}
