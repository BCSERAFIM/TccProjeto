
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;



public class Item implements Serializable{
    
    private Integer id;
    private String nome;
    private String descricaoTecnica;
    private String aplicacao;
    private Date necessidade;
    private Integer quantidade;
    
    private Requisicao requisição;
    

    public Item() {
    }

    public Item(String nome, String descricaoTecnica, String aplicacao, Date necessidade, Integer quantidade, Requisicao requisição) {
        this.nome = nome;
        this.descricaoTecnica = descricaoTecnica;
        this.aplicacao = aplicacao;
        this.necessidade = necessidade;
        this.quantidade = quantidade;
        this.requisição = requisição;
    }

    public Item(Integer id, String nome, String descricaoTecnica, String aplicacao, Date necessidade, Integer quantidade, Requisicao requisição) {
        this.id = id;
        this.nome = nome;
        this.descricaoTecnica = descricaoTecnica;
        this.aplicacao = aplicacao;
        this.necessidade = necessidade;
        this.quantidade = quantidade;
        this.requisição = requisição;
    }

    public Item(Integer id, String nome, String descricaoTecnica, String aplicacao, Date necessidade, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricaoTecnica = descricaoTecnica;
        this.aplicacao = aplicacao;
        this.necessidade = necessidade;
        this.quantidade = quantidade;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoTecnica() {
        return descricaoTecnica;
    }

    public void setDescricaoTecnica(String descricaoTecnica) {
        this.descricaoTecnica = descricaoTecnica;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public Date getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(Date necessidade) {
        this.necessidade = necessidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Requisicao getRequisição() {
        return requisição;
    }

    public void setRequisição(Requisicao requisição) {
        this.requisição = requisição;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
         
}
