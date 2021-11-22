package es.organlist.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "listaproducto")
public class ProductListEntity {
    @Id
    private Integer id;

    @Column(name = "producto")
    private BigDecimal product;

    @Column(name = "lista")
    private BigDecimal list;

    @Column(name = "unidades")
    private int units;
}
