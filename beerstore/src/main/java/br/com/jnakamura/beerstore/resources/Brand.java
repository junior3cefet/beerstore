package br.com.jnakamura.beerstore.resources;

import br.com.jnakamura.beerstore.models.BrandModel;
import br.com.jnakamura.beerstore.repository.Brands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class Brand {

    @Autowired
    private Brands brandsRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BrandModel> getBrands() {
        return brandsRepository.findAll(Sort.by("name"));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BrandModel createBrand(@RequestBody BrandModel newBrand) {
        return brandsRepository.save(newBrand);
    }

    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BrandModel updateBrand(@PathVariable Long id, @RequestBody BrandModel updBrand) {

        BrandModel brandModel = brandsRepository.getOne(id);

        updBrand.setId(brandModel.getId());

        return brandsRepository.save(updBrand);

    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBrand(@PathVariable Long id) {
        BrandModel brandModel = brandsRepository.getOne(id);
        brandsRepository.delete(brandModel);
    }


}
