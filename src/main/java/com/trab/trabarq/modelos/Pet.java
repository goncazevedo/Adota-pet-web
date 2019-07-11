package com.trab.trabarq.modelos;

import org.hibernate.validator.constraints.Length;

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
    
    @Column(name = "p_genero", length = 20)
    @NotNull(message = "O gênero é obrigatório")
    @Length(max = 20, min = 3)
    private String genero;
    
    @Column(name = "p_idade", nullable = false)
    @NotNull(message = "A idade é obrigatória")
    private int idade;

    @Column(name = "p_raca", length = 50, nullable = false)
    @NotNull(message = "A raça é obrigatória")
    @Length(max = 50, min = 3, message = "O nome deve conter entre 3 e 50 caracteres")
    private String raca;
    
    @Column(name = "p_descricao", length = 200, nullable = true)
    @Length(max = 200,  message = "A descrição deve conter até 200 caracteres")
    private String descricao;

    @Column(name = "p_vacinas", length = 200, nullable = true)
    @Length(max = 200,  message = "A descrição deve conter até 200 caracteres")
    private String vacinas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id")
    private Usuario dono;

    @Column(name = "p_uf", length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public String getVacinas() {
        return vacinas;
    }

    public void setVacinas(String vacinas) {
        this.vacinas = vacinas;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }
}