import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.Timer;
public class GestaoStock extends JFrame{
	private JTable table;
	private DefaultTableModel defaultmodel;
	private FantasmaModel fantasmamodel;
	private int row;
	public GestaoStock(String title) {
		super(title);
		super.setSize(900, 600);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		super.setResizable(false);
		
		Container maincontainer = super.getContentPane();
		maincontainer.setLayout(new BorderLayout());
		maincontainer.setBackground(Color.WHITE);

		fantasmamodel = new FantasmaModel();
		
		JPanel panel = new JPanel(null) {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				int h = getHeight();
				int hh = h/8;
				GradientPaint gradient = new GradientPaint(0, 0, Color.blue, 0, h, Color.white);
				g2d.setPaint(gradient);
				g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), h));
				 /*g.setColor(Color.blue);
				 g.fillRect(0, 0, getWidth(), hh);
				 g.setColor(Color.white);
				 g.fillRect(0, hh, getWidth(), h - hh);*/
			}
			
		};
		panel.setPreferredSize(new Dimension(900, 600));
		
		JLabel vendas = new JLabel("Ponto de Vendas");
		vendas.setBounds(290, 5, 300, 50);
		vendas.setForeground(Color.WHITE);
		vendas.setFont(new Font("Ariel", Font.CENTER_BASELINE, 35));
		
		JPanel client = new JPanel(null);
		TitledBorder clt = BorderFactory.createTitledBorder("Dados do Cliente");
		client.setBorder(clt);
		client.setBackground(Color.WHITE);
		client.setBounds(0, 75, 390, 120);
		
		JLabel lblclient = new JLabel("Nome: ");
		lblclient.setBounds(10, 25, 50, 20);
		JTextField txtnome = new JTextField();
		txtnome.setBounds(52, 22, 200, 22);
		
		JLabel lbldata = new JLabel("Data: ");
		lbldata.setBounds(10, 55, 50, 20);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateFormatter datformat = new DateFormatter(formato);
		JFormattedTextField txtdata = new JFormattedTextField(datformat);
		txtdata.setColumns(18);
		txtdata.setBounds(52, 55, 200, 22);
		
		JLabel lblprt = new JLabel("Producto Comprado: ");
		lblprt.setBounds(10, 85, 120, 20);
		JTextField txtprt = new JTextField();
		txtprt.setBounds(140, 85, 150, 22);
		
		JButton btnprespro = new JButton("Pesquisa Clnt");
		btnprespro.setBounds(255, 35, 120, 20);

		JPanel proct = new JPanel(null);
		TitledBorder pdt = BorderFactory.createTitledBorder("Dados do Producto");
		proct.setBorder(pdt);
		proct.setBackground(Color.WHITE);
		proct.setBounds(0, 205, 390, 150);
		
		JLabel lblcodigo = new JLabel("Código: ");
		lblcodigo.setBounds(10, 25, 50, 20);
		JTextField txtcodigo = new JTextField(6);
		txtcodigo.setBounds(60, 22, 100, 22);

		JLabel lblproct = new JLabel("Producto à Comprar: ");
		lblproct.setBounds(10, 55, 150, 20);
		JTextField txtproct = new JTextField(15);
		txtproct.setBounds(135, 55, 150, 22);
		
		JButton btnpesqui = new JButton("Pesquisar");
		btnpesqui.setBounds(255, 23, 120, 20);

		JLabel lblprec = new JLabel("Preço: ");
		lblprec.setBounds(10, 85, 120, 20);
		JTextField txtprec = new JTextField(7);
		txtprec.setBounds(55, 85, 80, 22);
		
		JLabel lblquanti = new JLabel("Quantidade: ");
		lblquanti.setBounds(155, 85, 120, 20);
		
		SpinnerNumberModel nrmodel = new SpinnerNumberModel(0, 0, 50, 1);
		JSpinner brquant = new JSpinner(nrmodel);
		brquant.setBounds(230, 85, 50, 20);
		btnpesqui.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int pesquisa = Integer.parseInt(txtcodigo.getText());//.trim().toLowerCase();
				if(table.getRowCount() != 0) {
					for(int linha = 0; linha < table.getRowCount(); linha++) {
						Object cod = table.getValueAt(linha, 0);
						if(cod != null) {
							int cody =(int) cod;//.toString().toLowerCase();
							fantasmamodel.setCodigoo(Integer.parseInt(table.getValueAt(linha, 0).toString()));
			                fantasmamodel.setProducto(table.getValueAt(linha, 1).toString());
			                fantasmamodel.setQuantidad(Integer.parseInt(table.getValueAt(linha, 2).toString()));
			                fantasmamodel.setPvenda(Double.parseDouble(table.getValueAt(linha, 3).toString()));
							if(cody == pesquisa) {
								table.setRowSelectionInterval(linha, linha);
								table.scrollRectToVisible(table.getCellRect(linha, 0, true));
								
								txtproct.setText(fantasmamodel.getProducto());
								nrmodel.setValue(fantasmamodel.getQuantidad());
								txtprec.setText(String.valueOf(fantasmamodel.getPvenda()));
								return;
							}	
						}
					}
				}else {
					JOptionPane.showMessageDialog(null,"Nao existe nenhuma linha na tabela!");
				}
				if(txtcodigo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nao existe nada nesse campo!");
				}
			}
		});
		
		JButton btnadicionar = new JButton("Adicionar Item");
		btnadicionar.setBounds(130, 120, 130, 20);
		
		JPanel compras = new JPanel();
		compras.setBorder(BorderFactory.createTitledBorder("Caminho de Compras"));
		compras.setBackground(Color.WHITE);
		compras.setBounds(395, 75, 485, 400);
		
		defaultmodel = new DefaultTableModel();
		defaultmodel.addColumn("Código");
		defaultmodel.addColumn("Producto");
		defaultmodel.addColumn("Quantidade");
		defaultmodel.addColumn("Preço");
		
		for(int i = 0; i < 50; i++) {
			defaultmodel.addRow(new Object[4]);
		}
		
		table = new JTable(defaultmodel);
		table.setPreferredScrollableViewportSize(new Dimension(460, 350));
		JScrollPane scrolltabela = new JScrollPane(table);
		scrolltabela.setViewportView(table);
		
		JLabel tempo = new JLabel();
		tempo.setBounds(15, 350, 300, 30);
		Timer timpo = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actualizartempo(tempo);
			}
		});
		timpo.start();
		
		JPanel pagar = new JPanel(null);
		pagar.setBorder(BorderFactory.createTitledBorder("Tabela de Venda"));
		pagar.setBackground(Color.WHITE);
		pagar.setBounds(395, 480, 485, 70);
		
		JLabel lblvalortotal = new JLabel();
		lblvalortotal.setText("Valor À Pagar: ");
		lblvalortotal.setBounds(10, 22, 170, 35);
		lblvalortotal.setFont(new Font("Ariel", Font.CENTER_BASELINE, 20));
		
		JTextField txtvalortotal =  new JTextField();
		txtvalortotal.setColumns(7);
		txtvalortotal.setBounds(155, 22, 150, 35);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					row = table.getSelectedRow();
				}
			}
		});
		btnadicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String vlr = txtvalortotal.getText();
					if(!vlr.isEmpty()) {
						int spinner = (int) brquant.getValue();
						double tudo = spinner * fantasmamodel.getPvenda();
						double ValorNr = Double.parseDouble(vlr);
						double novoValor = ValorNr + tudo;//fantasmamodel.getPvenda();
						txtvalortotal.setText(String.valueOf(novoValor));
						
						if(row != -1) {
							int coluna = 2;
							double recebe = (int) table.getValueAt(row, coluna);
							double actual = recebe - spinner;
							
							table.setValueAt(actual, row, coluna);
							((DefaultTableModel) table.getModel()).fireTableCellUpdated(row, coluna);
							fantasmamodel.setQuantidad(actual);	
						}else {
							
						}
						
						
					}else {
						txtvalortotal.setText("0");
					}
					
				} catch (NumberFormatException ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Error!");
					ex.printStackTrace();
				}
			}
		});
		proct.add(btnadicionar);
		proct.add(brquant);
		proct.add(txtprec);
		proct.add(lblprec);
		proct.add(btnpesqui);
		proct.add(lblcodigo);
		proct.add(txtcodigo);
		proct.add(txtproct);
		proct.add(lblproct);
		proct.add(lblquanti);
		
		client.add(btnprespro);
		client.add(txtnome);
		client.add(lblclient);
		client.add(txtdata);
		client.add(lbldata);
		client.add(txtprt);
		client.add(lblprt);
		
		compras.add(scrolltabela);
		
		JButton btncadastrar = new JButton("Cadastrar Producto");
		btncadastrar.setBounds(110, 400, 200, 60);
		btncadastrar.setFont(new Font("Times New Romas", Font.BOLD, 15));
		btncadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CadastroPro("Cadastrar o Producto");
				GestaoStock.this.setVisible(false);
			}
		});
		
		JButton btncancelar = new JButton("Cancelar");
		btncancelar.setBounds(15, 470, 170, 60);
		btncancelar.setFont(new Font("Times New Romas", Font.BOLD, 15));
		
		JButton btnpagamento = new JButton("Pagamento");
		btnpagamento.setBounds(205, 470, 170, 60);
		btnpagamento.setFont(new Font("Times New Romas", Font.BOLD, 15));
		
		pagar.add(lblvalortotal);
		pagar.add(txtvalortotal);
		
		panel.add(btnpagamento);
		panel.add(btncadastrar);
		panel.add(btncancelar);
		panel.add(vendas);
		panel.add(client);
		panel.add(proct);
		panel.add(tempo);
		panel.add(compras);
		panel.add(pagar);
		maincontainer.add(panel, BorderLayout.NORTH);;
		super.setVisible(true);
	}
	
/*	public static void main(String[] args) {
		new GestaoStock("Gestão de Stock");
	}*/
	public void DadosTabela(FantasmaModel fantasma) {
		List<FantasmaModel> prodct = fantasma.getLista();
		for(FantasmaModel produto : prodct) {
			defaultmodel.insertRow(0, new Object[] {produto.getCodigoo(), produto.getProducto(), produto.getQuantidad(), produto.getPvenda()});	
		}
	}
	
	private static void actualizartempo(JLabel temp) {
		SimpleDateFormat dat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String tempoact = dat.format(new Date());
		temp.setFont(new Font("Ariel", Font.CENTER_BASELINE, 15));
		temp.setText("Hora Actual: " + tempoact);
	}
}
