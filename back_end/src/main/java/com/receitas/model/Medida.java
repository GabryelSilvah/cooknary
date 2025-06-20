package com.receitas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "medidas")
public class Medida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_med")
    private Long id_med;



    @Column(name = "nome_med", nullable = false, length = 45)
    @NotNull(message = "Informe o nome da medida")
    private String nome_med;


    @OneToMany(mappedBy = "medida_id", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Receitas_and_ingredientes> receitas_and_ingredientes;


    //Construtores
    public Medida() {
    }

    public Medida(Long id_med) {
        this.id_med = id_med;
    }

    public Medida(Long id_med, String nome_med, List<Receitas_and_ingredientes> receitas_and_ingredientes) {
        this.id_med = id_med;
        this.nome_med = nome_med;
        this.receitas_and_ingredientes = receitas_and_ingredientes;
    }

    //Gets e Sets


    public Long getId_med() {
        return id_med;
    }

    public void setId_med(Long id_med) {
        this.id_med = id_med;
    }

    public String getNome_med() {
        return nome_med;
    }

    public void setNome_med(String nome_med) {
        this.nome_med = nome_med;
    }

    public List<Receitas_and_ingredientes> getReceitas_and_ingredientes() {
        return receitas_and_ingredientes;
    }

    public void setReceitas_and_ingredientes(List<Receitas_and_ingredientes> receitas_and_ingredientes) {
        this.receitas_and_ingredientes = receitas_and_ingredientes;
    }
}
