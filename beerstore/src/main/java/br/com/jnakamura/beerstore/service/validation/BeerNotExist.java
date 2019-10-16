package br.com.jnakamura.beerstore.service.validation;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.BeerRepository;
import br.com.jnakamura.beerstore.service.exception.BeerNotFoundException;

import java.util.Optional;

public class BeerNotExist implements BusinessValidation{

    @Override
    public void apply(BeerModel beerModel, BeerRepository repository) {

        Optional<BeerModel> beerModelOriginal = repository.findById(beerModel.getId());

        if(!beerModelOriginal.isPresent()) {
            throw new BeerNotFoundException();
        }

    }
}
