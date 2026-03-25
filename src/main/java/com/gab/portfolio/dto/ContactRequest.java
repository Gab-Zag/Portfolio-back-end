package com.gab.portfolio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Assunto é obrigatório")
    @Size(min = 3, max = 150)
    private String subject;

    @NotBlank(message = "Mensagem é obrigatória")
    @Size(min = 10, max = 2000)
    private String message;
}
