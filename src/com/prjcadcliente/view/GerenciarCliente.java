package com.prjcadcliente.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.prjcadcliente.dominio.Cliente;
import com.prjcadcliente.persistencia.CRUDCliente;

public class GerenciarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeCliente;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtIdade;
	private JTable tbClienteCadastro;
	private Cliente cliente;
	private CRUDCliente crud;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarCliente frame = new GerenciarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciarCliente() {
		
		//Vamos instanciar as classes Cliente e CRUD 
		//Gera um objeto termo "new"
		cliente = new Cliente();
		crud = new CRUDCliente();
		
		
		setTitle("Gerenciar Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			//CADASTRAR
			public void actionPerformed(ActionEvent e) {
			
				//Passar os dados do formulário para o objeto cliente
				cliente.setNome(txtNomeCliente.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				
				String retorno = crud.cadastrar(cliente);
				JOptionPane.showMessageDialog(null, retorno);
				
				txtNomeCliente.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
				
			}
		});
		btnCadastrar.setBounds(14, 231, 89, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(311, 231, 89, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Digite o Id do cliente");
				
				//Passar os dados do formulário para o objeto cliente
				cliente.setNome(txtNomeCliente.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				cliente.setId(Integer.parseInt(id));
				
				String retorno = crud.atualizar(cliente);
				JOptionPane.showMessageDialog(null, retorno);
				
				txtNomeCliente.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
				id="0";
				
			}
		});
		btnAtualizar.setBounds(113, 231, 89, 23);
		contentPane.add(btnAtualizar);
		
		JLabel lblNomeDoCliente = new JLabel("Nome do Cliente:");
		lblNomeDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoCliente.setBounds(10, 11, 114, 14);
		contentPane.add(lblNomeDoCliente);
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(10, 33, 346, 20);
		contentPane.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 88, 346, 20);
		contentPane.add(txtEmail);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 64, 48, 14);
		contentPane.add(lblEmail);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(10, 141, 128, 20);
		contentPane.add(txtTelefone);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(10, 119, 89, 14);
		contentPane.add(lblTelefone);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(10, 189, 96, 20);
		contentPane.add(txtIdade);
		txtIdade.setColumns(10);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdade.setBounds(10, 172, 48, 14);
		contentPane.add(lblIdade);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Digite o Id do cliente para apagar");
				
				cliente.setId(Integer.parseInt(id));
				
				JOptionPane.showMessageDialog(null, crud.deletar(cliente));
			}
		});
		btnDeletar.setBounds(212, 231, 89, 23);
		contentPane.add(btnDeletar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 265, 380, 144);
		contentPane.add(scrollPane);
		
		// ---------- Criar colunas na janela do CONSTRUTO00R!!
		String[] colunas = {"Id", "Nome", "E-Mail", "Telefone", "Idade"};
		
		Object[][] dados = {
			                	{15,"Matheus","matheus@gmail.com","11",18},
			                	{21,"Motta","motta@gmail.com","11",18},
			                	{44,"leandro","leandro@gmail.com","11",18},
			                	{56,"luizlixo","luizlixo@gmail.com","11",20}
			                	
		       };      
			                	
		
		//Vamos construir o modelo de dados para exibir na tabela
		DefaultTableModel modelo = new DefaultTableModel(dados,colunas);
		
		
		tbClienteCadastro = new JTable(modelo);
		scrollPane.setViewportView(tbClienteCadastro);
	}
}
