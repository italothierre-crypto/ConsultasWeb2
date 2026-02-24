package br.com.consultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "medico")
@Data
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Especialidade é obrigatória")
    private String especialidade;

    @NotBlank(message = "CRM é obrigatório")
    private String crm;

    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;   // 👈 ADICIONADO
}