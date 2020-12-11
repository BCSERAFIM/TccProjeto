package tccprojeto;

import beans.Orcamento;
import beans.Preco;
import dao.OrcamentoDao;
import dao.PrecoDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class TestePreco {


    public static void main(String[] args) {
        
        
           // Inserir item 
           Locale.setDefault(Locale.US);
           PrecoDao precoDao = new PrecoDao();
           OrcamentoDao orcamentoDao = new OrcamentoDao();
           Orcamento orcamento = orcamentoDao.consultarOrcamento(1);
           orcamento.getId();
           
           Integer ipi = 5;
           double icms = 12;
           double st = 1;
           double pisCofins = 3.65;
           double valor = 1000.00;
        
           Preco preco = new Preco(ipi,icms,st,pisCofins,valor,orcamento);
           /*try{
               precoDao.inserirPreco(preco);
           }catch(SQLException ex){
            String mensagem = ex.getMessage();
           }*/
           //------------------------------------------------------------------------------------------//  
        
        // consulta
        System.out.println("Consulta Preço por ID");
        Preco consultarPreco = precoDao.consultarPreco(1);
        System.out.println("Ipi: "+consultarPreco.getIpi()
                          +"\nIcms: "+consultarPreco.getIcms()
                          +"\nSt: "+consultarPreco.getSt()
                          +"\nPisCofins: "+consultarPreco.getPisCofins()
                          +"\nValor: "+consultarPreco.getValorItem()
                          +"\nNumero Orcamento: "+consultarPreco.getOrcamento().getNumero());
        
        
        
           //------------------------------------------------------------------------------------------//  
        //atualizar
        
//      try{
//            consultarPreco.setOrcamento(orcamento);
//            precoDao.atualizarPreco(consultarPreco);
//            System.out.println("Preço atualizado com sucesso");
//            
//        }catch (Exception e) {
//            String menssagem = e.getMessage();
//            System.out.println(menssagem);
//     }
       
       
        //------------------------------------------------------------------------------------------//  
     
      //Listar todos
      System.out.println("---------------------------------------------------------------------");
        System.out.println("Listar todos os Preços");
        
        
        List<Preco> listaPreco = new ArrayList<Preco>();
        listaPreco = precoDao.ListarPreco();
        for(Preco lista : listaPreco){
            System.out.println("ID: "+lista.getId()
                          +"\nIpi: "+lista.getIpi()
                          +"\nIcms: "+lista.getIcms()
                          +"\nSt: "+lista.getSt()
                          +"\nPisCofins: "+lista.getPisCofins()
                          +"\nValor: "+lista.getValorItem()
                          +"\nNumero Orcamento: "+lista.getOrcamento().getNumero());
            
        }
        
        
           //Listar preco por Orcamento
      System.out.println("---------------------------------------------------------------------");
        System.out.println("Listar preco por Orcamento");
        
        
       List<Preco> listarPrecoPorOrcamento = new ArrayList<Preco>();
       listarPrecoPorOrcamento  = precoDao.consultarPorOrcamento(orcamento);
        for(Preco list : listarPrecoPorOrcamento){
            System.out.println("ID: "+list.getId()
                          +"\nIpi: "+list.getIpi()
                          +"\nIcms: "+list.getIcms()
                          +"\nSt: "+list.getSt()
                          +"\nPisCofins: "+list.getPisCofins()
                          +"\nValor: "+list.getValorItem()
                          +"\nNumero Orcamento: "+list.getOrcamento().getNumero());
            
        }
    
    
    }
}
