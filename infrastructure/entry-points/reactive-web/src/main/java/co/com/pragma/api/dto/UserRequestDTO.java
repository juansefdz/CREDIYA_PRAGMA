package co.com.pragma.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class UserRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;
    @NotBlank(message = "El documento de identidad es obligatorio")
    private String documentoIdentidad;
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
    @NotNull(message = "El salario base es obligatorio.")
    @DecimalMin(value = "0.0", message = "El salario no puede ser negativo.")
    @DecimalMax(value = "15000000.0", message = "El salario no puede exceder 15,000,000.")
    private BigDecimal salarioBase;
}
