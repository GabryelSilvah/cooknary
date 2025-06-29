package com.receitas.model;

import com.receitas.dto.MetricasDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "metricas")
public class Metricas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_metrica;

    @Column(name = "quantidade_receitas")
    private int quantidade_receitas;

    @Column(name = "quantidade_livros")
    private int quantidade_livros;

    @Column(name = "media_avaliacoes")
    private double media_avaliacoes;

    @OneToOne
    @JoinColumn(name = "fk_funcionario")
    private Funcionario fk_funcionario;

    //Construtor


    public Metricas() {
    }

    public Metricas(Long id_metrica) {
        this.id_metrica = id_metrica;
    }

    public Metricas(Long id_metrica, Funcionario fk_funcionario) {
        this.id_metrica = id_metrica;
        this.fk_funcionario = fk_funcionario;
    }

    public Metricas(Long id_metrica, int quantidade_receitas, int quantidade_livros, double media_avaliacoes, Funcionario fk_funcionario) {
        this.id_metrica = id_metrica;
        this.quantidade_receitas = quantidade_receitas;
        this.quantidade_livros = quantidade_livros;
        this.media_avaliacoes = media_avaliacoes;
        this.fk_funcionario = fk_funcionario;
    }

    public Metricas(MetricasDTO metricasDTO) {
        this.id_metrica = metricasDTO.getId_metrica();
        this.quantidade_receitas = metricasDTO.getQuantidade_receitas();
        this.quantidade_livros = metricasDTO.getQuantidade_livros();
        this.media_avaliacoes = metricasDTO.getMedia_avaliacoes();
        this.fk_funcionario = new Funcionario(metricasDTO.getFk_funcionario().getId_func(),metricasDTO.getFk_funcionario().getNome());
    }

    //Gets e sets


    public Long getId_metrica() {
        return id_metrica;
    }

    public void setId_metrica(Long id_metrica) {
        this.id_metrica = id_metrica;
    }

    public int getQuantidade_receitas() {
        return quantidade_receitas;
    }

    public void setQuantidade_receitas(int quantidade_receitas) {
        this.quantidade_receitas = quantidade_receitas;
    }

    public int getQuantidade_livros() {
        return quantidade_livros;
    }

    public void setQuantidade_livros(int quantidade_livros) {
        this.quantidade_livros = quantidade_livros;
    }

    public double getMedia_avaliacoes() {
        return media_avaliacoes;
    }

    public void setMedia_avaliacoes(double media_avaliacoes) {
        this.media_avaliacoes = media_avaliacoes;
    }

    public Funcionario getFk_funcionario() {
        return fk_funcionario;
    }

    public void setFk_funcionario(Funcionario fk_funcionario) {
        this.fk_funcionario = fk_funcionario;
    }
}
