package br.com.jnakamura.beerstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerModel {

    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    @GeneratedValue(generator = "customer_seq")
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDay;

}
