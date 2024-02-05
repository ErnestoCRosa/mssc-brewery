package erosa.springframework.msscbrewery.services.v2;

import erosa.springframework.msscbrewery.web.model.BeerDto;
import erosa.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import erosa.springframework.msscbrewery.web.model.v2.BeerStyleEnum;

import java.util.UUID;

import static erosa.springframework.msscbrewery.web.model.v2.BeerStyleEnum.IPA;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2{

    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(IPA)
                .build();
    }

    @Override
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
        return BeerDtoV2.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
        //TODO connection to backend
        log.debug("Beer updating");
    }

    @Override
    public void deleteById(UUID beerID) {
        //TODO connection to backend
        log.debug("Beer deleted");

    }
}
