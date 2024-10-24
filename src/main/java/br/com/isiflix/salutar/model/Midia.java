package br.com.isiflix.salutar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_midia")
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_sec")
    private Integer numSec;
    @Column(name = "Descricao",length = 255)
    private String descricao;
    @Column(name = "link_midia",length = 255)
    private String linkMidia;

    @ManyToOne
    @JoinColumn(name = "id_ficha")
    @JsonIgnoreProperties("midias")
    private FichaPaciente ficha;

    public Integer getNumSec() {
        return numSec;
    }

    public void setNumSec(Integer numSec) {
        this.numSec = numSec;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLinkMidia() {
        return linkMidia;
    }

    public void setLinkMidia(String linkMidia) {
        this.linkMidia = linkMidia;
    }

    public FichaPaciente getFicha() {
        return ficha;
    }

    public void setFicha(FichaPaciente ficha) {
        this.ficha = ficha;
    }
}
