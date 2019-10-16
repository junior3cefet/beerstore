package br.com.jnakamura.beerstore.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "brand_model")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BrandModel implements Serializable {

    @Id
    @SequenceGenerator(name = "brand_sequence", sequenceName = "brand_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "brand-1")
    private String name;
}
