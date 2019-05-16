package com.prjcadcliente.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prjcadcliente.dominio.Cliente;

/**
 *<b>CRUDCliente</b>
 * Essa classe permite manipular as informa��es do cliente. Aqui voc� 
 * encontrar� os seguintes comandos:
 * <ul>
 * <Li>Cadastro</Li>
 * <Li>Pesquisar por nome e por id</Li>
 * <Li>Atualizar</Li>
 * <Li>Deletar</Li>
 * @author matheus.mmello
 *
 */
public class CRUDCliente {
	
	
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public String cadastrar(Cliente cliente) {
		return null;
		
		
		
		}



	public String atualizar(Cliente cliente) {
		String msg = ""; 
		//Cria��o dos objetos para a conex�o com o banco de dados 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			String consulta = "UPDATE tbclient SET nome=?,email=?,telefone=?,idade=? WHERE id=(?,?,?,?)";
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			pst.setInt(5, cliente.getId());
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Atualizado com sucesso";
			else
				msg = "n�o foi possivel atualizar";
				
		}
	    catch(SQLException ex) {
		     msg = "Erro ao tentar atualizar:"+ex.getMessage();
		     
	    }
		catch(Exception e) {
			msg = "Erro Inesperado:"+e.getMessage();
			
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}	
		return msg;
        
	}
	public String deletar(Cliente cliente) {
		String msg = ""; 
		//Cria��o dos objetos para a conex�o com o banco de dados 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			String consulta = "DELETE FROM tbclient SET nome=?,email=?,telefone=?,idade=? WHERE id=(?,?,?,?)";
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			pst.setInt(5, cliente.getId());
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Deletado  com sucesso";
			else
				msg = "n�o foi possivel  Deletar";
				
		}
	    catch(SQLException ex) {
		     msg = "Erro ao tentar Deletar:"+ex.getMessage();
		     
	    }
		catch(Exception e) {
			msg = "Erro Inesperado:"+e.getMessage();
			
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}	
		return msg;

	}
	
	public List<Cliente> PesquisarPorNome(String nome){
		List<Cliente> lista = new ArrayList<Cliente>();
		
		try { 
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb","root","");
		
		
		String consulta ="Select * from tbcliente where nome=?";
		
		pst = con.prepareStatement(consulta);
		
		pst.setString(1, nome );
		
		
		rs = pst.executeQuery();
		
		
		while(rs.next()) {
			lista.add(new Cliente(
					rs.getInt(0),
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4)
					));
		}
	}
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();} 
		}
		
		
		return lista;
		
	}
		
		
	
	public Cliente PesquisarPorID(int id){
		return null;
	}
	
	
	public List<Cliente> PesquisarTodos(){
		return null;
	}
}