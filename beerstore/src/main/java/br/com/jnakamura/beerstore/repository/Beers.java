package br.com.jnakamura.beerstore.repository;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.models.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Beers extends JpaRepository<BeerModel, Long> {

    Optional<BeerModel> findById(Long id);

    Optional<BeerModel> findByNameAndType(String name, BeerType type);
}
