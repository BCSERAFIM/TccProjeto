package beans;

import java.io.Serializable;
import java.util.List;


public class Empresa implements Serializable{
    
    private Integer id;
    private String cnpj;
    private String nomeEmpresa;
    private String pais;
    private Integer cep;
    private String endereco;
    private Integer numeroRua;
    private String cidade;
    private String estado;
    private String telefoneEmpresa;
    private Fornecedor fornecedor;
    private Requisitante requisitante;
    private List<Fornecedor> fornecedores;
    private List<Requisitante> requisitantes;        

    public Empresa() {
    }

    public Empresa(String cnpj, String nomeEmpresa, String pais, Integer cep, String endereco, Integer numeroRua, String cidade, String estado, String telefoneEmpresa) {
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.pais = pais;
        this.cep = cep;
        this.endereco = endereco;
        this.numeroRua = numeroRua;
        this.cidade = cidade;
        this.estado = estado;
        this.telefoneEmpresa = telefoneEmpresa;
    }


   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumeroRua() {
        return numeroRua;
    }

    public void setNumeroRua(Integer numeroRua) {
        this.numeroRua = numeroRua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Requisitante getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Requisitante requisitante) {
        this.requisitante = requisitante;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<Requisitante> getRequisitantes() {
        return requisitantes;
    }

    public void setRequisitantes(List<Requisitante> requisitantes) {
        this.requisitantes = requisitantes;
    }
    
    
    
    
    
}
