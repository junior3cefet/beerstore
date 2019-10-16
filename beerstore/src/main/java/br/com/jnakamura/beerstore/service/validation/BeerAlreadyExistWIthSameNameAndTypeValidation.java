package br.com.jnakamura.beerstore.service.validation;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.BeerRepository;
import br.com.jnakamura.beerstore.service.exception.BeerAlreadyExistException;

import java.util.Optional;

public class BeerAlreadyExistWIthSameNameAndTypeValidation implements BusinessValidation {

    @Override
    public void apply(BeerModel beerModel, BeerRepository beers) {
        Optional<BeerModel> findBeer = beers.findByNameAndType(beerModel.getName(), beerModel.getType());

        if(findBeer.isPresent()) {

            if(beerModel.isNew()) {
                throw new BeerAlreadyExistException();
            }

            if(beerModel.isAlreadyExist() && !beerModel.getId().equals(findBeer.get().getId())) {
                throw new BeerAlreadyExistException();
            }

        }
    }
}
