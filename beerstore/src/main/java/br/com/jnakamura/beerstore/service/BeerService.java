package br.com.jnakamura.beerstore.service;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.BeerRepository;
import br.com.jnakamura.beerstore.service.exception.BeerNotFoundException;
import br.com.jnakamura.beerstore.service.rule.CreateBeerRule;
import br.com.jnakamura.beerstore.service.rule.DeleteBeerRule;
import br.com.jnakamura.beerstore.service.rule.EditBeerRule;
import br.com.jnakamura.beerstore.service.validation.BusinessValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    @Autowired
    BeerRepository beers;

    public BeerModel edit(Long id, BeerModel beerModel) {
        beerModel.setId(id);
        BusinessValidator validator = new BusinessValidator(new EditBeerRule().get(), beerModel, beers);
        validator.applyValidations();
        return beers.save(beerModel);
    }

    public void delete(Long id) {

        BeerModel beerModel = this.findById(id);
        beers.delete(beerModel);
    }

    public BeerModel save(BeerModel beerModel) {
        BusinessValidator validator = new BusinessValidator(new CreateBeerRule().get(), beerModel, beers);
        validator.applyValidations();
        return beers.save(beerModel);
    }

    public List<BeerModel> listAll() {
        return beers.findAll();
    }

    public BeerModel findById(long id) {

        Optional<BeerModel> beerFound = beers.findById(id);

        if(beerFound.isPresent()) {
            return beerFound.get();
        }
        throw new BeerNotFoundException();


    }
}
