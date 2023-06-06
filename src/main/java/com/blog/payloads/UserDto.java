package com.blog.payloads;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

    private Integer id;
    @Size(min=2 , message = "Username must be greater than 2 characters")
    @NotEmpty
    private String name;
    @Email(message = "Your email address is not valid")
    private String email;
    @NotEmpty(message = "Password is mandate")
    @Size(min=8,max=10 , message = "Password must be min = 8 and max = 10 characters")
//    @Pattern for some regular expression -------------------------
    private String password;
    @NotEmpty
    private String about;

    private String role;


}
