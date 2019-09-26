package br.com.jnakamura.beerstore.service.validation;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.Beers;

public interface BusinessValidation {

    void apply(BeerModel beerModel, Beers repository);

}
