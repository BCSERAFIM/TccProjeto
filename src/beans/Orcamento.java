
package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Orcamento implements Serializable{
    
    private Integer id;
    private Date dataMomento;
    private Integer numero;
    private Preco preco;
    private List<Preco> cotacoes;
    
    private Fornecedor fornecedor;

    public Orcamento() {
    }

    public Orcamento(Date dataMomento, Integer numero, Fornecedor fornecedor) {
        this.dataMomento = dataMomento;
        this.numero = numero;
        this.fornecedor = fornecedor;
    }

    public Orcamento(Integer id, Date dataMomento, Integer numero, Fornecedor fornecedor) {
        this.id = id;
        this.dataMomento = dataMomento;
        this.numero = numero;
        this.fornecedor = fornecedor;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataMomento() {
        return dataMomento;
    }

    public void setDataMomento(Date dataMomento) {
        this.dataMomento = dataMomento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

  

    public List<Preco> getCotacoes() {
        return cotacoes;
    }

    public void setCotacoes(List<Preco> cotacoes) {
        this.cotacoes = cotacoes;
    }

    public Preco getPreco() {
        return preco;
    }

    public void setPreco(Preco preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Orcamento other = (Orcamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
