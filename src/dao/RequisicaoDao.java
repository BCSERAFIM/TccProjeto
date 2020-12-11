
package dao;

import beans.Item;
import beans.Requisicao;
import beans.Requisitante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequisicaoDao {
    
    private String inserirRequisicao = " INSERT INTO requisicao (data,numero,itemId) VALUES(?,?,?) ";
    private String consultarRequisicao = " SELECT requisicao.*,requisitante.nome as nomeRequisitante "
                                           +" FROM requisicao INNER JOIN requisitante "
                                           +" ON requisicao.requisitanteId = requisitante.id "
                                           +" WHERE requisicao.id = ? ";
    private String deletatRequisicao = " DELETE FROM requisicao WHERE id = ? ";
    private String listarRequisicao = " SELECT requisicao.*,requisitante.nome as nomeRequisitante "
                                           +" FROM requisicao INNER JOIN requisitante "
                                           +" ON requisicao.requisitanteId = requisitante.id ";
    private String consultarPorRequisitante = " SELECT requisicao.*,requisitante.nome as nomeRequisitante "
                                           +" FROM requisicao INNER JOIN requisitante "
                                           +" ON requisicao.requisitanteId = requisitante.id "
                                           +" WHERE requisitante.id = ? ";
    
    
    
    public void inserirRequisicao(Requisicao requisicao)throws SQLException{
        
        
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(inserirRequisicao,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new java.sql.Date(requisicao.getDataMomento().getTime()));
            stmt.setInt(2,requisicao.getNumero());
            stmt.setInt(3,requisicao.getItem().getId());
            
            stmt.executeUpdate();
            int idRequisicaoGravado = lerIdRequisicao(stmt);
            requisicao.setId(idRequisicaoGravado);
            
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir Requisição: "+ex.getMessage());
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
    
    
    
    private int lerIdRequisicao(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
     }
    
    
    
    public Requisicao consultarRequisicao(Integer id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Requisicao requisicaoEncontrado = null;
        
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarRequisicao);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                
                Requisitante req = instanciarRequisitante(rs);
                requisicaoEncontrado = instanciarRequisicao(rs, req);
                return requisicaoEncontrado;
                
            }
                    
           return requisicaoEncontrado;
        }catch (SQLException ex) {
            throw new RuntimeException("Não encontrei a requisicão esperada: "+ex.getMessage());
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
    
    
    private Requisicao instanciarRequisicao(ResultSet rs, Requisitante req)throws SQLException{
        
        Requisicao obj = new Requisicao();
        obj.setId(rs.getInt("id"));
        obj.setDataMomento(rs.getDate("data"));
        obj.setNumero(rs.getInt("numero"));
        obj.setRequisitante(req);
        
        return obj;
        
    }
    
    private Requisitante instanciarRequisitante (ResultSet rs)throws SQLException{
        
        Requisitante req = new Requisitante();
        req.setId(rs.getInt("id"));
        req .setNome(rs.getString("nomeRequisitante"));
        return req;
        
    }
    
    
    public void deletarRequisicao (Integer id){
        
        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deletatRequisicao);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar uma Requisicao: "+ ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt: " + ex.getMessage());
            };
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            };
        }
    }
    
    public List<Requisicao> listaRequisicoes (){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Requisicao> listaRequisicoes = new ArrayList<Requisicao>();
        Map<Integer, Requisitante> map = new HashMap<>();
        
        try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(listarRequisicao);
            
            rs = stmt.executeQuery();
            
             while (rs.next()) {
               Requisitante req = map.get(rs.getInt("requisitanteId"));
               
               if(req==null){
                   
                req = instanciarRequisitante(rs);
                map.put(rs.getInt("requisitanteId"), req);
                
                }
               
               Requisicao obj = instanciarRequisicao(rs, req);
               listaRequisicoes.add(obj);
               }
            
            return listaRequisicoes;
            
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar requisições: "+ ex.getMessage());
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
    
    
    public List<Requisicao> consultarPorRequisitante (Requisitante requisitante){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarPorRequisitante);
                        
            stmt.setInt(1, requisitante.getId());
            rs = stmt.executeQuery();
             
             List<Requisicao> listaRequisicaoPorRequisitante = new ArrayList<Requisicao>();
             Map<Integer, Requisitante> map = new HashMap<>();
             while (rs.next()) {
                 
                Requisitante req = map.get(rs.getInt("requisitanteId"));
                 
                 if(req==null){
                     
                     req = instanciarRequisitante(rs);
                     map.put(rs.getInt("requisitanteId"), req);
                 }
                 Requisicao obj = instanciarRequisicao(rs, req);
                 listaRequisicaoPorRequisitante.add(obj);
             }
             
             return listaRequisicaoPorRequisitante;
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
