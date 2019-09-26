package br.com.jnakamura.beerstore.service.rule;

import br.com.jnakamura.beerstore.service.validation.BeerAlreadyExistWIthSameNameAndTypeValidation;
import br.com.jnakamura.beerstore.service.validation.BeerNotExist;
import br.com.jnakamura.beerstore.service.validation.BusinessValidation;

import java.util.Arrays;
import java.util.List;

public class EditBeerRule implements BeerRule {
    @Override
    public List<BusinessValidation> get() {
        return Arrays.asList(
                new BeerNotExist(),
                new BeerAlreadyExistWIthSameNameAndTypeValidation());
    }
}
