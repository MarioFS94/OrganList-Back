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

@Builder
@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tienda")
public class ShopEntity {

    @Id
    private Integer id;

    @Column(name =  "nombre")
    private String name;

    @Column(name =  "tipo")
    private String type;

}
