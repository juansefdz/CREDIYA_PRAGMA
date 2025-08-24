package co.com.pragma.r2dbc.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Table("users")
public class UserData {

    @Id
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal baseSalary;

    @Column("id_role")
    private Long idRole;
}