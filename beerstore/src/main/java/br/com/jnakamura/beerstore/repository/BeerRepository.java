package br.com.jnakamura.beerstore.repository;

import br.com.jnakamura.beerstore.models.BeerModel;
import br.com.jnakamura.beerstore.models.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerRepository extends JpaRepository<BeerModel, Long> {


    Optional<BeerModel> findByNameAndType(String name, BeerType type);
}
