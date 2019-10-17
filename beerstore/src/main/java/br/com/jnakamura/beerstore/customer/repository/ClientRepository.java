package br.com.jnakamura.beerstore.customer.repository;

import br.com.jnakamura.beerstore.customer.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client save(Client client);

}
