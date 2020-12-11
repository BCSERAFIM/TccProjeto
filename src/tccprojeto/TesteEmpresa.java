package tccprojeto;

import beans.Empresa;
import beans.Fornecedor;
import beans.Requisitante;
import dao.EmpresaDao;
import dao.FornecedorDao;
import dao.RequisitanteDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TesteEmpresa {

 
    public static void main(String[] args) throws SQLException {
        
     // Inserir
     Locale.setDefault(Locale.US);
     EmpresaDao empresaDao = new EmpresaDao();
     FornecedorDao fornecedorDao = new FornecedorDao();
     RequisitanteDao requisitanteDao = new RequisitanteDao();
     Fornecedor fornecedor = fornecedorDao.consultarFornecedor(1);
     fornecedor.getId();
     Requisitante requisitente = requisitanteDao.consultarRequisitante(1);
     requisitente.getId();
     
     String cnpj = "05281825950";
     String nome = "Bruno Serafim";
     String pais = "Brasil";
     Integer cep = 81850170;
     String endereco = "Major Cassemiro";
     Integer numeroRua = 164;
     String cidade = "Curitiba";
     String estado = "Parana";
     String telefone = "4133783730";
     
      Empresa empresa = new Empresa(cnpj,nome,pais,cep,endereco,numeroRua,cidade,estado,telefone);
      

//      try{
//         empresaDao.inserirEmpresa(empresa);
//         System.out.println( "Empresa inserido com sucesso");
//      }catch(SQLException ex){
//          String mensagem = ex.getMessage();
//          System.out.println(mensagem);
//          
//      }
      
        
        
        //------------------------------------------------------------------------------------------// 
            //Listar por Empresa
            System.out.println( "------------------");
            System.out.println( "Listar por Empresa");
            
            Empresa consultarEmpresa = empresaDao.consultarEmpresa(1);
            System.out.println("CNPJ: "+consultarEmpresa.getCnpj()
                               +"\nNome: "+ consultarEmpresa.getNomeEmpresa()
                               +"\nID: "+consultarEmpresa.getId());
            
        
            //------------------------------------------------------------------------------------------// 
            //Listar por Cnpj da Empresa
            System.out.println( "------------------");
            System.out.println( "Listar por Empresa");
            
            Empresa consultarEmpresaPorCnpj = empresaDao.consultarEmpresaPorCnpj(cnpj);
            System.out.println("ID: "+consultarEmpresaPorCnpj.getId()
                               +"\nNome: "+ consultarEmpresa.getNomeEmpresa());
            
             //Listar todos
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar todos as Empresas");
      List<Empresa>listaEmpresas = new ArrayList<Empresa>();
      
      try{
          listaEmpresas = empresaDao.listaEmpresas();
          for(Empresa lista : listaEmpresas ){
              System.out.println("ID: "+lista.getId()
                                +"\nNome: "+lista.getNomeEmpresa()
                                +"\nCNPJ: "+lista.getCnpj());
          }
      }catch(Exception e){
          System.out.println("Erro: "+ e.getMessage());
      }
        
    }
    
}
