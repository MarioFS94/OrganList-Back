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
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    private Long id;

    @Column(name = "nombre")
    private String name;

//    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "contrasena")
    private String pass;
}
