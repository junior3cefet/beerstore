package br.com.jnakamura.beerstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "beer_model")
public class BeerModel implements Serializable {

    @Id
    @SequenceGenerator(name="beer_seq", sequenceName = "beer_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beer_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "beer-1")
    private String name;

    @NotNull(message = "beer-2")
    private BeerType type;

    @NotNull(message =  "beer-3")
    @DecimalMin(value="0", message = "beer-4")
    @DecimalMax(value = "100", message = "beer-5")
    private BigDecimal volume;

    @ManyToOne(fetch = FetchType.EAGER )
    @NotNull(message = "beer-8")
    private BrandModel brand;

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    @JsonIgnore
    public boolean isAlreadyExist() {
        return getId() != null;
    }
}
