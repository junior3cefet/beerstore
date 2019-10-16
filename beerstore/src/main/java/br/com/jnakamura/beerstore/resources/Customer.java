package br.com.jnakamura.beerstore.resources;

import br.com.jnakamura.beerstore.models.CustomerModel;
import br.com.jnakamura.beerstore.repository.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class Customer {

    @Autowired
    private Customers customersRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerModel> getCustomers() {

        return customersRepository.findAll(Sort.by("name"));

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerModel createCustomer(@Valid @RequestBody CustomerModel newCustomer) {

        return customersRepository.save(newCustomer);
    }


}
