package br.com.jnakamura.beerstore.repository;

import br.com.jnakamura.beerstore.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Orders extends JpaRepository<OrderModel, Long> {


}
