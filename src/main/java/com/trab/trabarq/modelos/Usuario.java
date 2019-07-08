package com.trab.trabarq.modelos;

import java.util.Date;
import java.util.List;

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
    @NotNull(message = "A senha é obrigatório")
    @Length(max = 15, min = 5, message = "A senha deve conter entre 5 e 15 caracteres")
    private String senha;

    @Column(name = "us_tipo", nullable = false)
    private boolean tipo;

    @Column(name = "us_linkFacebook", length = 60, nullable = true)
    @Length(max = 60, message = "O link do facebook deve conter até 60 caracteres")
    private String linkFacebook;

    @Column(name = "us_linkInstagram", length = 60, nullable = true)
    @Length(max = 60, message = "O link do instagram deve conter até 60 caracteres")
    private String linkInstagram;

    @Column(name = "us_numTel", length = 11, nullable = false)
    @NotNull(message = "O número de telefone é obrigatório")
    @Length(max = 11, min = 11, message = "O cpf deve conter 11 caracteres")
    private String numTel;

    @Column(name = "us_linkDoacao", length = 60, nullable = true)
    @Length(max = 60, message = "O link da doação deve conter até 60 caracteres")
    private String linkDoacao;

    @Column(name = "us_descricao", length = 200, nullable = true)
    @Length(max = 200,  message = "A descrição deve conter até 200 caracteres")
    private String descricao;

    @OneToMany(mappedBy = "p_pet")
    private List<Pet> pets;

    @ManyToOne
    @JoinColumn(name = "uf_id")
    private Uf uf;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getLinkFacebook() {
        return linkFacebook;
    }

    public void setLinkFacebook(String linkFacebook) {
        this.linkFacebook = linkFacebook;
    }

    public String getLinkInstagram() {
        return linkInstagram;
    }

    public void setLinkInstagram(String linkInstagram) {
        this.linkInstagram = linkInstagram;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getLinkDoacao() {
        return linkDoacao;
    }

    public void setLinkDoacao(String linkDoacao) {
        this.linkDoacao = linkDoacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}