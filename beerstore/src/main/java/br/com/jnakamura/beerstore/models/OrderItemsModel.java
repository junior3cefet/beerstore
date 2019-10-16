package br.com.jnakamura.beerstore.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItemsModel implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private OrderItemsId id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("orderId")
    private OrderModel order;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("beerId")
    private BeerModel beers;

    private Integer quantity;

    private BigDecimal price;

}
