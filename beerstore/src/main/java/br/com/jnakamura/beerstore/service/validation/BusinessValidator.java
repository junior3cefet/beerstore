package br.com.jnakamura.beerstore.service.validation;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.BeerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BusinessValidator implements BusinessValidatorHandle{

    private final List<BusinessValidation> validations;
    private final BeerModel beerModel;
    private BeerRepository beersRepository;

    @Override
    public void applyValidations() {
        this.validations.forEach(validation -> {
            validation.apply(beerModel, beersRepository);
        });
    }


}

