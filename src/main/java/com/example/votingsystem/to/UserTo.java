package com.example.votingsystem.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
