package com.yourlife.your.life.model.vo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
