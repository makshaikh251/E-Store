package com.elecronicStore.EStore.dtos;

import com.elecronicStore.EStore.Validate.ImageNameValid;
import com.elecronicStore.EStore.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {
    private String userId;

    @Size(min = 3,max = 25,message = "Invalid Name!!")
    private String name;

   @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Invalid Email!!!")
    @NotBlank(message = "Email requires")
    private String email;

    @NotBlank(message = "Password is Required")
    private String password;

    @Size(min = 4,max = 6,message = "Invalid Gender")
    private String gender;

    @NotBlank(message = "Write Something about yourself")
    private String about;

    @ImageNameValid
    private String imageName;

}
