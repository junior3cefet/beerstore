package br.com.jnakamura.beerstore.service.validation;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.Beers;
import br.com.jnakamura.beerstore.service.exception.BeerAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class BeerAlreadyExistWIthSameNameAndTypeValidation implements BusinessValidation {

    @Override
    public void apply(BeerModel beerModel, Beers beers) {
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
