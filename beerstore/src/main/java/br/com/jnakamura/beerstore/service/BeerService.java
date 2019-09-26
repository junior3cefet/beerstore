package br.com.jnakamura.beerstore.service;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.Beers;
import br.com.jnakamura.beerstore.service.rule.CreateBeerRule;
import br.com.jnakamura.beerstore.service.rule.DeleteBeerRule;
import br.com.jnakamura.beerstore.service.rule.EditBeerRule;
import br.com.jnakamura.beerstore.service.validation.BusinessValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

    @Autowired
    Beers beers;

    public BeerModel edit(Long id, BeerModel beerModel) {
        beerModel.setId(id);
        BusinessValidator validator = new BusinessValidator(new EditBeerRule().get(), beerModel, beers);
        validator.applyValidations();
        return beers.save(beerModel);
    }

    public void delete(Long id) {
        BeerModel beer = new BeerModel();
        beer.setId(id);
        BusinessValidator validator = new BusinessValidator(new DeleteBeerRule().get(), beer, beers);
        validator.applyValidations();
        beers.deleteById(id);
    }

    public BeerModel save(BeerModel beerModel) {
        BusinessValidator validator = new BusinessValidator(new CreateBeerRule().get(), beerModel, beers);
        validator.applyValidations();
        return beers.save(beerModel);
    }

}
