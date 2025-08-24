package co.com.pragma.model.user;

import java.math.BigDecimal;

import co.com.pragma.model.role.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private Long id;

    private String name;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal baseSalary;

    private Role role;

}
