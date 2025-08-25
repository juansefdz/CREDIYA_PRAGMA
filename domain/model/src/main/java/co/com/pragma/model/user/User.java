package co.com.pragma.model.user;

import java.math.BigDecimal;
import java.time.LocalDate;

import co.com.pragma.model.role.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private Long id;

    private String nombre;
    private String apellido;
    private String documentoIdentidad;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String email;
    private String telefono;
    private BigDecimal salarioBase;

    private Role rol;

}
