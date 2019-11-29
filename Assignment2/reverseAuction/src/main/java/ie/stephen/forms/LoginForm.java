package ie.stephen.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotNull
    @Size(min=1, max=30)
    private String email;

    @NotNull
    private String password;
}