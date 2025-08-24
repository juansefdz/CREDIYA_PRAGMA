package co.com.pragma.r2dbc.data;

import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Table("roles")
public class RoleData {

    @Id
    private Long id;
    private String name;
    private String description;

}
