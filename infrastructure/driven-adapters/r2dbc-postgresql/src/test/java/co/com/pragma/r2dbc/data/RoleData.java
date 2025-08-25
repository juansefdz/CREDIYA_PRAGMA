package co.com.pragma.r2dbc.data;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Table("roles")
public class RoleData {

    @Id
    private UUID id;
    private String nombre;
    private String descripcion;

}
