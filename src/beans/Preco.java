package beans;

import java.io.Serializable;
import java.util.Objects;

public class Preco implements Serializable {
    
    private Integer id;
    private double ipi;
    private double icms;
    private double st;
    private double pisCofins;
    private double valorItem;
    
    
    private Orcamento orcamento;

    public Preco() {
    }

    public Preco(Integer id, double ipi, double icms, double st, double pisCofins, double valorItem, Orcamento orcamento) {
        this.id = id;
        this.ipi = ipi;
        this.icms = icms;
        this.st = st;
        this.pisCofins = pisCofins;
        this.valorItem = valorItem;
        this.orcamento = orcamento;
    }

    public Preco(double ipi, double icms, double st, double pisCofins, double valorItem, Orcamento orcamento) {
        this.ipi = ipi;
        this.icms = icms;
        this.st = st;
        this.pisCofins = pisCofins;
        this.valorItem = valorItem;
        this.orcamento = orcamento;
    }

    public Preco(Integer id, double ipi, double icms, double st, double pisCofins, double valorItem) {
        this.id = id;
        this.ipi = ipi;
        this.icms = icms;
        this.st = st;
        this.pisCofins = pisCofins;
        this.valorItem = valorItem;
    }

  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
    public double getIpi() {
        return ipi;
    }

    public void setIpi(double ipi) {
        this.ipi = ipi;
    }

    public double getIcms() {
        return icms;
    }

    public void setIcms(double icms) {
        this.icms = icms;
    }

    public double getSt() {
        return st;
    }

    public void setSt(double st) {
        this.st = st;
    }

    public double getPisCofins() {
        return pisCofins;
    }

    public void setPisCofins(double pisCofins) {
        this.pisCofins = pisCofins;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Preco other = (Preco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
