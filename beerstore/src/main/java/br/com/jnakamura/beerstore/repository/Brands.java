package br.com.jnakamura.beerstore.repository;


import br.com.jnakamura.beerstore.models.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Brands extends JpaRepository<BrandModel, Long> {
}
