
package dao;

import beans.Empresa;
import beans.Fornecedor;
import beans.Requisitante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmpresaDao {
    
     private String inserirEmpresa = " INSERT INTO empresa (cnpj,nome,pais,cep,endereco,numeroRua,cidade,estado,telefone) VALUES(?,?,?,?,?,?,?,?,?) ";
     private String consultarEmpresa = " SELECT * FROM empresa WHERE id = ?";
     private String consultarEmpresaPorCnpj = " SELECT * FROM empresa WHERE cnpj = ?";
     private String atualizarEmpresa = " UPDATE empresa SET cnpj=?,nome=?,pais=?,cep=?,endereco=?,numeroRua=?,cidade=?,estado=?,telefone=? where id=? ";
     private String deletatEmpresa = " DELETE FROM empresa WHERE id = ? ";
     private String listarEmpresa = " SELECT * FROM empresa ";
     
     
     
     public void inserirEmpresa (Empresa empresa)throws SQLException{
         
        Connection con = null;
        PreparedStatement stmt = null;
         
         try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(inserirEmpresa,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, empresa.getCnpj());
            stmt.setString(2, empresa.getNomeEmpresa());
            stmt.setString(3, empresa.getPais());
            stmt.setInt(4, empresa.getCep());
            stmt.setString(5, empresa.getEndereco());
            stmt.setInt(6, empresa.getNumeroRua());
            stmt.setString(7, empresa.getCidade());
            stmt.setString(8, empresa.getEstado());
            stmt.setString(9, empresa.getTelefoneEmpresa());
           
            
            stmt.executeUpdate();
            int idEmpresaGravado = lerIdEmpresa(stmt);
            empresa.setId(idEmpresaGravado);
            
         }catch (SQLException ex) {
        throw new RuntimeException("Erro ao inserir Empresa - Erro: "+ex.getMessage());
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
   
    private int lerIdEmpresa (PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
     } 
     
     public Empresa consultarEmpresa (Integer id){
         
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Empresa empresaEncontrada = null;
        
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarEmpresa);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
              
             empresaEncontrada = instanciarEmpresa(rs);
             return empresaEncontrada;  
             
           }
            
            
            return empresaEncontrada;
        }catch (SQLException ex) {
            throw new RuntimeException("Não encontrei a empresa esperada: "+ ex.getMessage());
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
     
     private Empresa instanciarEmpresa (ResultSet rs )throws SQLException{
         
         Empresa obj = new Empresa();
         obj.setId(rs.getInt("id"));
         obj.setCnpj(rs.getString("cnpj"));
         obj.setNomeEmpresa(rs.getString("nome"));
         obj.setPais(rs.getString("pais"));
         obj.setCep(rs.getInt("cep"));
         obj.setEndereco(rs.getString("endereco"));
         obj.setNumeroRua(rs.getInt("numeroRua"));
         obj.setCidade(rs.getString("cidade"));
         obj.setEstado(rs.getString("estado"));
         obj.setTelefoneEmpresa(rs.getString("telefone"));
                  
         return obj;
         
     }
     
      public Empresa consultarEmpresaPorCnpj (String cnpj){
         
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Empresa empresaEncontrada = null;
        
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarEmpresaPorCnpj);
            stmt.setString(1, cnpj);
            rs = stmt.executeQuery();
            
            if(rs.next()){
              
             empresaEncontrada = instanciarEmpresa(rs);
             return empresaEncontrada;  
             
           }
            
            
            return empresaEncontrada;
        }catch (SQLException ex) {
            throw new RuntimeException("Não encontrei a empresa esperada: "+ ex.getMessage());
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
     
     public void atualizarEmpresa (Empresa empresa) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(atualizarEmpresa);
            stmt.setString(1, empresa.getCnpj());
            stmt.setString(2, empresa.getNomeEmpresa());
            stmt.setString(3, empresa.getPais());
            stmt.setInt(4, empresa.getCep());
            stmt.setString(5, empresa.getEndereco());
            stmt.setInt(6, empresa.getNumeroRua());
            stmt.setString(7, empresa.getCidade());
            stmt.setString(8, empresa.getEstado());
            stmt.setString(9, empresa.getTelefoneEmpresa());
            stmt.setInt(10, empresa.getId());
            
            stmt.executeUpdate();
            if (stmt.executeUpdate() == 0) {
                throw new RuntimeException("Não encontrei Empresa para Atualizar");
            }
        }catch (SQLException ex) {
            throw new RuntimeException("Não foi possivel atualizar a Empresa erro: "+ex.getMessage());
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
     
     public void deletarEmpresa (Integer id){
         
         Connection con = null;
        PreparedStatement stmt = null;
        try {
            
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deletatEmpresa);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
            
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar uma Empresa: "+ ex.getMessage());
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
     
     public List<Empresa> listaEmpresas(){
         
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Empresa> listaEmpresas = new ArrayList<Empresa>();
         
        try{
            
            con = conexao.ConnectionFactory.getConnection();
             stmt = con.prepareStatement(listarEmpresa);
            
             rs = stmt.executeQuery();

             while (rs.next()) {
                 Empresa emp = instanciarEmpresa(rs);
                 listaEmpresas.add(emp);
             }
                        
            return listaEmpresas;
            
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
