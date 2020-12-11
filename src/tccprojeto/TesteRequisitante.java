
package tccprojeto;

import beans.Empresa;
import beans.Requisicao;
import beans.Requisitante;
import dao.EmpresaDao;
import dao.RequisicaoDao;
import dao.RequisitanteDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TesteRequisitante {

 
    public static void main(String[] args) {
    
         // Inserir
          Locale.setDefault(Locale.US);
          RequisitanteDao requisitanteDao = new RequisitanteDao();
          EmpresaDao empresaDao = new EmpresaDao();
          Empresa empresa = empresaDao.consultarEmpresa(1);
          empresa.getId();
          
          
          String nome = "Bruno Serafim";
          String email = "bcserafim@gmail.com";
          String celular = "419999999999";
          
          Requisitante requisitante = new Requisitante(nome,email,celular,empresa);
          
          /* try{
              requisitanteDao.inserirRequisitatne(requisitante);
           }catch(SQLException ex){
            String mensagem = ex.getMessage();
           }*/
          
           //------------------------------------------------------------------------------------------// 
            //Listar por Requisitante
            System.out.println( "Listar por Requisitante");
        
            Requisitante consultarRequisitante = requisitanteDao.consultarRequisitante(1);
                System.out.println("ID: "+consultarRequisitante.getId()
                                 +"\nNome: "+consultarRequisitante.getNome()
                                 +"\nEmail: "+consultarRequisitante.getEmail()
                                 +"\nCelular: "+consultarRequisitante.getCelular()
                                 +"\nNome Empresa: "+consultarRequisitante.getEmpresa().getNomeEmpresa());
        
        
        //Listar todos
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar todos os Requisitantes");
      List<Requisitante>listaRequisitantes = new ArrayList<Requisitante>();
      
      try{
          listaRequisitantes = requisitanteDao.listaRequisitantes();
          for(Requisitante lista : listaRequisitantes ){
              System.out.println("ID: "+lista.getId()
                                +"\nNome: "+lista.getNome()
                                +"\nEmail: "+lista.getEmail()
                                +"\nCelular: "+lista.getCelular()
                                +"\nNome Empresa: "+lista.getEmpresa().getNomeEmpresa());
          }
      }catch(Exception e){
          System.out.println("Erro: "+ e.getMessage());
      }
      
      
      //Listar Requisitante por Empresa
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Listar Requisitante por Empresa");
      List<Requisitante>listaRequisitantePorEmpresa = new ArrayList<Requisitante>();
      
      try{
         listaRequisitantePorEmpresa = requisitanteDao.consultarPorEmpresa(empresa);
          for(Requisitante list : listaRequisitantePorEmpresa ){
              System.out.println("ID: "+list.getId()
                                +"\nNome: "+list.getNome()
                                +"\nEmail: "+list.getEmail()
                                +"\nCelular: "+list.getCelular()
                                +"\nNome Empresa: "+list.getEmpresa().getNomeEmpresa());
          }
      }catch(Exception e){
          System.out.println("Erro: "+ e.getMessage());
      }
      
        
        //------------------------------------------------------------------------------------------//  
        //atualizar
      System.out.println( "------------------------------------------------------------------");
      System.out.println( "Atuaizar  Requisitante");
//       try{
//       Requisitante consultaRequisitante = requisitanteDao.consultarRequisitante(1);
//            consultaRequisitante.setNome("Dunha Silva");
//            requisitanteDao.atualizarRequisitante(consultaRequisitante);
//            System.out.println("Requisitante Atualizado com sucesso");
//            
//        }catch (Exception e) {
//            String menssagem = e.getMessage();
//            System.out.println(menssagem);
//     }
      
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
