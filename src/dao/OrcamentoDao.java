
package dao;


import beans.Fornecedor;
import beans.Orcamento;
import beans.Preco;
import beans.Requisitante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrcamentoDao {
    private String inserirOrcamento = " INSERT INTO orcamento (data,numero,fornecedorId) VALUES(?,?,?) ";
    private String consultarOrcamento = " SELECT orcamento.*,fornecedor.nome as nomeFornecedor "
                                           +" FROM orcamento INNER JOIN fornecedor "
                                           +" ON orcamento.fornecedorId = fornecedor.id "
                                           +" WHERE orcamento.id = ? ";
    private String deletarOrcamento = " DELETE FROM orcamento WHERE id = ? ";
    private String listarOrcamento = " SELECT orcamento.*,fornecedor.nome as nomeFornecedor "
                                           +" FROM orcamento INNER JOIN fornecedor "
                                           +" ON orcamento.fornecedorId = fornecedor.id ";
    private String consultarPorFornecedor = " SELECT orcamento.*,fornecedor.nome as nomeFornecedor "
                                           +" FROM orcamento INNER JOIN fornecedor "
                                           +" ON orcamento.fornecedorId = fornecedor.id "
                                           +" WHERE fornecedor.id = ? ";
    
    
    public void inserirOrcamento(Orcamento orcamento)throws SQLException{
        
        
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(inserirOrcamento,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, new java.sql.Date(orcamento.getDataMomento().getTime()));
            stmt.setInt(2,orcamento.getNumero());
            stmt.setInt(3,orcamento.getFornecedor().getId());
            
            stmt.executeUpdate();
            int idOrcamentoGravado = lerIdOrcamento(stmt);
            orcamento.setId(idOrcamentoGravado);
            
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir Orcamento: "+ex.getMessage());
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
    
    
    
    private int lerIdOrcamento(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
     }
    
    
    
    public Orcamento consultarOrcamento (Integer id){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Orcamento orcamentoEncontrado = null;
        
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarOrcamento);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                
                Fornecedor forn = instanciarFornecedor(rs);
                orcamentoEncontrado = instanciarOrcamento(rs, forn);
                return orcamentoEncontrado;
                
            }
                    
           return orcamentoEncontrado;
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
    
    
    private Orcamento instanciarOrcamento (ResultSet rs, Fornecedor forn)throws SQLException{
        
        Orcamento obj = new Orcamento();
        obj.setId(rs.getInt("id"));
        obj.setDataMomento(rs.getDate("data"));
        obj.setNumero(rs.getInt("numero"));
        obj.setFornecedor(forn);
        
        return obj;
        
    }
    
    private Fornecedor instanciarFornecedor (ResultSet rs)throws SQLException{
        
        Fornecedor forn = new Fornecedor();
        forn.setId(rs.getInt("id"));
        forn.setNome(rs.getString("nomeFornecedor"));
        return forn;
        
    }
    
    
    public void deletarOrcamento (Integer id){
        
        
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(deletarOrcamento);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar um Orçamento: "+ ex.getMessage());
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
    
    public List<Orcamento> listaOrcamento (){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Orcamento> listaOrcamentos = new ArrayList<Orcamento>();
        Map<Integer, Fornecedor> map = new HashMap<>();
        
        try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(listarOrcamento);
            
            rs = stmt.executeQuery();
            
             while (rs.next()) {
               Fornecedor forn = map.get(rs.getInt("fornecedorId")); 
              if(forn==null){
                  
                  forn = instanciarFornecedor(rs);
                  map.put(rs.getInt("fornecedorId"), forn);
                  
              }
               Orcamento obj = instanciarOrcamento(rs, forn);
               listaOrcamentos.add(obj);
               
               }
            
            return listaOrcamentos;
            
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar orcamentos: "+ ex.getMessage());
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
    
    public List<Orcamento> consultarPorFornecedor (Fornecedor fornecedor){
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         try{
            con = conexao.ConnectionFactory.getConnection();
            stmt = con.prepareStatement(consultarPorFornecedor);
                        
            stmt.setInt(1, fornecedor.getId());
            rs = stmt.executeQuery();
            
            List<Orcamento> listaOrcamentoPorFornecedor = new ArrayList<Orcamento>();
            Map<Integer, Fornecedor> map = new HashMap<>();
             
              while (rs.next()) {
               Fornecedor forn = map.get(rs.getInt("fornecedorId")); 
              if(forn==null){
                  
                  forn = instanciarFornecedor(rs);
                  map.put(rs.getInt("fornecedorId"), forn);
                  
              }
               Orcamento obj = instanciarOrcamento(rs, forn);
               listaOrcamentoPorFornecedor.add(obj);
               
               }
                        
             
          return listaOrcamentoPorFornecedor;   
         }catch (SQLException ex) {
            throw new RuntimeException("Não encontrei o Orcamento esperado erro: "+ex.getMessage());
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
