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
 * <b>CRUDCliente</b><br>
 * Essa classe permite manipular as informações do cliente. Aqui você
 * encontrará os seguintes comandos: 
 * <ul>
 * 	<li>Cadastro</li>
 * 	<li>Pesquisar por nome e id</li>
 * 	<li>Atualizar</li>
 * 	<li>Deletar</li>
 * </ul>
 * 
 * @author luiz.hssantos11
 *
 */
//Declaração das instancias de comunicação com o bando de dados

public class CRUDCliente {
	
	private Connection con = null;//Estabelecer a comunicação com o banco de dados
	private ResultSet rs = null;//Guardar os retornos dos selects no banco de dados
	private PreparedStatement pst = null;//Executa as consultas de SQL
	
	public String cadastrar(Cliente cliente) {
		
		String msg = "";
		
		//Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");//conexao com o banco de dados
			
			String consulta = "INSERT INTO tbcliente(nome, email, telefone, idade) values(?, ?, ?, ?)";//"?" é igual parametros
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Cadastro realizado com sucesso!";
			else
				msg = "Não foi possível cadastrar!";
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar cadastrar: "+ex.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		return msg;
		
	}
	public String atualizar(Cliente cliente) {
		String msg = "";
		
		//Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");
			
			
			//------------ Não se faz update sem WHERE
			String consulta = "UPDATE tbcliente SET nome=?, email=?, telefone=?, idade=? WHERE id?";
			
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			pst.setInt(5, cliente.getId());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Atualizado com sucesso!";
			else
				msg = "Não foi possível atualizar!";
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar atualizar: "+ex.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		return msg;
		
	}
	public String deletar(Cliente cliente) {
		String msg = "";
		
		//Criação dos objetos para a conexao com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");
			
			String consulta = "DELETE FROM tbcliente WHERE id=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setInt(1, cliente.getId());
			
			int r = pst.executeUpdate();
			
			
			if(r > 0)
				msg = "Cadastro deletado com sucesso!";
			else
				msg = "Não foi possível deletar!";
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar deletar: "+ex.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		return msg;
	}
	
	public List<Cliente> PesquisarPorNome(String nome){
	
		List <Cliente> lista = new ArrayList<Cliente>();
		
		try {
			//carregar o drive de comunicação com o banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciar de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");
			
			//vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbcliente where nome=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, nome);
			
			//Vamos executar a consulta e guardar o resultado na variavel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que está no rs e adicioná-lo
			 * a lista cheia de clientes para, então retorná-la
			 */
			
			while(rs.next()) {
				lista.add(new Cliente(
						rs.getInt(0),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)
						));
			}//fim do while/enquanto
		}//fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			
			try {con.close();} catch(Exception e ) {e.printStackTrace();}
		}
		return lista;
	}
		
	public Cliente PequisarPorId(int id){
		
		Cliente cliente = new Cliente();
		
		try {
			//carregar o drive de comunicação com o banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciar de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");
			
			//vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbcliente where id=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setInt(1, id);//parametro
			
			//Vamos executar a consulta e guardar o resultado na variavel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que está no rs e adicioná-lo
			 * a lista cheia de clientes para, então retorná-la
			 */
			
			if(rs.next()) {
				cliente.setId(rs.getInt(0));
				cliente.setNome(rs.getString(1));
				cliente.setEmail(rs.getString(2));
				cliente.setTelefone(rs.getString(3));
				cliente.setIdade(rs.getInt(4));
			}
			
		}//fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			
			try {con.close();} catch(Exception e ) {e.printStackTrace();}
		}
		return cliente;

	}
	
	
	public List<Cliente> PesquisarTodos(){
		
		List <Cliente> lista = new ArrayList<Cliente>();
		
		try {
			//carregar o drive de comunicação com o banco de dados
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciar de driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientedb?serverTimezone=UTC","root","");
			
			//vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbcliente";
			
			pst = con.prepareStatement(consulta);
			
			//Vamos executar a consulta e guardar o resultado na variavel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que está no rs e adicioná-lo
			 * a lista cheia de clientes para, então retorná-la
			 */
			
			while(rs.next()) {
				lista.add(new Cliente(
						rs.getInt(0),
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)
						));
			}//fim do while/enquanto
		}//fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			
			try {con.close();} catch(Exception e ) {e.printStackTrace();}
		}
		return lista;
	}
}
