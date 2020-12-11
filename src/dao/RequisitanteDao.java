
package dao;

import beans.Empresa;
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


public class RequisitanteDao {
    
    
    private String inserirRequisitante = " INSERT INTO requisitante (nome,email,celular,empresaId) VALUES(?,?,?,?) ";
    private String atualizarRequisitante = " UPDATE requisitante SET nome= ?,email=?,celular=? where id=? ";
    private String deletatRequisitante = " DELETE FROM requisitante WHERE id = ? ";
    private String consultarRequisitante = " SELECT requisitante.*,empresa.nome as NomeEmpresa "
                                           +" FROM requisitante INNER JOIN empresa "
                                           +" ON requisitante.empresaId = empresa.id "
                                           +" WHERE requisitante.id = ? ";
    private String listarRequisitante = " SELECT requisitante.*,empresa.nome as NomeEmpresa "
                                           +" FROM requisitante INNER JOIN empresa "
                                           +" ON requisitante.empresaId = empresa.id ";
    private String consultarPorEmpresa = " SELECT requisitante.*,empresa.nome as NomeEmpresa "
                                           +" FROM requisitante INNER JOIN empresa "
                                           +" ON requisitante.empresaId = empresa.id "
                                           +" WHERE empresa.id = ? ";
    
    
    
    public void inserirRequisitatne (Requisitante requisitante)throws SQLException{
        
     Connection con = null;
     PreparedStatement stmt = null;
        
    try{
            
           con = conexao.ConnectionFactory.getConnection();
           stmt = con.prepareStatement(inserirRequisitante,PreparedStatement.RETURN_GENERATED_KEYS); 
           stmt.setString(1, requisitante.getNome());
           stmt.setString(2, requisitante.getEmail());
           stmt.setString(3, requisitante.getCelular());
           stmt.setInt(4, requisitante.getEmpresa().getId());
           
           stmt.executeUpdate();
           int idRequisitanteGravado = lerIdRequisitante(stmt);
            requisitante.setId(idRequisitanteGravado);
           
            
    }catch (SQLException ex) {
        throw new RuntimeException("Erro ao inserir Requisitante: "+ex.getMessage());
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
    
    private int lerIdRequisitante(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
     }
    
    
    
    public Requisitante consultarRequisitante(Integer id){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Requisitante requisitanteEncontrado = null;
        
        
         try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarRequisitante);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                Empresa emp = instanciarEmpresa(rs);
                requisitanteEncontrado = instanciarRequisitante(rs, emp);
                return requisitanteEncontrado;
            }
             
            return requisitanteEncontrado;
         }catch (SQLException ex) {
            throw new RuntimeException("Não encontrei a requisitante esperada: "+ ex.getMessage());
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
    
    
    private Requisitante instanciarRequisitante(ResultSet rs, Empresa emp)throws SQLException{
        
        Requisitante obj = new Requisitante();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setCelular(rs.getString("celular"));
        obj.setEmpresa(emp);
        
         return obj;
        
    }
    
    private Empresa instanciarEmpresa(ResultSet rs)throws SQLException{
        
        Empresa emp = new Empresa();
        emp.setId(rs.getInt("id"));
        emp.setNomeEmpresa(rs.getString("NomeEmpresa"));
        
        return emp;
        
    }
    
    public void deletarRequisitante (Integer id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deletatRequisitante);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar uma Requisitante: " + ex.getMessage());
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
    
    public List<Requisitante> listaRequisitantes(){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Requisitante> listaRequisitantes = new ArrayList<Requisitante>();
        Map<Integer, Empresa> map = new HashMap<>();
        try{
            
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(listarRequisitante);
            
            rs = stmt.executeQuery();
            
             while (rs.next()) {
               
                Empresa emp = map.get(rs.getInt("empresaId")); 
                 
                if(emp==null){
                    
                  emp = instanciarEmpresa(rs);
                  map.put(rs.getInt("empresaId"), emp);
                }
                
                Requisitante obj = instanciarRequisitante(rs, emp);
                listaRequisitantes.add(obj);
             
             }
            
            
           return listaRequisitantes ; 
           
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar requisitantes: "+ ex.getMessage());
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
    
    public void atualizarRequisitante (Requisitante requisitante)throws SQLException{
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(atualizarRequisitante);
            stmt.setString(1, requisitante.getNome());
            stmt.setString(2, requisitante.getEmail());
            stmt.setString(3, requisitante.getCelular());
            stmt.setInt(4, requisitante.getId());
            
            stmt.executeUpdate();
            if (stmt.executeUpdate() == 0) {
                throw new RuntimeException("Não encontrei requisitante para Atualizar");
            }
            
        }catch (SQLException ex) {
            throw new RuntimeException("Não foi possivel atualizar o Requisitante erro: " + ex.getMessage());
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
    
    public List<Requisitante> consultarPorEmpresa (Empresa empresa){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarPorEmpresa);
                        
            stmt.setInt(1, empresa.getId());
            rs = stmt.executeQuery();
            
            List<Requisitante> listaRequisitantePorEmpresa = new ArrayList<Requisitante>();
            Map<Integer, Empresa> map = new HashMap<>();
            
            while (rs.next()) {
                 Empresa emp = map.get(rs.getInt("empresaId")); 
                 
                if(emp == null){
                    
                  emp = instanciarEmpresa(rs);
                  map.put(rs.getInt("empresaId"), emp);
                }
                
                Requisitante obj = instanciarRequisitante(rs, emp);
                listaRequisitantePorEmpresa.add(obj);
            }
                         
             return listaRequisitantePorEmpresa;
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
