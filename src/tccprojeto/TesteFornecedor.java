
package tccprojeto;

import beans.Empresa;
import beans.Fornecedor;
import beans.Orcamento;
import dao.EmpresaDao;
import dao.FornecedorDao;
import dao.OrcamentoDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TesteFornecedor {

    
    public static void main(String[] args) {
        
     // Inserir
          Locale.setDefault(Locale.US);
          FornecedorDao fornecedorDao = new FornecedorDao ();
          EmpresaDao empresaDao = new EmpresaDao();
          String cnpj = "05281825950";
          Empresa empresa = empresaDao.consultarEmpresaPorCnpj(cnpj);
          empresa.getId();
          
          String nome = "Jao";
          String email = "jao@gmail.com";
          String celular = "41777778888";
          String familia = "informatica";
          
          Fornecedor fornecedor = new Fornecedor(nome,email,celular,familia,empresa);
          
          try{
              fornecedorDao.inserirFornecedor(fornecedor);
              System.out.println( "Fornecedor inserido com sucesso");
          }catch(SQLException ex){
              String mensagem = ex.getMessage();
              System.out.println(mensagem);
          }
          
          
          //------------------------------------------------------------------------------------------// 
            //Listar por Fornecedor
            System.out.println( "Listar por Fornecedor");
          Fornecedor consultarFornecedor = fornecedorDao.consultarFornecedor(1);
          System.out.println("ID: "+consultarFornecedor.getId()
                                 +"\nNome: "+consultarFornecedor.getNome()
                                 +"\nEmail: "+consultarFornecedor.getEmail()
                                 +"\nCelular: "+consultarFornecedor.getCelular()
                                 +"\nNome Empresa: "+consultarFornecedor.getEmpresa().getNomeEmpresa());
          
          
       
          
          //Listar todos
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar todos os Fornecedores");
      List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
      
      
       try{
           listaFornecedores = fornecedorDao.listaFornecedores();
           for(Fornecedor lista : listaFornecedores){
               System.out.println("ID: "+lista.getId()
                                +"\nNome: "+lista.getNome()
                                +"\nEmail: "+lista.getEmail()
                                +"\nCelular: "+lista.getCelular()
                                +"\nNome Empresa: "+lista.getEmpresa().getNomeEmpresa());
           }
           
           
       }catch(Exception e){
          System.out.println("Erro: "+ e.getMessage());
      }
          
          
           //Listar Fornecedores por Empresa
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar Fornecedores por Empresa");
      
      List<Fornecedor> listaFornecedorPorEmpresa = new ArrayList<Fornecedor>();
      
      
       try{
           listaFornecedorPorEmpresa = fornecedorDao.consultarPorEmpresa(empresa);
           for(Fornecedor lista : listaFornecedorPorEmpresa){
               System.out.println("ID: "+lista.getId()
                                +"\nNome: "+lista.getNome()
                                +"\nEmail: "+lista.getEmail()
                                +"\nCelular: "+lista.getCelular()
                                +"\nNome Empresa: "+lista.getEmpresa().getNomeEmpresa());
           }
           
           
       }catch(Exception e){
          System.out.println("Erro: "+ e.getMessage());
      }
          
       
    }
    
}
