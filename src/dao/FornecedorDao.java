
package dao;

import beans.Empresa;
import beans.Fornecedor;
import beans.Orcamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FornecedorDao {
    
    
    
    
    private String inserirFornecedor = " INSERT INTO fornecedor (nome,email,celular,familia,empresaId) VALUES(?,?,?,?,?) ";
    private String atualizarFornecedor = " UPDATE fornecedor SET nome=?,email=?,celular=?,familia=? where id=? ";
    private String deletatFornecedor = " DELETE FROM fornecedor WHERE id = ? ";
    private String consultarFornecedor = " SELECT fornecedor.*,empresa.nome as NomeEmpresa "
                                           +" FROM fornecedor INNER JOIN empresa "
                                           +" ON fornecedor.empresaId = empresa.id "
                                           +" WHERE fornecedor.id = ? ";
    private String listarFornecedor = " SELECT fornecedor.*,empresa.nome as NomeEmpresa "
                                           +" FROM fornecedor INNER JOIN empresa "
                                           +" ON fornecedor.empresaId = empresa.id ";
    private String consultarPorEmpresa = " SELECT fornecedor.*,empresa.nome as NomeEmpresa "
                                           +" FROM fornecedor INNER JOIN empresa "
                                           +" ON fornecedor.empresaId = empresa.id "
                                           +" WHERE empresa.id = ? ";
    
    
    
    public void inserirFornecedor(Fornecedor fornecedor)throws SQLException{
        
     Connection con = null;
     PreparedStatement stmt = null;
     
      try{
           con = conexao.ConnectionFactory.getConnection();
           stmt = con.prepareStatement(inserirFornecedor,PreparedStatement.RETURN_GENERATED_KEYS);
           stmt.setString(1, fornecedor.getNome());
           stmt.setString(2, fornecedor.getEmail());
           stmt.setString(3, fornecedor.getCelular());
           stmt.setString(4, fornecedor.getFamilia());
           stmt.setInt(5, fornecedor.getEmpresa().getId());
           
           stmt.executeUpdate();
            int idFornecedorGravado = lerIdFornecedor(stmt);
            fornecedor.setId(idFornecedorGravado);
          
      }catch (SQLException ex) {
        throw new RuntimeException("Erro ao inserir Fornecedor - Erro: "+ex.getMessage());
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
    
      private int lerIdFornecedor(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
     } 
      
    
    public Fornecedor consultarFornecedor (Integer id){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Fornecedor fornecedorEncontrado = null;
        
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarFornecedor);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                Empresa emp = instanciarEmpresa(rs);
                fornecedorEncontrado = instanciarFornecedor(rs, emp);
                return fornecedorEncontrado;
            }
            
            return fornecedorEncontrado;
            
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
    
   
   private Fornecedor instanciarFornecedor (ResultSet rs, Empresa emp)throws SQLException{
       
       Fornecedor obj = new Fornecedor();
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        obj.setEmail(rs.getString("email"));
        obj.setCelular(rs.getString("celular"));
        obj.setFamilia(rs.getString("familia"));
        obj.setEmpresa(emp);
       
        return obj;
             
   }
   
   private Empresa instanciarEmpresa(ResultSet rs)throws SQLException{
        
        Empresa emp = new Empresa();
        emp.setId(rs.getInt("id"));
        emp.setNomeEmpresa(rs.getString("NomeEmpresa"));
        
        return emp;
       
   }
   
   public void deletarFornecedor (Integer id){
       
       
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deletatFornecedor);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar um Fornecedor: " + ex.getMessage());
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

    public List<Fornecedor> listaFornecedores(){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
        Map<Integer, Empresa> map = new HashMap<>();
        try{
            
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(listarFornecedor);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
               Empresa emp = map.get(rs.getInt("empresaId"));
               
               if(emp==null){
                  emp = instanciarEmpresa(rs);
                  map.put(rs.getInt("empresaId"), emp);
               }
               Fornecedor obj = instanciarFornecedor(rs, emp);
               listaFornecedores.add(obj);
            }
            
            return listaFornecedores;
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
       
       
   public void atualizarFornecedor (Fornecedor fornecedor)throws SQLException{
       
      Connection con = null;
      PreparedStatement stmt = null; 
      
      try {
          con = conexao.ConnectionFactory.getConnection();
          stmt = con.prepareStatement(atualizarFornecedor);
          
           stmt.setString(1, fornecedor.getNome());
           stmt.setString(2, fornecedor.getEmail());
           stmt.setString(3, fornecedor.getCelular());
           stmt.setString(4, fornecedor.getFamilia());
           stmt.setInt(5, fornecedor.getId());
          
          stmt.executeUpdate();
            if (stmt.executeUpdate() == 0) {
                throw new RuntimeException("Não encontrei fornecedor para Atualizar");
            }
          
      }catch (SQLException ex) {
            throw new RuntimeException("Não foi possivel atualizar o Fornecedor erro: " + ex.getMessage());
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
   
   public List<Fornecedor> consultarPorEmpresa (Empresa empresa){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarPorEmpresa);
                        
            stmt.setInt(1, empresa.getId());
            rs = stmt.executeQuery();
            
            List<Fornecedor> listaFornecedorPorEmpresa = new ArrayList<Fornecedor>();
            Map<Integer, Empresa> map = new HashMap<>();
            
            while (rs.next()) {
                 Empresa emp = map.get(rs.getInt("empresaId")); 
                 
                if(emp == null){
                    
                  emp = instanciarEmpresa(rs);
                  map.put(rs.getInt("empresaId"), emp);
                }
                
                Fornecedor obj = instanciarFornecedor(rs, emp);
                listaFornecedorPorEmpresa.add(obj);
            }
                         
             return listaFornecedorPorEmpresa;
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
