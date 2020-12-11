
package tccprojeto;

import beans.Fornecedor;
import beans.Orcamento;
import beans.Preco;
import dao.FornecedorDao;
import dao.OrcamentoDao;
import dao.PrecoDao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TesteOrcamento {

  
    public static void main(String[] args) {
        
        
          // Inserir
        Locale.setDefault(Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        OrcamentoDao orcamentoDao = new OrcamentoDao();
        FornecedorDao fornecedorDao = new FornecedorDao();
        Fornecedor fornecedor = fornecedorDao.consultarFornecedor(1);
        fornecedor.getId();
        
        Integer numero = 100;
        
        Orcamento orcamento = new Orcamento( null,new Date(),numero,fornecedor);
//       try{
//            orcamentoDao.inserirOrcamento(orcamento);
//        }catch(SQLException ex){
//            String mensagem = ex.getMessage();
//        }
        
      
        //------------------------------------------------------------------------------------------// 
        //Listar por Requisição
       System.out.println( "Listar por Requisição");
        
        Orcamento consultaOrcamento = orcamentoDao.consultarOrcamento(1);
        System.out.println("Data: "+sdf.format(consultaOrcamento.getDataMomento())
                           +"\nNumero: "+consultaOrcamento.getNumero()
                           +"\nFornecedor: "+consultaOrcamento.getFornecedor().getNome());
        
      
        
           //------------------------------------------------------------------------------------------//  
     
      //Listar todos
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar todos os orcamentos");
      List<Orcamento> listaOrcamento = new ArrayList<Orcamento>();
      
        listaOrcamento = orcamentoDao.listaOrcamento();
        for(Orcamento lista : listaOrcamento){
             System.out.println("ID: "+lista.getId()
                     +"\nNumero: "+lista.getNumero()
                     +"\nData: "+ sdf.format(lista.getDataMomento())
                    +"\nNome Fornecedor: "+lista.getFornecedor().getNome());
        }
        
         //Listar orcamento por Fornecedor
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar Orcamento por Fornecedor");
      List<Orcamento> listarOrcamentoPorFornecedor = new ArrayList<Orcamento>();
      
        listarOrcamentoPorFornecedor = orcamentoDao.consultarPorFornecedor(fornecedor);
        for(Orcamento list : listarOrcamentoPorFornecedor){
             System.out.println("ID: "+list.getId()
                     +"\nNumero: "+list.getNumero()
                     +"\nData: "+ sdf.format(list.getDataMomento())
                     +"\nNome Fornecedor: "+list.getFornecedor().getNome());
        }
        
    
        
    }
    
}
