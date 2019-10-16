package br.com.jnakamura.beerstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;

@Embeddable
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItemsId implements Serializable {

    @EqualsAndHashCode.Include
    private Long orderId;

    @EqualsAndHashCode.Include
    private Long beerId;
}
