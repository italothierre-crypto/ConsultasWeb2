package br.com.consultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "consulta")
@Data
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==============================
    // Relacionamentos
    // ==============================

    @NotNull(message = "Paciente é obrigatório")
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Paciente paciente;

    @NotNull(message = "Médico é obrigatório")
    @ManyToOne
    @JoinColumn(name = "id_medico")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Medico medico;

    // ==============================
    // Campos da consulta
    // ==============================

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Horário é obrigatório")
    private LocalTime horario;

    @NotBlank(message = "Observação é obrigatória")
    private String observacao;

    //feito apenas observações básicas; nada muito complexo
}