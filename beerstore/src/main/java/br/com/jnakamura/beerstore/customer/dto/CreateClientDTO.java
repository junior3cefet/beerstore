package br.com.jnakamura.beerstore.customer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateClientDTO {

    private String name;

    private List<CreateAddressClientDTO> addresses;

}
