package ie.stephen.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;
}