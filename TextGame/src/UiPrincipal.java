import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UiPrincipal {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable tableArea;
	private JPanel panel_2;
	private JTextArea textArea;
	List<Area> listaAreas;

	public UiPrincipal() {
		carregarConfigs();
		iniciarLista();
		
		frame.setVisible(true);
	}
	
	private void carregarConfigs() {
		
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo");
		mnNewMenu.setActionCommand("");
		menuBar.add(mnNewMenu);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textArea = new JTextArea();
		
		panel_2.add(textArea);
		textArea.setPreferredSize(new Dimension(700, 400));
		textArea.setMinimumSize(new Dimension(700, 400));
		textArea.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 40));
		verticalStrut.setMinimumSize(new Dimension(0, 40));
		panel_1.add(verticalStrut, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton btnExplorar = new JButton("Explorar");
		panel_4.add(btnExplorar);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_4.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		panel_4.add(btnNewButton);
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaAreas = Exploracao.getListArea();
				textArea.setVisible(false);
				carregarLista(listaAreas);
			}

		});
	}
	
	private void iniciarLista() {

		listaAreas = new ArrayList<Area>();

		DefaultTableModel model = criarModelArea(listaAreas);

		tableArea = new JTable();
		tableArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					scrollPane.setVisible(false);
					textArea.setVisible(true);
				}
			}
		});
		tableArea.setModel(model);
		tableArea.setName("Área");
		tableArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane = new JScrollPane(tableArea);
		scrollPane.setPreferredSize(new Dimension(700, 400));
		scrollPane.setMinimumSize(new Dimension(700, 400));
		
		panel_2.add(scrollPane);

		scrollPane.setVisible(false);
	}

	private void carregarLista(List<Area> listaArea) {
		
		DefaultTableModel model = criarModelArea(listaArea);
		
		tableArea.setModel(model);
		
		tableArea.setName("Área");
		
		tableArea.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableArea.getColumnModel().getColumn(0).setMaxWidth(120);
		tableArea.getColumnModel().getColumn(0).setMinWidth(120);
		
		
		tableArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tableArea.setVisible(true);
		scrollPane.setVisible(true);
		
	}
	
	private DefaultTableModel criarModelArea(List<Area> lista) {
		@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel()
		{
			public Class<?> getColumnClass(int column)
			{
				switch(column)
				{
				case 0:
					return String.class;
				case 1:
					return String.class;
				default:
					return String.class;
				}
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return false;
			}
		};

		model.addColumn("Nome");
		model.addColumn("Descrição");

		if(lista != null){
			for (Area area : lista) {
				model.addRow(new Object[]{area.getNome(), area.getDescricao()});
			}
		}
		return model;
	}

}
