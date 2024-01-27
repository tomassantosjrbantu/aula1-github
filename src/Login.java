import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	
	
	public Login(String title) {
		super(title);
		this.setSize(350, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		Container mainContainer = getContentPane();
		mainContainer.setLayout(new BorderLayout());
		
		JPanel centerPanel = new JPanel(null);
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 20));
		
		JLabel lblTituloPagina = new JLabel("Página de Login");
		lblTituloPagina.setBounds(80, 25, 200, 80);
		lblTituloPagina.setFont(new Font("Arial", Font.BOLD, 23));
		
		JLabel lblNomeUsuario = new JLabel("Nome do usuário");
		lblNomeUsuario.setBounds(60, 90, 140, 20);
		lblNomeUsuario.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 17));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(60, 185, 120, 20);
		lblPassword.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 17));
		
		JTextField txtNomeUsuario = new JTextField();
		txtNomeUsuario.setBounds(60, 115, 220, 25);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setBounds(60, 210, 220, 25);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(130, 285, 80, 25);
		
		btnEntrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = txtNomeUsuario.getText();
				
				char[] senha1 = txtPassword.getPassword();
				String senha2 = new String(senha1);
				int senha3 = Integer.parseUnsignedInt(senha2);
				if(!nome.contains("Tomas") && !nome.contains(nome.substring(0, 1).toUpperCase() + nome.substring(1))) {
					JOptionPane.showMessageDialog(null, "A primeira letra deve ser maiuscula", "Erro", JOptionPane.ERROR_MESSAGE);
				}else if(!(senha3 == 123456)) {
					JOptionPane.showMessageDialog(null, "A senha ñ esta correcta!", "Erro", JOptionPane.ERROR_MESSAGE);
				}else {
					new GestaoStock("Gestão de Stock");
					dispose();
				}
			}
		});
		
		centerPanel.add(lblTituloPagina);
		centerPanel.add(lblNomeUsuario);
		centerPanel.add(lblPassword);
		centerPanel.add(txtNomeUsuario);
		centerPanel.add(txtPassword);
		centerPanel.add(btnEntrar);
		
		mainContainer.add(centerPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Login("Login");
		
	}

}
