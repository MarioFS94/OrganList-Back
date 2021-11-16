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
@Table(name = "producto")
public class ProductEntity {

    @Id
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "esencial")
    private boolean essential;

    @Column(name = "categoria")
    private String category;
}
