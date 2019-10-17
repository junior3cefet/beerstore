package br.com.jnakamura.beerstore.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressClientDTO {

    private String streetName;

    private AccessorTypeDTO type;



}
