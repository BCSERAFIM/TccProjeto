
package dao;

import beans.Orcamento;
import beans.Preco;
import beans.Requisicao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PrecoDao {
    
    private String inserirPreco = " INSERT INTO preco (ipi,icms,st,piscofins,valor,orcamentoId) VALUES(?,?,?,?,?,?) ";
    private String atualizarPreco = " UPDATE preco SET ipi= ?,icms=?,st=?,piscofins=?,valor=? where id=? ";
    private String deletatPreco = " DELETE FROM preco WHERE id = ? ";
    private String consultarPreco  = " SELECT preco.*,orcamento.numero as numeroOrcamento "
                                  +" FROM preco INNER JOIN orcamento " 
                                  +" ON preco.orcamentoId = orcamento.id " 
                                  +" WHERE preco.id = ? ";
    private String listarPreco = " SELECT preco.*,orcamento.numero as numeroOrcamento "
                                  +" FROM preco INNER JOIN orcamento " 
                                  +" ON preco.orcamentoId = orcamento.id ";
    private String consultarPorOrcamento = " SELECT preco.*,orcamento.numero as numeroOrcamento "
                                  +" FROM preco INNER JOIN orcamento " 
                                  +" ON preco.orcamentoId = orcamento.id " 
                                  +" WHERE orcamento.id = ? ";
    
    
    public void inserirPreco(Preco preco)throws SQLException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(inserirPreco , PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setDouble(1, preco.getIpi());
            stmt.setDouble(2, preco.getIcms());
            stmt.setDouble(3, preco.getSt());
            stmt.setDouble(4, preco.getPisCofins());
            stmt.setDouble(5, preco.getValorItem());
            stmt.setInt(6, preco.getOrcamento().getId());
            
            stmt.executeUpdate();
            int idPrecoGravado = lerIdPreco(stmt);
            preco.setId(idPrecoGravado);
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir Item: "+ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
     
     }
    
       private int lerIdPreco (PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
        
    }
    
    
    public void atualizarPreco (Preco preco)throws SQLException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(atualizarPreco);
            stmt.setDouble(1, preco.getIpi());
            stmt.setDouble(2, preco.getIcms());
            stmt.setDouble(3, preco.getSt());
            stmt.setDouble(4, preco.getPisCofins());
            stmt.setDouble(5, preco.getValorItem());
            stmt.setInt(6, preco.getOrcamento().getId());
            stmt.setInt(7,preco.getId());
            
            stmt.executeUpdate();
            if (stmt.executeUpdate() == 0) {
                throw new RuntimeException("Não encontrei Preco para Atualizar");
            }
            
        }catch (SQLException ex) {
            throw new RuntimeException("Não foi possivel atualizar o Preco erro: "+ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        
    }
    
       public void deletarPreco (Integer id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deletatPreco);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar um Item: "+ ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        
    }
    
    
    
    public Preco consultarPreco (Integer id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Preco precoEncontrado = null;
        
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarPreco);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
           if(rs.next()){
              Orcamento orc = instanciarOrcamento(rs);
              precoEncontrado = instanciarPreco(rs, orc);
              return precoEncontrado; 
           }
            
           return precoEncontrado; 
        }catch (SQLException ex) {
            throw new RuntimeException("Não encontrei o cliente esperado erro: "+ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
        }
        
    }
    
    private Preco instanciarPreco (ResultSet rs,Orcamento orc)throws SQLException{
        
        Preco obj = new Preco();
        obj.setId(rs.getInt("id"));
        obj.setIpi(rs.getDouble("ipi"));
        obj.setIcms(rs.getDouble("icms"));
        obj.setSt(rs.getDouble("st"));
        obj.setPisCofins(rs.getDouble("pisCofins"));
        obj.setValorItem(rs.getDouble("valor"));
        obj.setOrcamento(orc);
        
        return obj;
        
    }

private Orcamento instanciarOrcamento(ResultSet rs)throws SQLException{
    
        Orcamento orc = new Orcamento();
        orc.setId(rs.getInt("id"));
        orc.setNumero(rs.getInt("numeroOrcamento"));
    
        return orc;

}


    public List<Preco> ListarPreco(){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
       
        try{
             con = conexao.ConnectionFactory.getConnection();
             stmt = con.prepareStatement(listarPreco);
            
             rs = stmt.executeQuery();
              List<Preco> listaPreco = new ArrayList<Preco>();
              Map<Integer, Orcamento> map = new HashMap<>();

             while (rs.next()) {
                 
                Orcamento orc = map.get(rs.getInt("orcamentoId"));
                 if(orc == null){
                   
                     orc = instanciarOrcamento(rs);
                     map.put(rs.getInt("orcamentoId"), orc);
                }
                 
                 Preco obj = instanciarPreco(rs, orc);
                 listaPreco.add(obj);
             }     
                 
              return listaPreco;
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar preços: "+ ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
         
         }
         
    }
        
    public List<Preco> consultarPorOrcamento (Orcamento orcamento) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
       
        try{
             con = conexao.ConnectionFactory.getConnection();
             stmt = con.prepareStatement(consultarPorOrcamento);
             
             stmt.setInt(1, orcamento.getId());
             rs = stmt.executeQuery();
             
              List<Preco> listarPrecoPorOrcamento = new ArrayList<Preco>();
              Map<Integer, Orcamento> map = new HashMap<>();

             while (rs.next()) {
                 
                Orcamento orc = map.get(rs.getInt("orcamentoId"));
                 if(orc == null){
                   
                     orc = instanciarOrcamento(rs);
                     map.put(rs.getInt("orcamentoId"), orc);
                }
                 
                 Preco obj = instanciarPreco(rs, orc);
                 listarPrecoPorOrcamento.add(obj);
             }     
                 
              return listarPrecoPorOrcamento;
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar preços: "+ ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            };
         
         }
             
    }   
      
}
