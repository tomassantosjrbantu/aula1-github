import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class CadastroPro extends JFrame {
	
	private int selecLinhaIndex = -1;
	private DefaultTableModel tableModel;
	private JTable tbTabela;
	private GestaoStock gestao;
	private FantasmaModel fantasma;
	public CadastroPro(String title) {
		super(title);
		super.setSize(720, 430);
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		
		Container maincontainer = super.getContentPane();
		maincontainer.setLayout(new BorderLayout());
		
		Font fonte = new Font("Times New Roman", Font.CENTER_BASELINE, 20);
		JPanel north = new JPanel(null);
        north.setSize(new Dimension(720, 430));
        JLabel cadastro = new JLabel("Cadastro de Produto");
        cadastro.setBounds(270, 15, 300, 50);
        cadastro.setFont(fonte);
        
        JLabel codigo = new JLabel("Cód: ");
        codigo.setBounds(15, 70, 50, 30);
        JLabel product = new JLabel("Producto: ");
        product.setBounds(150, 70, 60, 30);
        JLabel quantidade = new JLabel("Quantidade: ");
        quantidade.setBounds(500, 70, 80, 30);
        JLabel pre_compra = new JLabel("Preço de Compra: ");
        pre_compra.setBounds(15, 110, 120, 30);
        JLabel pre_venda = new JLabel("Preço de Venda: ");
        pre_venda.setBounds(230, 110, 120, 30);
        JLabel fornecedor = new JLabel("Fornecedor: ");
        fornecedor.setBounds(435, 110, 120, 30);
        
        JTextField txtcod = new JTextField();
        txtcod.setBounds(52, 73, 90, 25);
        JTextField txtprod = new JTextField();
        txtprod.setBounds(215, 73, 270, 25);
        JTextField txtquant = new JTextField();
        txtquant.setBounds(580, 73, 120, 25);
        JTextField txtpre_compra = new JTextField();
        txtpre_compra.setBounds(130, 113, 90, 25);
        JTextField txtpre_venda = new JTextField();
        txtpre_venda.setBounds(330, 113, 90, 25);
        
        String[] empresa = {"Mercearia Mario", "Tomás"};
        DefaultComboBoxModel<String> defaultcombobox = new DefaultComboBoxModel<String>(empresa);
        JComboBox<String> forcers = new JComboBox<>(defaultcombobox);
        forcers.setBounds(515, 110, 120, 30);
        forcers.setSelectedIndex(-1);
        
        JButton mais = new JButton("+");
        mais.setBounds(650, 110, 50, 30);
        
        JPanel subpainel = new JPanel();
        subpainel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        subpainel.setBounds(22, 170, 650, 40);
        
        JButton novo = new JButton("Novo");
        JButton salvar = new JButton("Salvar");
        JButton eliminar = new JButton("Eliminar");
        JButton cancelar = new JButton("Cancelar");
        JTextField txtsub = new JTextField();
        txtsub.setPreferredSize(new Dimension(120,25));
        JButton pesquisar = new JButton("Pesquisar");
        JButton sair = new JButton("Sair");
        
        salvar.setEnabled(false);
        cancelar.setEnabled(false);
        sair.setEnabled(false);
        mais.setEnabled(false);

        novo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mais.setEnabled(true);
				salvar.setEnabled(true);
				sair.setEnabled(true);
				eliminar.setEnabled(false);
				pesquisar.setEnabled(true);
				novo.setEnabled(false);
		        cancelar.setEnabled(true);
				
			}
		});
        JPanel tabela = new JPanel(new BorderLayout(15,15));
        tabela.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        tabela.setBounds(22, 230, 650, 87);
        
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Producto");
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("P.Compra");
		tableModel.addColumn("P.Venda");
		
		for(int i = 0; i < 50; i++) {
			tableModel.addRow(new Object[5]);
		}
		tbTabela = new JTable(tableModel);
		tbTabela.setPreferredScrollableViewportSize(new Dimension(700, 250));
		JScrollPane roll = new JScrollPane(tbTabela);
		roll.setViewportView(tbTabela);
		
		mais.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String novo = JOptionPane.showInputDialog("Digite o nome do novo fornecedor:");
				
				if(novo != null && !novo.isEmpty()) {
					defaultcombobox.addElement(novo);
					forcers.setSelectedItem(novo);
				}
			}
		});
		fantasma = new FantasmaModel();
		salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String producto, fornece;
				int codigoo = Integer.parseInt(txtcod.getText());
				producto = txtprod.getText();
				Object quantidad = Integer.parseInt(txtquant.getText());
				double pcompra = Integer.parseInt(txtpre_compra.getText());
				double pvenda = Integer.parseInt(txtpre_venda.getText());
				fornece = forcers.getSelectedItem().toString();
				if(producto.equals("") && txtquant.getText().equals("") && txtpre_compra.getText().equals("") && txtcod.getText().equals("") && txtpre_venda.getText().equals("") && fornece.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				}else {
					fantasma.salvarDados(codigoo, producto, quantidad, pcompra, pvenda, fornece);
					salvarDados(codigoo, producto, quantidad, pcompra , pvenda, fornece);
					JOptionPane.showMessageDialog(null, "Dados Salvos!");
					tableModel.insertRow(0, new Object[] {fantasma.setCodigoo(codigoo), fantasma.setProducto(producto), fantasma.setQuantidad(quantidad), fantasma.setPcompra(pcompra), fantasma.setPvenda(pvenda)});
					txtcod.setText("");
					txtpre_compra.setText("");
					txtpre_venda.setText("");
					txtprod.setText("");
					txtquant.setText("");
					forcers.setSelectedIndex(-1);
				}
			}
		});
		tabela.add(roll);
		subpainel.add(novo);
        subpainel.add(salvar);
        subpainel.add(eliminar);
        subpainel.add(cancelar);
        subpainel.add(txtsub);
        subpainel.add(pesquisar);
        subpainel.add(sair);
       
        tbTabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         	@Override
         	public void valueChanged(ListSelectionEvent e) {
             	if (!e.getValueIsAdjusting() && tbTabela.getSelectedRow() != -1) {
                 	eliminar.setEnabled(true);
                 	selecLinhaIndex = tbTabela.getSelectedRow();
                 }
         	}
     	});

     	eliminar.addActionListener(new ActionListener() {
         	@Override
         	public void actionPerformed(ActionEvent e) {
             	if (selecLinhaIndex != -1) {
                 	((DefaultTableModel) tbTabela.getModel()).removeRow(selecLinhaIndex);
                 	selecLinhaIndex = -1;
                 	eliminar.setEnabled(false);
             	}
         	}
     	});
     	
     	pesquisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pesquisa = txtsub.getText().trim().toLowerCase();

		        for (int linha = 0; linha < tbTabela.getRowCount(); linha++) {
		            String codigo = tbTabela.getValueAt(linha, 0).toString().toLowerCase();
		            String produto = tbTabela.getValueAt(linha, 1).toString().toLowerCase();
		            String quantidade = tbTabela.getValueAt(linha, 2).toString().toLowerCase();
		            String precoCompra = tbTabela.getValueAt(linha, 3).toString().toLowerCase();
		            String precoVenda = tbTabela.getValueAt(linha, 4).toString().toLowerCase();
		            
		            if (codigo.contains(pesquisa) || produto.contains(pesquisa) || quantidade.contains(pesquisa) || precoCompra.contains(pesquisa) || precoVenda.contains(pesquisa)) {
		                tbTabela.setRowSelectionInterval(linha, linha);
		                tbTabela.scrollRectToVisible(tbTabela.getCellRect(linha, 0, true));
		                return;
		            }
		        }

		        JOptionPane.showMessageDialog(null, "Produto não encontrado.");
		    }
			
		});
     	
     	gestao = new GestaoStock("Gestão de Producto");
     	JButton btnVenda = new JButton("Campo de Venda");
     	btnVenda.setBounds(250, 325, 200, 60);
     	btnVenda.setFont(new Font("Times New Romas", Font.BOLD, 15));
     	btnVenda.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(gestao != null) {
					gestao.setVisible(true);
					gestao.DadosTabela(fantasma);
	                CadastroPro.this.setVisible(false);
				}else {
					JOptionPane.showConfirmDialog(null, "Nenhum");
				}
				
			}
		});
     	
     	north.add(btnVenda);
        north.add(cadastro);
		north.add(txtcod);
		north.add(codigo);
		north.add(product);
		north.add(txtprod);
		north.add(quantidade);
		north.add(txtquant);
		north.add(pre_compra);
		north.add(txtpre_compra);
		north.add(pre_venda);
		north.add(txtpre_venda);
		north.add(fornecedor);
		north.add(forcers);
		north.add(mais);
		north.add(subpainel);
		north.add(tabela);
		
		maincontainer.add(north, BorderLayout.CENTER);
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		new CadastroPro("Cadastro");
	}
	
	private void salvarDados(int codigo, String produto, Object quant, double precompra, double prevenda, String fornece) {
		try {
			FileWriter escrever = new FileWriter("cadastro.txt", true);
			escrever.write("Código: " + codigo + "\n");
			escrever.write("Producto: " + produto + "\n");
			escrever.write("Quantidade: " + quant + "\n");
			escrever.write("Preço Venda: " + precompra + "\n");
			escrever.write("Preço Compra: " + prevenda + "\n");
			escrever.write("Fornecedor: " + fornece + "\n");
			escrever.write("/n");
			escrever.close();
		}catch(IOException ev) {
			ev.printStackTrace();
		}
	}
	public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
