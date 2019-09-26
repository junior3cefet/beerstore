package br.com.jnakamura.beerstore.service.rule;

import br.com.jnakamura.beerstore.service.validation.BusinessValidation;

import java.util.List;

public interface BeerRule {

    List<BusinessValidation> get();

}
