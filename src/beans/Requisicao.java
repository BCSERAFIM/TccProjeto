
package beans;

import java.io.Serializable;
import java.util.Date;

import java.util.List;
import java.util.Objects;



public class Requisicao implements Serializable{
    
    private Integer id;
    private Date dataMomento;
    private Integer numero;
    private Item item;
    private List<Item> itens;
    
    
    private Requisitante requisitante;
    
    

    public Requisicao() {
    }

    public Requisicao(Date dataMomento, Integer numero, Requisitante requisitante) {
   
        this.dataMomento = dataMomento;
        this.numero = numero;
        this.requisitante = requisitante;
    }

    
    
    
    public Requisicao(Integer id, Date dataMomento, Integer numero, Item item) {
        this.id = id;
        this.dataMomento = dataMomento;
        this.numero = numero;
        this.item = item;
    }

    public Requisicao(Integer id, Date dataMomento, Integer numero) {
        this.id = id;
        this.dataMomento = dataMomento;
        this.numero = numero;
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

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Requisitante getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Requisitante requisitante) {
        this.requisitante = requisitante;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Requisicao other = (Requisicao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

   

        
}
