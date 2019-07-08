package com.trab.trabarq.modelos;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "p_pet")
public class Pet {

    @Id //identifica a chave principal
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private long id;

    @Column(name = "p_nome", length = 50, nullable = false)
    @NotNull(message = "O nome é obrigatório")
    @Length(max = 50, min = 3, message = "O nome deve conter entre 3 e 50 caracteres")
    private String nome;

    @Column(name = "p_genero", length = 20, nullable = false)
    @NotNull(message = "O gênero é obrigatório")
    @Length(max = 20, min = 3)
    private String genero;

    @Column(name = "p_data_nascimento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    @Column(name = "p_descricao", length = 200, nullable = true)
    @Length(max = 200,  message = "A descrição deve conter até 200 caracteres")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "us_id")
    private Usuario dono;
}