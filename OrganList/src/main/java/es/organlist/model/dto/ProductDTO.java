package es.organlist.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class ProductDTO {

    @Id
    private Long id;
}
