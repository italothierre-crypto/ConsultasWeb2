package br.com.consultas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    // ==============================
    // Campos da consulta
    // ==============================

    private LocalDate data;

    private LocalTime horario;

    private String observacao;
}
