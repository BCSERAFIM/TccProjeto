
package tccprojeto;

import beans.Item;
import beans.Requisicao;
import dao.ItemDao;
import dao.RequisicaoDao;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TesteItem {

    
    public static void main(String[] args) throws ParseException, SQLException {
      
        // Inserir item 
     Locale.setDefault(Locale.US);
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     ItemDao itemDao = new ItemDao();
     RequisicaoDao requisicaoDao = new RequisicaoDao();
     Requisicao requisicao = requisicaoDao.consultarRequisicao(1);
     requisicao.getId();
     
     String nome = "TV 50' 4k";
     String descricaoTecnica = "110v - smartTV";
     Date necessidade =(Date) sdf.parse("12/12/2022");
     String aplicacao = "Intererimento";
     Integer quantidade = 2;
     
     Item item = new Item(nome,descricaoTecnica,aplicacao, (Date) necessidade ,quantidade,requisicao);
//     try{
//        itemDao.inserirItem(item);
//     }catch(SQLException ex){
//       String mensagem = ex.getMessage();
//     }
      //------------------------------------------------------------------------------------------//  
        
     // consulta
     System.out.println( "--------------------------");
     System.out.println( "Consultar por Item");
     
     Item consultaItem = itemDao.consultarItem(1);
      
     System.out.println("ID: "+consultaItem.getId()
                  +" \nNome: "+consultaItem.getNome()
                  +" \nAplicação: "+consultaItem.getAplicacao()
                  +"\nDescrição: "+consultaItem.getDescricaoTecnica()
                  +"\nNecessidade: "+sdf.format(consultaItem.getNecessidade())
                  +"\nNumero Requisicao: "+consultaItem.getRequisição().getNumero());
     
     
     //atualizar
//     try{
//         consultaItem.setRequisição(requisicaoId);
//         itemDao.atualizarItem(consultaItem);
//         System.out.print("Item "+consultaItem.getNome()+" atualizado com sucesso");
//         
//     }catch (Exception e) {
//            String menssagem = e.getMessage();
//            System.out.println(menssagem);
//     }
     
     
          
      //------------------------------------------------------------------------------------------//  
     
      //Listar 
      System.out.println( "--------------------------");
     System.out.println( "Listar Itens");
      List<Item> listaItem = new ArrayList<Item>();
      listaItem = itemDao.listarItens();
      for(Item list : listaItem){
          
          System.out.println("ID: "+list.getId()
                  +" \nNome: "+list.getNome()
                  +" \nAplicação: "+list.getAplicacao()
                  +"\nNecessidade: "+sdf.format(list.getNecessidade())
                  +"\nNumero Requisicao: "+list.getRequisição().getNumero());
      }
        
        
      //------------------------------------------------------------------------------------------//  
     
      //Listar por requisicao
      System.out.println( "--------------------------");
      System.out.println( "Listar Itens por requisicao");
      List<Item> listarPorRequisicao = itemDao.consultarPorRequisicao(requisicao);
      for(Item lista : listarPorRequisicao){
           System.out.println("ID: "+lista.getId()
                  +" \nNome: "+lista.getNome()
                  +" \nAplicação: "+lista.getAplicacao()
                  +"\nNecessidade: "+sdf.format(lista.getNecessidade()));
      }
      
    }
   
    
}
