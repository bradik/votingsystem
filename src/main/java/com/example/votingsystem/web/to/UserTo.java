package com.example.votingsystem.web.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    public UserTo(Integer id, String email, String password) {
        super(id);
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
