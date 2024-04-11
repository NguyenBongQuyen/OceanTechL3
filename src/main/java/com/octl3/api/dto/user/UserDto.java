package com.octl3.api.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octl3.api.validator.anotations.Phone;
import com.octl3.api.validator.anotations.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    @Username
    private String username;
    private String password;
    private Integer roleId;
    private String name;
    private String position;
    @Email
    private String email;
    @Phone
    private String phone;

}