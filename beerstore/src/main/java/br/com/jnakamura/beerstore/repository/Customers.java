package br.com.jnakamura.beerstore.repository;

import br.com.jnakamura.beerstore.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customers extends JpaRepository<CustomerModel, Long> {
}
