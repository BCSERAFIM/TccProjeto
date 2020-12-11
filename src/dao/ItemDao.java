
package dao;

import beans.Item;
import beans.Requisicao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ItemDao {
    
    private String inserirItem = " INSERT INTO item (nome,descricaoTecnica,aplicacao,necessidade,quantidade,requisicaoId) VALUES(?,?,?,?,?,?) ";
    private String atualizarItem = " UPDATE item SET nome= ?,descricaoTecnica=?,aplicacao=?,necessidade=?,quantidade=?,requisicaoId=? where id=? ";
    private String consultarItem = " SELECT item.*,requisicao.numero as numeroRequisicao "
                                  +" FROM item INNER JOIN requisicao " 
                                  +" ON item.requisicaoId = requisicao.id " 
                                  +" WHERE item.id = ? " ;
    private String deletatItem = " DELETE FROM item WHERE id = ? ";
    private String listarItens = " SELECT item.*,requisicao.numero as numeroRequisicao "
                                  +" FROM item INNER JOIN requisicao " 
                                  +" ON item.requisicaoId = requisicao.id ";
    private String listarPorRequisicao = " SELECT item.*,requisicao.numero as numeroRequisicao "
                                  +" FROM item INNER JOIN requisicao " 
                                  +" ON item.requisicaoId = requisicao.id " 
                                  +" WHERE requisicaoId = ? " ;
    
    public void inserirItem (Item item) throws SQLException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        try{
             con = conexao.ConnectionFactory.getConnection();
             stmt = con.prepareStatement(inserirItem,PreparedStatement.RETURN_GENERATED_KEYS);
             stmt.setString(1, item.getNome());
             stmt.setString(2,item.getDescricaoTecnica());
             stmt.setString(3, item.getAplicacao());
             stmt.setDate(4,new java.sql.Date(item.getNecessidade().getTime()));
             stmt.setInt(5, item.getQuantidade());
             stmt.setInt(6, item.getRequisição().getId());
            
            stmt.executeUpdate();
            int idItemGravado = lerIdItem(stmt);
            item.setId(idItemGravado);
            
        }catch (SQLException ex) {
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
    
    
     private int lerIdItem(PreparedStatement stmt) throws SQLException {
            ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
     }
     
     
     public void atualizarItem (Item item) throws SQLException{
            Connection con = null;
            PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(atualizarItem);
            
            stmt.setString(1, item.getNome());
            stmt.setString(2,item.getDescricaoTecnica());
            stmt.setString(3, item.getAplicacao());
            stmt.setDate(4,new java.sql.Date(item.getNecessidade().getTime()));
            stmt.setInt(5, item.getQuantidade());
            stmt.setInt(6, item.getRequisição().getId());
            stmt.setInt(7,item.getId());
            
            stmt.executeUpdate();
            if (stmt.executeUpdate() == 0) {
                throw new RuntimeException("Não encontrei Item para Atualizar");
            }
            
        }catch (SQLException ex) {
            throw new RuntimeException("Não foi possivel atualizar o item");
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
     
     public Item consultarItem (Integer id){
         
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         
        Item itemEncontrado = null;
        
         try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarItem);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
             if(rs.next()){
                 Requisicao req = instanciarRequisicao(rs);
                 itemEncontrado = instanciarItem(rs, req);
                 return itemEncontrado;
            }
            
            return itemEncontrado;
            
         }catch (SQLException ex) {
            throw new RuntimeException("Não encontrei o item esperado erro: "+ex.getMessage());
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
    
     private Item instanciarItem(ResultSet rs, Requisicao req) throws SQLException{
         
        Item obj = new Item();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setDescricaoTecnica(rs.getString("descricaoTecnica"));
        obj.setAplicacao(rs.getString("aplicacao"));
        obj.setNecessidade(rs.getDate("necessidade"));
        obj.setQuantidade(rs.getInt("quantidade"));
        obj.setRequisição(req);
        return obj;
         
     }
     
     
     private Requisicao instanciarRequisicao(ResultSet rs) throws SQLException{
         
         Requisicao req = new Requisicao();
         req.setId(rs.getInt("id"));
         req.setNumero(rs.getInt("numeroRequisicao"));
      
        return req;
         
     }
     
     public void deletarItem (Integer id){
         
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deletatItem);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
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
     
     public List<Item> listarItens () {
         
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         
        
         
         try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(listarItens);
           
            rs = stmt.executeQuery();
            
            List<Item> listarItens = new ArrayList<Item>();
            Map<Integer, Requisicao> map = new HashMap<>();
            
            while (rs.next()) {
                
                Requisicao req = map.get(rs.getInt("requisicaoId"));
                
                if(req==null){
                    req = instanciarRequisicao(rs);
                    map.put(rs.getInt("requisicaoId"), req);
                }
                Item obj = instanciarItem(rs, req);
                listarItens.add(obj);
            }
             return listarItens;
             
         }catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar itens: "+ ex.getMessage());
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
     
     
     public List<Item>  consultarPorRequisicao (Requisicao requisicao){
         
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         try{
             
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(listarPorRequisicao);
                        
            stmt.setInt(1, requisicao.getId());
            rs = stmt.executeQuery();
            
            List<Item> listaItemPorRequisicao = new ArrayList<Item>();
            Map<Integer, Requisicao> map = new HashMap<>();
            
            while (rs.next()) {
                
                Requisicao req = map.get(rs.getInt("requisicaoId"));
                
                if(req==null){
                    req = instanciarRequisicao(rs);
                    map.put(rs.getInt("requisicaoId"), req);
                }
                Item obj = instanciarItem(rs, req);
                listaItemPorRequisicao.add(obj);
            }
            
            return listaItemPorRequisicao;
            
         }catch (SQLException ex) {
            throw new RuntimeException("Não encontrei o item esperado erro: "+ex.getMessage());
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

