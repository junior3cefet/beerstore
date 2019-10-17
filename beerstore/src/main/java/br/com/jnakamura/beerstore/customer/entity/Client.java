package br.com.jnakamura.beerstore.customer.entity;

import br.com.jnakamura.beerstore.customer.dto.CreateAddressClientDTO;
import br.com.jnakamura.beerstore.customer.dto.CreateClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "client_id")
    private List<Address> addresses;

    public static Client from(CreateClientDTO clientDto) {

        return Client
                .builder()
                .name(clientDto.getName())
//                .addresses(addressFrom(clientDto.getAddresses()))
                .build();

    }

    public static List<Address> addressFrom(List<CreateAddressClientDTO> addressDTO) {

        return addressDTO
                .stream()
                .map(address -> Address.from(address))
                .collect(Collectors.toList());


    }


}
