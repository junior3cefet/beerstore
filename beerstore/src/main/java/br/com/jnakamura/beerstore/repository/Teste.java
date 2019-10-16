package br.com.jnakamura.beerstore.repository;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.models.BeerType;
import br.com.jnakamura.beerstore.service.exception.BeerNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Teste {

    public static void main(String[] args) {

        BeerModel newBeer = new BeerModel();
        newBeer.setName("teste");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal(5));
        newBeer.setId(10L);

        List<BeerModel> beerModels = Arrays.asList(newBeer);

        List<BeerModel> lista = beerModels.stream().filter(beer -> beer.getId() == 10L).collect(Collectors.toList());

        lista.forEach(value -> System.out.println(value));



    }
}
