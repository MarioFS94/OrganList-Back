package es.organlist.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;

@Builder
@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    //@OneToMany
    @GeneratedValue(strategy = GenerationType.IDENTITY)//indicar que el id lo va a generar la BBDD
    private Integer id;

    @Column(name = "nombre")
    private String name;

//    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "contrasena")
    private String pass;
}
