
package tccprojeto;

import beans.Empresa;
import beans.Fornecedor;
import beans.Item;
import beans.Orcamento;
import beans.Preco;
import beans.Requisicao;
import beans.Requisitante;
import dao.EmpresaDao;
import dao.FornecedorDao;
import dao.ItemDao;
import dao.OrcamentoDao;
import dao.PrecoDao;
import dao.RequisicaoDao;
import dao.RequisitanteDao;
import java.sql.SQLException;
import java.util.Scanner;


public class Programa {

 
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
      
        EmpresaDao empresaDao = new EmpresaDao();
        FornecedorDao forncedorDao = new FornecedorDao();
        RequisitanteDao requisitanteDao = new RequisitanteDao();
        ItemDao itemDao = new ItemDao();
        OrcamentoDao orcamentoDao = new OrcamentoDao();
        PrecoDao precoDao = new PrecoDao();
        RequisicaoDao requisicaoDao = new RequisicaoDao();
       
        while (true) {
            try {
                System.out.println();
		System.out.println();
		System.out.println("Escolha uma das opções e tecle <ENTER>: ");
		System.out.println("  1 - Cadastrar Empresa");
		System.out.println("  2 - Cadastrar Requisitante");
		System.out.println("  3 - Cadastrar Fornecedor");
                System.out.println("  4 - Sair");
		System.out.print("  : ");
		String opcao = sc.nextLine();
		System.out.println();
		System.out.print("Opção escolhida [" + opcao + "] \n");
                
                switch (opcao) {
                  case "1":
                    System.out.println("Digitar dados para cadastro da Empresa");  
                      System.out.print("  CNPJ: ");
		      String cnpj = sc.nextLine();
                      System.out.print("  Nome: ");
                      String nome = sc.nextLine();
                      System.out.print("  Pais: ");
                      String pais = sc.nextLine();
                      System.out.print("  CEP: ");
                      Integer cep = sc.nextInt();
                      sc.nextLine();
                      System.out.print("  Endereço: ");
                      String endereco = sc.nextLine();
                      System.out.print("  Numero da Rua: ");
                      Integer numeroRua = sc.nextInt();
                      sc.nextLine();
                      System.out.print("  Cidade: ");
                      String cidade = sc.nextLine();
                      System.out.print("  Estado: ");
                      String estado = sc.nextLine();
                      System.out.print("  Teleforne: ");
                      String telefone = sc.nextLine();
                      Empresa novaEmpresa  = new Empresa(cnpj,nome,pais,cep,endereco,numeroRua,cidade,estado,telefone);
                      try{
                        empresaDao.inserirEmpresa(novaEmpresa);
                        System.out.println("Empresa "+nome+" Cadastrada com sucesso, numero ID: "+novaEmpresa.getId());
                    }catch(SQLException ex){
                        String mensagem = ex.getMessage();
                        System.out.println(mensagem);
                    }
                    break;
                  case "2":
                       System.out.println("Digitar dados para cadastro de Requisitante");
                       System.out.println("Digitar CNPJ da Empresa");  
                       System.out.print("  CNPJ: ");
                       String cnpjCadastroRequisitante = sc.nextLine();
                       System.out.print("  Nome: ");
                       String nomeRequisitante = sc.nextLine();
                       System.out.print("  Email: ");
                       String emailRequisitante = sc.nextLine();
                       System.out.print("  Celular: ");
                       String celularRequisitante = sc.nextLine();
                       Empresa empresaRequisitante = empresaDao.consultarEmpresaPorCnpj(cnpjCadastroRequisitante);
                       empresaRequisitante.getId();
                       Requisitante novoRequisitente = new Requisitante(nomeRequisitante,emailRequisitante,celularRequisitante,empresaRequisitante);
                      try{
                          requisitanteDao.inserirRequisitatne(novoRequisitente);
                          System.out.println("Requisitante "+nomeRequisitante+" Cadastrada com sucesso, numero ID: "+novoRequisitente.getId());
                      }catch(SQLException ex){
                        String mensagem = ex.getMessage();
                        System.out.println(mensagem);
                    }
                          
                    break;  
                  case "3":
                      System.out.println("Digitar dados para cadastro de Fornecedor");
                       System.out.println("Digitar CNPJ da Empresa");  
                       System.out.print("  CNPJ: ");
                       String cnpjCadastroFornecedor = sc.nextLine();
                       System.out.print("  Nome: ");
                       String nomeFornecedor = sc.nextLine();
                       System.out.print("  Email: ");
                       String emailFornecedor = sc.nextLine();
                       System.out.print("  Celular: ");
                       String celularFornecedor = sc.nextLine();
                       System.out.print("  Familia: ");
                       String familiaFornecedor = sc.nextLine();
                       Empresa empresaFornecedor = empresaDao.consultarEmpresaPorCnpj(cnpjCadastroFornecedor);
                       empresaFornecedor.getId();
                       Fornecedor novoFornecedor = new Fornecedor(nomeFornecedor,emailFornecedor,celularFornecedor,familiaFornecedor,empresaFornecedor);
                      try{
                          forncedorDao.inserirFornecedor(novoFornecedor);
                          System.out.println("Fornecedor "+nomeFornecedor+" Cadastrado com sucesso, numero ID: "+novoFornecedor.getId());
                      }catch(SQLException ex){
                        String mensagem = ex.getMessage();
                        System.out.println(mensagem);
                    }
                    break;
                  case "4":
                    System.out.println("Fim da operação!");
                    break;
                default:
		System.out.println("Opção Invalida.");
		break;    
                }
                if (opcao.equals("4")) {
                     
		break;
		}
            }catch (Exception ex) {
		System.out.println(" Falha na operação. Mensagem =" + ex.getMessage());
				
            }
        }
        sc.close();
    }
    
}
