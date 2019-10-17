package br.com.jnakamura.beerstore.customer.endpoint;

import br.com.jnakamura.beerstore.customer.dto.CreateClientDTO;
import br.com.jnakamura.beerstore.customer.entity.Client;
import br.com.jnakamura.beerstore.customer.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class ClientEndPoint {

    @Autowired
    ClientRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity<Client> post(@RequestBody CreateClientDTO clientDTO) {

        Client client = Client.from(clientDTO);

        client.setAddresses(Client.addressFrom(clientDTO.getAddresses()));

        client = repository.save(client);

        return ResponseEntity.status(HttpStatus.OK).body(client);

    }

}


