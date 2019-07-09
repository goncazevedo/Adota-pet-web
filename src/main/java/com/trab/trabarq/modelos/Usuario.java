package com.trab.trabarq.modelos;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//modelo que explicita oq é uma tarefa
//sera uma tabela no banco de dados

@Entity
@Table(name = "us_usuario")
public class Usuario {

    @Id //identifica a chave principal
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private long id;

    @Column(name = "us_nome", length = 50, nullable = false)
    @NotNull(message = "O nome é obrigatório")
    @Length(max = 50, min = 3, message = "O nome deve conter entre 3 e 50 caracteres")
    private String nome;

    @Column(name = "us_email", length = 60, nullable = false)
    @NotNull(message = "O email é obrigatório")
    @Length(max = 60, min = 3, message = "O email deve conter entre 3 e 60 caracteres")
    private String email;

    @Column(name = "us_genero", length = 20, nullable = false)
    @NotNull(message = "O gênero é obrigatório")
    @Length(max = 20, min = 3)
    private String genero;

    @Column(name = "us_cpf", length = 11, nullable = false)
    @NotNull(message = "O cpf é obrigatório")
    @Length(max = 11, min = 11, message = "O cpf deve conter 11 caracteres")
    private String cpf;

    @Column(name = "us_data_nascimento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    @Column(name = "us_senha", length = 15, nullable = false)
    @NotNull(message = "O email é obrigatório")
    @Length(max = 15, min = 6, message = "O senha deve conter entre 5 e 15 caracteres")
    private String senha;

    @Column(name = "us_linkInstagram", length = 60, nullable = true)
    @Length(max = 60, message = "O link do instagram deve conter até 60 caracteres")
    private String linkInstagram;

    @Column(name = "us_linkFacebook", length = 60, nullable = true)
    @Length(max = 60, message = "O link do facebook deve conter até 60 caracteres")
    private String linkFacebook;

    private Boolean concluida = false;
}