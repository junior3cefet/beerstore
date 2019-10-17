package br.com.jnakamura.beerstore.customer.entity;

import br.com.jnakamura.beerstore.customer.dto.AccessorTypeDTO;
import br.com.jnakamura.beerstore.customer.dto.CreateAddressClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street_name")
    private String streetName;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccessorType type;

    public static Address from(CreateAddressClientDTO addressClientDTO) {

        return Address
                .builder()
                .streetName(addressClientDTO.getStreetName())
                .type(accessorTypeFrom(addressClientDTO.getType()))
                .build();

    }

    private static AccessorType accessorTypeFrom(AccessorTypeDTO dto) {

        return AccessorType
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();

    }
}
