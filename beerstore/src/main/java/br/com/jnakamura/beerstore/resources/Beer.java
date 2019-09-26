package br.com.jnakamura.beerstore.resources;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.Beers;
import br.com.jnakamura.beerstore.service.BeerService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class Beer {

    @Autowired
    Beers beers;

    @Autowired
    BeerService beerService;

    @GetMapping
    public List<BeerModel> getAll() {
        return beers.findAll(new Sort(Sort.Direction.ASC, "id")) ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerModel newBeer(@Valid @RequestBody BeerModel beerModel) {
        return beerService.save(beerModel);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BeerModel updateBeer(@PathVariable Long id, @RequestBody BeerModel beerModel)  {
        return beerService.edit(id, beerModel);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable Long id)  {
        beerService.delete(id);
    }


}
