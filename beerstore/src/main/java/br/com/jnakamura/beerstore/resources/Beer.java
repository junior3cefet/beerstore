package br.com.jnakamura.beerstore.resources;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.repository.BeerRepository;
import br.com.jnakamura.beerstore.service.BeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beers")
@Api(value = "Beer")
public class Beer {

    @Autowired
    BeerRepository beers;

    @Autowired
    BeerService beerService;

    @GetMapping
    @ApiOperation(
            value = "This endpoint serves a list of beers",
            response = BeerModel[].class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return a list of beers"),
            @ApiResponse(code = 500, message = "Internal error, please contact junior3cefet@gmail.com")
    })
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
