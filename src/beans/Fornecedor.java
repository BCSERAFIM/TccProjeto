
package beans;

import java.util.List;
import java.util.Objects;


public class Fornecedor {
    
    private Integer id;
    private String nome;
    private String email;
    private String celular;
    private String familia;
    private Orcamento orcamento;
    private List<Orcamento> orcamentos;
    
    
    private Empresa empresa;

    public Fornecedor() {
    }

    public Fornecedor(Integer id, String nome, String email, String celular, String familia) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.familia = familia;
    }

    public Fornecedor(String nome, String email, String celular, String familia, Empresa empresa) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.familia = familia;
        this.empresa = empresa;
    }
    
    

    public Fornecedor(Integer id, String nome, String email, String celular, String familia, Orcamento orcamento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.familia = familia;
        this.orcamento = orcamento;
    }

    public Fornecedor(String nome, String email, String celular, String familia, Orcamento orcamento) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.familia = familia;
        this.orcamento = orcamento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
