package beans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class Requisitante implements Serializable{
    
    private Integer id;
    private String nome;
    private String email;
    private String celular;
    private Requisicao requisicao;
    private List<Requisicao> requisicoes;
    
    
    private Empresa empresa;
    

    public Requisitante() {
    }

    public Requisitante(String nome, String email, String celular, Empresa empresa) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.empresa = empresa;
    }

   

    public Requisitante(Integer id, String nome, String email, String celular, Requisicao requisicao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.requisicao = requisicao;
    }

    public Requisitante(Integer id, String nome, String email, String celular) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
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

    public List<Requisicao> getRequisicoes() {
        return requisicoes;
    }

    public void setRequisicoes(List<Requisicao> requisicoes) {
        this.requisicoes = requisicoes;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Requisitante other = (Requisitante) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
 
    
    
    
}
