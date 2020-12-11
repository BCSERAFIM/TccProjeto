
package tccprojeto;

import beans.Item;
import beans.Requisicao;
import beans.Requisitante;
import dao.ItemDao;
import dao.RequisicaoDao;
import dao.RequisitanteDao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TesteRequisicao {

    public static void main(String[] args) throws SQLException {
        
        
        // Inserir
        Locale.setDefault(Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        RequisicaoDao requisicaoDao = new RequisicaoDao();
        RequisitanteDao requisitanteDao = new RequisitanteDao();
        Requisitante requisitante = requisitanteDao.consultarRequisitante(1);
        requisitante.getId();
        
        Integer numero = 500;
        
        Requisicao requisicao = new Requisicao(new Date(),numero,requisitante);
       /* try{
            requisicaoDao.inserirRequisicao(requisicao);
        }catch(SQLException ex){
            String mensagem = ex.getMessage();
        }*/
        
      
        //------------------------------------------------------------------------------------------// 
        //Listar por Requisição
       System.out.println( "Listar por Requisição");
        
        Requisicao consultaRequisicao = requisicaoDao.consultarRequisicao(1);
        System.out.println("ID: "+consultaRequisicao.getId()
                           +"\ndata: "+sdf.format(consultaRequisicao.getDataMomento())
                           +"\nNumero: "+consultaRequisicao.getNumero()
                           +"\nNome Requisitante: "+consultaRequisicao.getRequisitante().getNome());
        
      
        
           //------------------------------------------------------------------------------------------//  
     
      //Listar todos
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar todos as Requisição");
      List<Requisicao> listaRequisicao = new ArrayList<Requisicao>();
      
        listaRequisicao = requisicaoDao.listaRequisicoes();
        for(Requisicao lista : listaRequisicao){
             System.out.println("ID: "+lista.getId()
                     +"\nNumero: "+lista.getNumero()
                     +"\nData: "+ sdf.format(lista.getDataMomento())
                     +"\nNome Requisitante: "+lista.getRequisitante().getNome());
        }
        
        //Listar por Requisitante
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar Requisicao por Requisitante");
       List<Requisicao> listarPorRequisitente = new ArrayList<Requisicao>();
      
        listarPorRequisitente = requisicaoDao.consultarPorRequisitante(requisitante);
        for(Requisicao list : listarPorRequisitente){
             System.out.println("ID: "+list.getId()
                     +"\nNumero: "+list.getNumero()
                     +"\nData: "+ sdf.format(list.getDataMomento())
                     +"\nNome Requisitante: "+list.getRequisitante().getNome());
        }
        
        
        
        
        
        
        
        
        
        
    }
    
}
